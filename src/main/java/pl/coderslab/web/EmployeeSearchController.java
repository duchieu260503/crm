package pl.coderslab.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.AuthenticationFacade;
import pl.coderslab.entity.Client;
import pl.coderslab.entity.User;
import pl.coderslab.repository.ClientRepository;
import pl.coderslab.service.AddressService;
import pl.coderslab.service.ClientService;

@Controller
@RequestMapping("/employeeSearch")
public class EmployeeSearchController {

	private List<Client> clientList;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressService addressService;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("cityList", addressService.getCitiesList());
		model.addAttribute("categoryList", clientService.getCategoryOptions());
		model.addAttribute("chanceList", clientService.getChanceOptions());
		model.addAttribute("sourceList", clientService.getSourceOptions());
	}

	@GetMapping()
	public String defaultSearch() {
		User user = authenticationFacade.getAuthenticatedUser();
		clientList = clientRepository.findByUserOrderByIdAsc(user);
		return "employee/search";
	}

	// DEFAULT - MY CLIENTS
	@GetMapping("/team")
	public String myClients(Model model) {
		User user = authenticationFacade.getAuthenticatedUser();
		clientList = clientRepository.findByUserOrderByIdAsc(user);
		model.addAttribute("clientList", clientList);
		model.addAttribute("activeTab", "tab-default");
		return "employee/search";
	}


	// NAME SEARCH
	@PostMapping("/name")
	public String nameSearch(@RequestParam String name, Model model) {
		User user = authenticationFacade.getAuthenticatedUser();
		clientList = clientRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name).stream()
				.filter(c -> c.getUser().equals(user))
				.collect(Collectors.toList());
		model.addAttribute("clientList", clientList);
		model.addAttribute("activeTab", "tab-name");
		model.addAttribute("name", name);
		return "employee/search";
	}

	// DATE RANGE FILTER
	@GetMapping("/date")
	public String searchByDateRange(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			Model model) {

		User user = authenticationFacade.getAuthenticatedUser();
		List<Client> results = clientService.searchByDateRange(startDate, endDate).stream()
				.filter(c -> c.getUser().equals(user))
				.collect(Collectors.toList());

		model.addAttribute("clientList", results);
		model.addAttribute("activeTab", "tab-date");
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "employee/search";
	}
}
