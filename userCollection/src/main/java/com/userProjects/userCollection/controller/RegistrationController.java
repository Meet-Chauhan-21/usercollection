package com.userProjects.userCollection.controller;

import com.userProjects.userCollection.entity.User;
import com.userProjects.userCollection.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration/user")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/user")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/user")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            // Check if username already exists (more efficient query)
            if (userRepository.existsByUsername(user.getUsername())) {
                model.addAttribute("error", "Username already exists. Please choose a different one.");
                return "register";
            }
            
            // Set default role if not provided
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                user.setRoles("USER");
            }
            
            // Encode password and save user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(user.getRoles().toUpperCase());
            userRepository.save(user);
            
            return "redirect:/security/login?registered";
            
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "This username is already taken. Please choose another one.");
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration. Please try again.");
            return "register";
        }
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        return registerUser(user, model);
    }
}