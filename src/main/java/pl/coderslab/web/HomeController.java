package pl.coderslab.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.coderslab.AuthenticationFacade;
import pl.coderslab.entity.Client;
import pl.coderslab.entity.Contract;
import pl.coderslab.entity.User;
import pl.coderslab.service.ClientService;
import pl.coderslab.service.ContractService;

@Controller
public class HomeController {
	

	@Autowired
	private ContractService contractService;
	@Autowired
	private AuthenticationFacade authenticationFacade;
    @Autowired
    private ClientService clientService;

	//SHOW HOME PAGE FOR LOGGED USER
	@GetMapping("/home")
    public String home(Model model) {
		User user = authenticationFacade.getAuthenticatedUser();
		model.addAttribute("user", user);
		
//		List<Contract> contractList = contractService.findByAcceptedBy(user);
		List<Contract> contractList = contractService.findByAuthor(user);
		model.addAttribute("contractList", contractList);

		List<Client> clientList = clientService.findByUser(user);
		model.addAttribute("clientList", clientList);

        return "index";
//		return "login";
	}
	
}
