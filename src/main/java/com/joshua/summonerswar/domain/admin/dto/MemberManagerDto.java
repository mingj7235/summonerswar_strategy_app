package com.joshua.summonerswar.domain.admin.dto;

import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberManagerDto {

    private String id;
    private String email;
    private String nickname;
    private String batch;
    private List<String> roles;

    public static MemberManagerDto toDtoFromEntity (final @NotNull Member member) {
        return MemberManagerDto.builder()
                .id(String.valueOf(member.getId()))
                .email(member.getEmail())
                .nickname(member.getNickname())
                .batch(member.getBatch())
                .roles(member.getUserRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                .build();
    }

}
