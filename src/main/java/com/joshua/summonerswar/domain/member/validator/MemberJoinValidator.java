package com.joshua.summonerswar.domain.member.validator;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.service.core.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberJoinValidator implements Validator {

    private final MemberService memberService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MemberRequestDto.Join.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberRequestDto.Join adminUserJoinForm = (MemberRequestDto.Join) target;

        if (memberService.existsByEmail(adminUserJoinForm.getEmail()))
            errors.rejectValue("email", "invalid.email", new Object[]{adminUserJoinForm.getEmail()}, "이미 존재하는 이메일입니다.");

        if (!adminUserJoinForm.getPassword().equals(adminUserJoinForm.getPasswordCheck()))
            errors.rejectValue("passwordCheck", "invalid.passwordCheck", new Object[]{adminUserJoinForm.getPasswordCheck()},
                    "비밀번호를 확인해주세요.");
    }

}
