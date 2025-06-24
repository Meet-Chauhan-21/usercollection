package com.userProjects.userCollection.controller;

import com.userProjects.userCollection.entity.User;
import com.userProjects.userCollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/security")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("user", user);

        if (user != null && user.getRoles().contains("ADMIN")) {
            return "admin";
        } else {
            return "user";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/security/home")
    public String homeRedirect() {
        return "redirect:/security/register";
    }

}