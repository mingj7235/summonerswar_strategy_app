package com.joshua.summonerswar.domain.member.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class MemberResponseDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberInfo {

        private String email;

        private String username;

    }

}
