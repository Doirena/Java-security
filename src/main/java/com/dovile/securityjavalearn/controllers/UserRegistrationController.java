package com.dovile.securityjavalearn.controllers;

import com.dovile.securityjavalearn.domain.UserRequest;
import com.dovile.securityjavalearn.service.UserService;
import com.dovile.securityjavalearn.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRequest userRequest, BindingResult bindingResult,
                                      ModelMap model) {
        userValidator.validate(userRequest, bindingResult);
        //Check it is valid every fields not empty
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        //Check email is not dublicate
        if (userService.existByEmail(userRequest.getEmail())) {
            model.addAttribute("dublicate", "Someone already has that username");
            return "registration";
        }

        userService.save(userRequest);
        return "redirect:/registration?success";
    }
}
