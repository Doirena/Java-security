package com.dovile.securityjavalearn.controllers;

import com.dovile.securityjavalearn.domain.UserRequest;
import com.dovile.securityjavalearn.service.UserService;
import com.dovile.securityjavalearn.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping
    public String showRegistrationForm(Model modelMap) {
        modelMap.addAttribute("user", new UserRequest());
        return "registration";
    }


    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRequest userRequest) {
//        if (userRequest.getEmail().equals("test1@test.com")) {
//            return "registration";
//        }
        userService.save(userRequest);
        return "redirect:/registration?success";
    }
}
