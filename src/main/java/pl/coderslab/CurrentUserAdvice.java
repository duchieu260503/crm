package pl.coderslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import java.security.Principal;

@ControllerAdvice
public class CurrentUserAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute("currentUser")
    public User getCurrentUser(Principal principal) {
        System.out.println("Resolving currentUser..."); // Add this line to test
        if (principal == null) return null;
        return userService.findByEmail(principal.getName());
    }
}
