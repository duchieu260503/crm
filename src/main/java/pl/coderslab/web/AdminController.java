package pl.coderslab.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.entity.Office;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.repository.AddressRepository;
import pl.coderslab.repository.OfficeRepository;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.service.ClientService;
import pl.coderslab.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AddressRepository addressRepository;

	// Display form to add a new user
	@GetMapping("/addUser")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		populateDropdownData(model);
		return "admin/addUser";
	}

	// Handle form submission for creating a user
	@PostMapping("/addUser")
	public String registerUser(@ModelAttribute("user") @Valid User user,
							   BindingResult result,
							   Model model) {
		if (result.hasErrors()) {
			populateDropdownData(model);
			return "admin/addUser";
		}

		System.out.println("[DEBUG] Creating user: " + user.getEmail());
		userService.saveUser(user);
		return "admin/success";
	}

	// DRY: Populate dropdown values
	private void populateDropdownData(Model model) {
		model.addAttribute("allRoles", new HashSet<>(roleRepository.findAll()));
		model.addAttribute("allUsers", userService.findAllAdmins());
		model.addAttribute("allAddresses", addressRepository.findAll());
	}
}
