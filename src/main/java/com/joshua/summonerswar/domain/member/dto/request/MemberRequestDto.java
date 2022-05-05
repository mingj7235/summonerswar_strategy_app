package com.joshua.summonerswar.domain.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public static class Update {

    }

}
