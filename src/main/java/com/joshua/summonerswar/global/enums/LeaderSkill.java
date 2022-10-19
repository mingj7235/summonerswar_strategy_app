package com.joshua.summonerswar.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LeaderSkill {

    // ARENA ###########################################################################################

    // HEALTH

    ARENA_HEALTH_44 ("아레나 체력 44퍼", 44),
    ARENA_HEALTH_33 ("아레나 체력 33퍼", 33),

    // DEFENSE

    // SPEED

    // RESIST

    // CRITICAL

    // GUILD ###########################################################################################

    // HEALTH

    // DEFENSE

    // SPEED

    // RESIST

    // CRITICAL

    // DUNGEON ###########################################################################################

    // HEALTH

    // DEFENSE

    // SPEED

    // RESIST

    // CRITICAL

    // FIRE

    // WATER

    // WIND

    // LIGHT

    // DARK

    ;

    private final String description;

    private final int amount;

}
