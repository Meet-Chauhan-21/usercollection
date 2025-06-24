package com.userProjects.userCollection.controller;

import com.userProjects.userCollection.entity.User;
import com.userProjects.userCollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/registration/user")
    public String createUser(User user, Model model) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(user.getRoles().toUpperCase()); // Just "ADMIN" or "USER"
        userRepository.save(user);
        return "redirect:/security/login";
    }


}