package com.joshua.summonerswar.domain.member.entity;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter (AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String batch;

    public static Member toEntity (final @NotNull MemberRequestDto request) {
        return Member.builder()
                .build();
    }
}
