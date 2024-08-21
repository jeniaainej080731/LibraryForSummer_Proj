package com.example.LibraryForSummer.util;

import com.example.LibraryForSummer.models.unique.Utilizer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Utilizer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Utilizer user = (Utilizer) target;

        // Validate name
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (user.getName().length() < 2) {
            errors.rejectValue("name", "Size.utilizer.name");
        }

        // Validate email
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!user.getEmail().matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")) {
            errors.rejectValue("email", "Email.utilizer.email");
        }

        // Validate password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "Size.utilizer.password");
        }
    }
}
