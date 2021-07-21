package com.akhtyamovfanil.springboot.demo.controller;

import com.akhtyamovfanil.springboot.demo.model.User;
import com.akhtyamovfanil.springboot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;



   @GetMapping
    public String showUser(Principal principal, Model model) {
     Optional<User> user = userRepository.getUserByUsername(principal.getName());
      model.addAttribute("user", user.get());
        return "user";
    }


}