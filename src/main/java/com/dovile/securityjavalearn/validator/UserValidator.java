package com.dovile.securityjavalearn.validator;

import com.dovile.securityjavalearn.domain.UserRequest;
import com.dovile.securityjavalearn.entities.User;
import com.dovile.securityjavalearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRequest user = (UserRequest) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        if (user.getFirstName().length() < 4 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.userRequest.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        if (user.getLastName().length() < 4 || user.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size.userRequest.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 4 || user.getEmail().length() > 32 ) {
            errors.rejectValue("email", "Size.userRequest.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userRequest.password");
        }
    }
}
