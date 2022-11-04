package com.joshua.summonerswar.domain.member.dto.request;

import com.joshua.summonerswar.global.constants.ValidationConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class MemberRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Login  {

        private String email;

        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Join {

        @NotBlank
        @Email(regexp = ValidationConstant.MEMBER_EMAIL_REGEXP)
        private String email;

        @NotBlank
        @Pattern(regexp = ValidationConstant.MEMBER_PASSWORD_REGEXP, message = ValidationConstant.MEMBER_PASSWORD_REGEXP_DEFAULT_MESSAGE)
        private String password;

        @NotBlank
        @Pattern(regexp = ValidationConstant.MEMBER_PASSWORD_REGEXP, message = ValidationConstant.MEMBER_PASSWORD_REGEXP_DEFAULT_MESSAGE)
        private String checkPassword;

        @NotBlank
        private String nickname;

        private String batch;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Update {

        private String password;

        private String nickname;

        private String batch;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Find {

        private String leaderMonster;

        private String otherMonster;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PasswordCheck {
        private String password;

        private String checkPassword;
    }


}
