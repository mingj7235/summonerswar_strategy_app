package com.joshua.summonerswar.global.enums;

import lombok.Getter;

import java.util.List;

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

    public static List<Attribute> getAttributes() {
        return List.of(Attribute.values());
    }
}
