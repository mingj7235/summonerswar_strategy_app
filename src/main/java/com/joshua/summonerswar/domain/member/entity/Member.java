package com.joshua.summonerswar.domain.member.entity;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.role.entity.Role;
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
    private String email;

    private String password;

    @Column (unique = true)
    private String nickname;

    private String batch;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> userRoles = new HashSet<>();


    public static Member toEntity (final MemberRequestDto.@NotNull Join request) {
        return Member.builder()
                .email(request.getEmail())
                .password(EncodeUtils.encode(request.getPassword()))
                .nickname(request.getNickname())
                .batch(request.getBatch())
                .role("ROLE_USER")
                .build();
    }
}
