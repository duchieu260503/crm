package pl.coderslab.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.AuthenticationFacade;
import pl.coderslab.entity.Client;
import pl.coderslab.entity.Contract;
import pl.coderslab.entity.ContractType;
import pl.coderslab.entity.User;
import pl.coderslab.repository.ClientRepository;
import pl.coderslab.repository.ContractRepository;
import pl.coderslab.service.ClientService;
import pl.coderslab.service.ContractService;
import pl.coderslab.service.UserService;
import pl.coderslab.validation.ValidationGroups;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private UserService userService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private javax.validation.Validator validator;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("statusList", contractService.getStatusOfContract());
    }

    private String getRoleView(User user, String viewName) {
        return user.getRole().getName().equals("ROLE_ADMIN") ? "admin_contract/" + viewName : "contract/" + viewName;
    }

    // FORM NEW CONTRACT
    @GetMapping("/new/{id}")
    public String newContract(@PathVariable Long id, Model model) {
        Contract contract = new Contract();
        contract.setType(null); // ensure type is null

        Client client = clientService.findById(id); // ⬅️ Load the actual client
        contract.setClient(client);
        model.addAttribute("client", client);       // ⬅️ Add client object for use in HTML
        model.addAttribute("contract", contract);
        model.addAttribute("contractTypes", ContractType.values());
        System.out.println("Loaded client: " + client);
        System.out.println("Client ID: " + client.getId());
        return getRoleView(authenticationFacade.getAuthenticatedUser(), "new");
    }

    // GENERATING NEW CONTRACT
    @PostMapping("/new/{id}")
    public String registerContract(@PathVariable Long id,
                                   @ModelAttribute("contract") Contract contract,
                                   BindingResult bresult,
                                   Model model) {
		User user = authenticationFacade.getAuthenticatedUser();
		Client client = clientService.findById(id);

		contract.setClient(client);
        contract.setId(null);
		contract.setAuthor(user);
		contract.setCreated(LocalDate.now());
		contract.setAcceptedBy(user);

		// Dynamically validate based on type
		Class<?> validationGroup = (contract.getType() == ContractType.IN_PROGRESS)
				? ValidationGroups.InProgress.class
				: ValidationGroups.DoneDeal.class;

		Set<ConstraintViolation<Contract>> violations = validator.validate(contract, validationGroup);
		for (ConstraintViolation<Contract> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			bresult.rejectValue(propertyPath, "", message);
		}

        if (bresult.hasErrors()) {
            if (contract.getClient() == null) {
                contract.setClient(clientService.findById(id));
            }
            return getRoleView(authenticationFacade.getAuthenticatedUser(), "new");
        }

        if (contract.getType() == ContractType.DONE_DEAL) {
            contract.setStatus("Đã ký");
        }
		// Prepare based on contract type (IN_PROGRESS or DONE_DEAL)
        contractService.prepareAndSave(contract);

        model.addAttribute("result", "Contract created successfully");
        return getRoleView(user, "success");
    }

    // CONTRACT DETAILS
    @GetMapping("/details/{id}")
    public String contractDetails(@PathVariable Long id, Model model) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contract", contract);
        return getRoleView(authenticationFacade.getAuthenticatedUser(), "contractDetails");
    }

    // EDITING CONTRACT
    @GetMapping("/edit/{id}")
    public String editContract(@PathVariable Long id, Model model) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contract", contract);
        model.addAttribute("productList", contractService.getProductOfContract());
        model.addAttribute("contractTypes", ContractType.values());
        return getRoleView(authenticationFacade.getAuthenticatedUser(), "editContract");
    }

    // SAVING EDITED CONTRACT
    @PostMapping("/edit/{id}")
    public String saveContract(
            @ModelAttribute("contract") @Valid Contract contract,
            BindingResult result,
            @PathVariable Long id,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("productList", contractService.getProductOfContract());
            return getRoleView(authenticationFacade.getAuthenticatedUser(), "editContract");
        }

        User user = authenticationFacade.getAuthenticatedUser();
        Contract existing = contractService.findById(id);

        if (existing == null) {
            return "redirect:/contract/all";
        }

        // Preserve immutable fields
        contract.setId(id);
        contract.setClient(existing.getClient());
        contract.setAuthor(existing.getAuthor());
        contract.setCreated(existing.getCreated());
        contract.setAcceptedBy(user);

        // If updated to DONE_DEAL, generate revenue
        contractService.prepareAndSave(contract);

        return getRoleView(user, "success");
    }

    @GetMapping
    public String defaultSearch() {
        User user = authenticationFacade.getAuthenticatedUser();
        return getRoleView(user, "search");
    }

    @GetMapping("/all")
    public String allSearch(Model model) {
        User user = authenticationFacade.getAuthenticatedUser();

        // Fetch and divide contracts by type
        List<Contract> inProgressContracts = contractRepository.findByType(ContractType.IN_PROGRESS);
        List<Contract> doneDealContracts = contractRepository.findByType(ContractType.DONE_DEAL);

        // Pass them to the view
        model.addAttribute("inProgressContracts", inProgressContracts);
        model.addAttribute("doneDealContracts", doneDealContracts);
        model.addAttribute("activeTab", "tab-default");

        return getRoleView(user, "search");
    }


    @GetMapping("/team")
    public String teamSearch(Model model) {
        User user = authenticationFacade.getAuthenticatedUser();

        // Fetch in-progress contracts
        List<Contract> inProgressContracts = user.getRole().getName().equals("ROLE_ADMIN") ?
                contractRepository.findByAuthorInAndTypeOrderByIdAsc(userService.findBySupervisor(user), ContractType.IN_PROGRESS) :
                contractRepository.findByAuthorAndTypeOrderByIdAsc(user, ContractType.IN_PROGRESS);


        // Fetch done deal contracts
        List<Contract> doneDealContracts = user.getRole().getName().equals("ROLE_ADMIN") ?
                contractRepository.findByAuthorInAndTypeOrderByIdAsc(userService.findBySupervisor(user), ContractType.DONE_DEAL) :
                contractRepository.findByAuthorAndTypeOrderByIdAsc(user, ContractType.DONE_DEAL);

        // Add both to model
        model.addAttribute("inProgressContracts", inProgressContracts);
        model.addAttribute("doneDealContracts", doneDealContracts);
        model.addAttribute("activeTab", "tab-default");

        return getRoleView(user, "search");
    }


    @GetMapping("/status")
    public String searchByStatus(@RequestParam("selectedStatus") String selectedStatus, Model model) {
        User user = authenticationFacade.getAuthenticatedUser();

        ContractType type;
        switch (selectedStatus) {
            case "Đang xúc tiến":
                type = ContractType.IN_PROGRESS;
                break;
            case "Đã hợp tác":
                type = ContractType.DONE_DEAL;
                break;
            default:
                throw new IllegalArgumentException("Loại hợp đồng không hợp lệ: " + selectedStatus);
        }

        List<Contract> contracts = user.getRole().getName().equals("ROLE_ADMIN")
                ? contractRepository.findByAuthorInAndTypeOrderByIdAsc(userService.findBySupervisor(user), type)
                : contractRepository.findByTypeAndAuthor(type, user);

        // Split by contract type
        List<Contract> inProgressContracts = contracts.stream()
                .filter(c -> c.getType() == ContractType.IN_PROGRESS)
                .collect(Collectors.toList());

        List<Contract> doneDealContracts = contracts.stream()
                .filter(c -> c.getType() == ContractType.DONE_DEAL)
                .collect(Collectors.toList());

//		System.out.println("Total contracts: " + contracts.size());
//		System.out.println("IN_PROGRESS contracts:");
//		inProgressContracts.forEach(c -> System.out.println("ID: " + c.getId() + ", Type: " + c.getType()));

        model.addAttribute("inProgressContracts", inProgressContracts);
        model.addAttribute("doneDealContracts", doneDealContracts);
        model.addAttribute("selectedStatus", selectedStatus);
        model.addAttribute("activeTab", "tab-status");

        return getRoleView(user, "search");
    }


    @GetMapping("/date")
    public String searchByClientApproachDate(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        User user = authenticationFacade.getAuthenticatedUser();
        List<Contract> contracts;

        if (startDate != null && endDate != null) {
            contracts = user.getRole().getName().equals("ROLE_ADMIN") ?
                    contractRepository.findByAuthorInAndClient_ApproachTimeGreaterThanEqualAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(userService.findBySupervisor(user), startDate, endDate) :
                    contractRepository.findByAuthorAndClient_ApproachTimeGreaterThanEqualAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(user, startDate, endDate);
        } else if (startDate != null) {
            contracts = user.getRole().getName().equals("ROLE_ADMIN") ?
                    contractRepository.findByAuthorInAndClient_ApproachTimeGreaterThanEqualOrderByClient_ApproachTimeAsc(userService.findBySupervisor(user), startDate) :
                    contractRepository.findByAuthorAndClient_ApproachTimeGreaterThanEqualOrderByClient_ApproachTimeAsc(user, startDate);
        } else if (endDate != null) {
            contracts = user.getRole().getName().equals("ROLE_ADMIN") ?
                    contractRepository.findByAuthorInAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(userService.findBySupervisor(user), endDate) :
                    contractRepository.findByAuthorAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(user, endDate);
        } else {
            contracts = user.getRole().getName().equals("ROLE_ADMIN") ?
                    contractRepository.findByAuthorInOrderByCreatedAsc(userService.findBySupervisor(user)) :
                    contractRepository.findByAuthorOrderByCreatedAsc(user);
        }

        List<Contract> inProgressContracts = contracts.stream()
                .filter(c -> c.getType() == ContractType.IN_PROGRESS).collect(Collectors.toList());
        List<Contract> doneDealContracts = contracts.stream()
                .filter(c -> c.getType() == ContractType.DONE_DEAL).collect(Collectors.toList());

        model.addAttribute("inProgressContracts", inProgressContracts);
        model.addAttribute("doneDealContracts", doneDealContracts);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("activeTab", "tab-date");

        return getRoleView(user, "search");
    }

    @GetMapping("/name")
    public String searchByClientName(@RequestParam("name") String name, Model model) {
        User user = authenticationFacade.getAuthenticatedUser();

        List<Contract> contracts = user.getRole().getName().equals("ROLE_ADMIN") ?
                contractRepository.findByAuthorInAndClient_NameContainingIgnoreCase(
                        userService.findBySupervisor(user), name
                ) :
                contractRepository.findByAuthorAndClient_NameContainingIgnoreCase(user, name);

        // Split into two lists
        List<Contract> inProgressContracts = contracts.stream()
                .filter(c -> c.getType() == ContractType.IN_PROGRESS).collect(Collectors.toList());

        List<Contract> doneDealContracts = contracts.stream()
                .filter(c -> c.getType() == ContractType.DONE_DEAL).collect(Collectors.toList());

        model.addAttribute("inProgressContracts", inProgressContracts);
        model.addAttribute("doneDealContracts", doneDealContracts);
        model.addAttribute("searchName", name);
        model.addAttribute("activeTab", "tab-name");

        return getRoleView(user, "search");
    }

}
