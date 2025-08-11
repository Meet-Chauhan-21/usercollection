package com.userProjects.userCollection.controller;

import com.userProjects.userCollection.entity.User;
import com.userProjects.userCollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/registration/user")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registration/user")
    public String createUser(User user, Model model) {
        try {
            // Check if username already exists
            if (userRepository.findByUsername(user.getUsername()) != null) {
                model.addAttribute("error", "Username already exists. Please choose a different one.");
                return "register";
            }
            
            // Validate role
            String role = user.getRoles() != null ? user.getRoles().toUpperCase() : "USER";
            if (!role.equals("ADMIN") && !role.equals("USER")) {
                role = "USER";
            }
            
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(role);
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
    public String signup(User user, Model model) {
        return createUser(user, model);
    }
}