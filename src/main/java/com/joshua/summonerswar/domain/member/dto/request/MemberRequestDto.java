package com.joshua.summonerswar.domain.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;

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


        private String email;

        private String password;

        private String passwordCheck;

        private String nickname;

        private String batch;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Update {

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
