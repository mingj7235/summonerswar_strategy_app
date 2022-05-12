package com.joshua.summonerswar.domain.member.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberLoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
