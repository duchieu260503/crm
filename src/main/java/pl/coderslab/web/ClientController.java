package pl.coderslab.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthenticationFacade;
import pl.coderslab.entity.Client;
import pl.coderslab.entity.Contract;
import pl.coderslab.entity.ContractType;
import pl.coderslab.service.ClientService;
import pl.coderslab.service.ContractService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@ModelAttribute
	public void addDropdownOptions(Model model) {
		model.addAttribute("chanceList", clientService.getChanceOptions());
		model.addAttribute("categoryList", clientService.getCategoryOptions());
		model.addAttribute("sourceList", clientService.getSourceOptions());
		model.addAttribute("fieldList", clientService.getFieldOptions());
		model.addAttribute("statusList", clientService.getStatusOptions());
	}

	// ========== ADD ==========
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("client", new Client());
		return "client/addClient";
	}

	@PostMapping("/add")
	public String saveClient(@ModelAttribute("client") @Valid Client client, BindingResult result) {
		if (result.hasErrors()) return "client/addClient";

		clientService.saveClientWithLoggedUser(client);
		return "client/success";
	}

	// ========== EDIT ==========
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Client client = clientService.findById(id);
		if (client == null) return "redirect:/client/list";

		model.addAttribute("client", client);
		return "client/editClient";
	}

	@PostMapping("/edit/{id}")
	public String updateClient(@PathVariable Long id,
							   @ModelAttribute("client") @Valid Client updatedClient,
							   BindingResult result) {
		if (result.hasErrors()) return "client/editClient";

		Client existing = clientService.findById(id);
		if (existing == null) return "redirect:/client/list";

		// Update fields
		existing.setName(updatedClient.getName());
		existing.setAbbreviation(updatedClient.getAbbreviation());
		existing.setField(updatedClient.getField());
		existing.setApproachTime(updatedClient.getApproachTime());
		existing.setProjectName(updatedClient.getProjectName());
		existing.setProjectBudget(updatedClient.getProjectBudget());
		existing.setSource(updatedClient.getSource());
		existing.setChance(updatedClient.getChance());
		existing.setCategory(updatedClient.getCategory());
		existing.setUser(authenticationFacade.getAuthenticatedUser());

		clientService.saveClient(existing);
		return "client/success";
	}

	// ========== DETAILS ==========
	@GetMapping("/details/{id}")
	public String viewClientDetails(@PathVariable Long id, Model model) {
		Client client = clientService.findById(id);
		if (client == null) {
			return "redirect:/client/list";
		}

		List<Contract> allContracts = contractService.findByClient(client);

		List<Contract> inProgressContracts = allContracts.stream()
				.filter(c -> c.getType() == ContractType.IN_PROGRESS)
				.collect(Collectors.toList());

		List<Contract> doneDealContracts = allContracts.stream()
				.filter(c -> c.getType() == ContractType.DONE_DEAL)
				.collect(Collectors.toList());

		model.addAttribute("client", client);
		model.addAttribute("inProgressContracts", inProgressContracts);
		model.addAttribute("doneDealContracts", doneDealContracts);

		return "client/clientDetails";
	}


	// Test redirect
	@GetMapping("/testSuccess")
	public String testSuccess() {
		return "client/success";
	}
}
