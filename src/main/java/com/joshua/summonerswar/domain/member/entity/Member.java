package com.joshua.summonerswar.domain.member.entity;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.global.base.BaseTime;
import com.joshua.summonerswar.global.util.EncodeUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter (AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
public class Member extends BaseTime {

    @Id
    @GeneratedValue
    @Column (name = "MEMBER_ID")
    private Long id;

    @Column (unique = true)
    private String username;

    @Column (unique = true)
    private String email;

    private String password;

    @Column (unique = true)
    private String nickname;

    private String batch;

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    public static Member ofUser (final MemberRequestDto.@NotNull Join request) {
        Member member = toEntity(request);
        member.addAuthority(Authority.ofUser(member));
        return member;
    }

    public static Member ofAdmin (final MemberRequestDto.@NotNull Join request) {
        Member member = toEntity(request);
        member.addAuthority(Authority.ofAdmin(member));
        return member;
    }

    public List<String> getRoles () {
        return authorities.stream()
                .map(Authority::getRole)
                .collect(Collectors.toList());
    }

    private void addAuthority (Authority authority) {
        authorities.add(authority);
    }

    private static Member toEntity (final MemberRequestDto.@NotNull Join request) {
        return Member.builder()
                .username(UUID.randomUUID().toString())
                .email(request.getEmail())
                .password(EncodeUtils.encode(request.getPassword()))
                .nickname(request.getNickname())
                .batch(request.getBatch())
                .build();
    }
}
