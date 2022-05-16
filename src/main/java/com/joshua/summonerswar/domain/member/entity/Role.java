package com.joshua.summonerswar.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN ("ROLE_USER,ROLE_ADMIN"),

    USER ("ROLE_USER");

    private final String value;
}
