package com.joshua.summonerswar.domain.member.entity;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.admin.entity.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"userRoles"})
@SuperBuilder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable{

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

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> userRoles = new HashSet<>();


    public static Member toEntity (final MemberRequestDto.@NotNull Join request) {
        return Member.builder()
                .email(request.getEmail())
//                .password(EncodeUtils.encode(request.getPassword()))
                .nickname(request.getNickname())
                .batch(request.getBatch())
                .build();
    }
}
