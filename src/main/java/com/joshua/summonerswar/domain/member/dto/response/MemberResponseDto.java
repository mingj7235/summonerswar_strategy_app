package com.joshua.summonerswar.domain.member.dto.response;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.NotNull;

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

        private String nickName;

        private String batch;

        public static MemberInfo toDtoFromEntity (final @NotNull Member request) {
            return MemberInfo.builder()
                    .email(request.getEmail())
                    .nickName(request.getNickname())
                    .batch(request.getBatch())
                    .build();
        }
    }

}
