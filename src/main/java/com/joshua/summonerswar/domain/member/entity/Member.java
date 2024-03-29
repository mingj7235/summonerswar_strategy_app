package com.joshua.summonerswar.domain.member.entity;

import com.joshua.summonerswar.domain.admin.dto.MemberManagerDto;
import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter (AccessLevel.PRIVATE)
//@ToString(exclude = {"userRoles"})
@SuperBuilder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column (unique = true)
    private String email;

    @Column
    private String password;

    @Column (unique = true)
    private String nickname;

    @Column
    private String batch;

    @OneToMany (mappedBy = "member")
    private List<DefDeck> defDeckList;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> userRoles = new HashSet<>();


    public static Member toEntity (final MemberRequestDto.@NotNull Join request) {
        return Member.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .batch(request.getBatch())
                .build();
    }

    public static Member toEntityFromMemberManagerDto (final @NotNull MemberManagerDto request) {
        return Member.builder()
                .id(Long.valueOf(request.getId()))
                .email(request.getEmail())
                .nickname(request.getNickname())
                .batch(request.getBatch())
                .build();
    }

    public static Member updateRole(Member member, Set<Role> roles) {
        member.setUserRoles(roles);
        return member;
    }

    public static Member updatePassword(Member member, String encodedPassword) {
        member.setPassword(encodedPassword);
        return member;
    }

    public static Member updateInfo(final Member member, final MemberRequestDto.Update request) {
        if (StringUtils.hasText(request.getNickname()))
            member.setNickname(request.getNickname());

        if (StringUtils.hasText(request.getBatch()))
            member.setBatch(request.getBatch());

        return member;
    }

    public static Member updateInfoFromAdmin (final Member member, final MemberManagerDto request) {
        if (StringUtils.hasText(request.getBatch()))
            member.setBatch(request.getBatch());

        return member;
    }

}
