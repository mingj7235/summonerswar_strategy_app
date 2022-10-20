package com.joshua.summonerswar.global.enums;

import lombok.Getter;

@Getter
public enum Attribute {
    FIRE,
    WATER,
    WIND,
    LIGHT,
    DARK

    ;

    public static Attribute toEnumByName (String name) {

        for (Attribute attribute : Attribute.values()) {
            if (attribute.name().equals(name)) {
                return attribute;
            }
        }

        return null;
    }
}
