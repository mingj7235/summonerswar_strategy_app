package com.joshua.summonerswar.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberManagerDto {

    private String id;
    private String username;
    private String email;
    private int age;
    private String password;
    private List<String> roles;

}
