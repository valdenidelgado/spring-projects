package com.course.dscatalog.services.validation;

import com.course.dscatalog.entities.User;
import com.course.dscatalog.dto.UserInsertDTO;
import com.course.dscatalog.repositories.UserRepository;
import com.course.dscatalog.resources.exceptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {


    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail(userInsertDTO.getEmail());

        if (user != null) {
            list.add(new FieldMessage("email", "Email já existe"));
        }

        if (userInsertDTO.getPassword().length() < 6) {
            list.add(new FieldMessage("password", "A senha deve ter pelo menos 6 caracteres"));
        }


        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
