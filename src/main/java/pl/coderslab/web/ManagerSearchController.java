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
import pl.coderslab.service.UserService;

@Controller
@RequestMapping("/managerSearch")
public class ManagerSearchController {

	private List<Client> clientList;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private UserService userService;

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
		List<User> employees = userService.findBySupervisor(user);
		clientList = clientRepository.findByUserInOrderByIdAsc(employees);
		return "manager/search";
	}

	// DEFAULT SEARCH - TEAM CLIENTS
	@GetMapping("/team")
	public String teamSearch(Model model) {
		User user = authenticationFacade.getAuthenticatedUser();
		List<User> employees = userService.findBySupervisor(user);
		clientList = clientRepository.findByUserInOrderByIdAsc(employees);
		model.addAttribute("clientList", clientList);
		model.addAttribute("activeTab", "tab-default");
		return "manager/search";
	}

	// ALL CLIENTS SEARCH
	@GetMapping("/all")
	public String allSearch(Model model) {
		clientList = clientRepository.findAll();
		model.addAttribute("clientList", clientList);
		model.addAttribute("activeTab", "tab-default");
		return "manager/search";
	}


	// NAME SEARCH
	@PostMapping("/name")
	public String nameSearch(@RequestParam String name, Model model) {
		clientList = clientRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
		model.addAttribute("clientList", clientList);
		model.addAttribute("activeTab", "tab-name");
		model.addAttribute("name", name);
		return "manager/search";
	}

	// DATE SEARCH
	@GetMapping("/date")
	public String searchByDateRange(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			Model model) {

		List<Client> results = clientService.searchByDateRange(startDate, endDate);
		model.addAttribute("clientList", results);
		model.addAttribute("activeTab", "tab-date");
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "manager/search"; // adjust to your actual view name
	}

}
