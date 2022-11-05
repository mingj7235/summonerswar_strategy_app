package com.joshua.summonerswar.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum LeaderSkill {

    // ALL AREA (E) #########################################################################################
    // HEALTH

    ALL_AREA_HEALTH_15 ("공통 체력 15퍼", 15, "EH15"),
    ALL_AREA_HEALTH_25 ("공통 체력 25퍼", 25, "EH25"),
    ALL_AREA_HEALTH_33 ("공통 체력 33퍼", 33, "EH33"),


    // ATTACK

    ALL_AREA_ATTACK_15 ("공통 공격력 15퍼", 15, "EA15"),
    ALL_AREA_ATTACK_18 ("공통 공격력 18퍼", 18, "EA18"),
    ALL_AREA_ATTACK_20 ("공통 공격력 20퍼", 20, "EA20"),
    ALL_AREA_ATTACK_22 ("공통 공격력 22퍼", 22, "EA22"),
    ALL_AREA_ATTACK_25 ("공통 공격력 25퍼", 25, "EA25"),
    ALL_AREA_ATTACK_28 ("공통 공격력 28퍼", 28, "EA28"),
    ALL_AREA_ATTACK_33 ("공통 공격력 33퍼", 33, "EA33"),

    // DEFENSE

    ALL_AREA_DEFENSE_20 ("공통 방어력 20퍼", 20, "ED20"),
    ALL_AREA_DEFENSE_25 ("공통 방어력 25퍼", 25, "ED25"),
    ALL_AREA_DEFENSE_33 ("공통 방어력 33퍼", 33, "ED33"),

    // SPEED

    ALL_AREA_SPEED_10 ("공통 공격속도 10퍼", 10, "ES10"),
    ALL_AREA_SPEED_13 ("공통 공격속도 13퍼", 13, "ES13"),
    ALL_AREA_SPEED_15 ("공통 공격속도 15퍼", 15, "ES15"),
    ALL_AREA_SPEED_19 ("공통 공격속도 19퍼", 19, "ES19"),
    ALL_AREA_SPEED_24 ("공통 공격속도 24퍼", 24, "ES24"),

    // CRITICAL DAMAGE

    ALL_AREA_CDMG_25 ("공통 치명타 피해 25퍼", 25, "EK25"),

    // CRITICAL RATE

    ALL_AREA_RATE_10 ("공통 치명타 확률 10퍼", 10, "EC10"),
    ALL_AREA_RATE_13 ("공통 치명타 확률 13퍼", 13, "EC13"),
    ALL_AREA_RATE_15 ("공통 치명타 확률 15퍼", 15, "EC15"),
    ALL_AREA_RATE_17 ("공통 치명타 확률 17퍼", 17, "EC17"),
    ALL_AREA_RATE_19 ("공통 치명타 확률 19퍼", 19, "EC19"),
    ALL_AREA_RATE_24 ("공통 치명타 확률 24퍼", 24, "EC24"),

    // ACCURACY

    ALL_AREA_ACCURACY_15 ("공통 효과 적중 15퍼", 15, "EU15"),
    ALL_AREA_ACCURACY_22 ("공통 효과 적중 22퍼", 22, "EU22"),
    ALL_AREA_ACCURACY_30 ("공통 효과 적중 30퍼", 30, "EU30"),
    ALL_AREA_ACCURACY_41 ("공통 효과 적중 41퍼", 41, "EU41"),

    // RESISTANCE

    ALL_AREA_RESISTANCE_20 ("공통 효과 저항 20퍼", 20, "ER20"),
    ALL_AREA_RESISTANCE_28 ("공통 효과 저항 28퍼", 28, "ER28"),
    ALL_AREA_RESISTANCE_30 ("공통 효과 저항 30퍼", 30, "ER30"),
    ALL_AREA_RESISTANCE_41 ("공통 효과 저항 41퍼", 41, "ER41"),


    // ARENA (A) ###########################################################################################

    // HEALTH

    ARENA_HEALTH_21 ("아레나 체력 21퍼", 21, "AH21"),
    ARENA_HEALTH_33 ("아레나 체력 33퍼", 33, "AH33"),
    ARENA_HEALTH_38 ("아레나 체력 38퍼", 38, "AH38"),
    ARENA_HEALTH_44 ("아레나 체력 44퍼", 44, "AH44"),

    // ATTACK

    ARENA_ATTACK_21 ("아레나 공격력 21퍼", 21, "AA21"),
    ARENA_ATTACK_33 ("아레나 공격력 33퍼", 33, "AA33"),
    ARENA_ATTACK_44 ("아레나 공격력 44퍼", 44, "AA44"),

    // DEFENSE

    ARENA_DEFENSE_27 ("아레나 방어력 27퍼", 27, "AD27"),
    ARENA_DEFENSE_33 ("아레나 방어력 33퍼", 33, "AD33"),
    ARENA_DEFENSE_44 ("아레나 방어력 44퍼", 44, "AD44"),

    // SPEED

    ARENA_SPEED_24 ("아레나 공격속도 24퍼", 24, "AS24"),
    ARENA_SPEED_28 ("아레나 공격속도 28퍼", 28, "AS28"),
    ARENA_SPEED_33 ("아레나 공격속도 33퍼", 33, "AS33"),

    // CRITICAL RATE

    ARENA_RATE_10 ("아레나 치명타 확률 10퍼", 10, "AC10"),
    ARENA_RATE_13 ("아레나 치명타 확률 13퍼", 13, "AC13"),
    ARENA_RATE_15 ("아레나 치명타 확률 15퍼", 15, "AC15"),
    ARENA_RATE_17 ("아레나 치명타 확률 17퍼", 17, "AC17"),
    ARENA_RATE_19 ("아레나 치명타 확률 19퍼", 19, "AC19"),
    ARENA_RATE_24 ("아레나 치명타 확률 24퍼", 24, "AC24"),
    ARENA_RATE_33 ("아레나 치명타 확률 33퍼", 33, "AC33"),

    // ACCURACY

    ARENA_ACCURACY_26 ("아레나 효과 적중 26퍼", 26, "AU26"),
    ARENA_ACCURACY_40 ("아레나 효과 적중 40퍼", 40, "AU40"),
    ARENA_ACCURACY_48 ("아레나 효과 적중 48퍼", 48, "AU48"),
    ARENA_ACCURACY_55 ("아레나 효과 적중 55퍼", 55, "AU55"),

    // RESISTANCE

    ARENA_RESISTANCE_26 ("아레나 효과 저항 26퍼", 26, "AR26"),
    ARENA_RESISTANCE_40 ("아레나 효과 저항 40퍼", 40, "AR40"),
    ARENA_RESISTANCE_48 ("아레나 효과 저항 48퍼", 48, "AR48"),
    ARENA_RESISTANCE_55 ("아레나 효과 저항 55퍼", 55, "AR55"),

    // GUILD (G) ###########################################################################################

    // HEALTH

    GUILD_HEALTH_21 ("길드 체력 21퍼", 21, "GH21"),
    GUILD_HEALTH_33 ("길드 체력 33퍼", 33, "GH33"),
    GUILD_HEALTH_44 ("길드 체력 44퍼", 44, "GH44"),

    // ATTACK

    GUILD_ATTACK_18 ("길드 공격력 18퍼", 18, "GA18"),
    GUILD_ATTACK_21 ("길드 공격력 21퍼", 21, "GA21"),
    GUILD_ATTACK_33 ("길드 공격력 33퍼", 33, "GA33"),
    GUILD_ATTACK_44 ("길드 공격력 44퍼", 44, "GA44"),

    // DEFENSE

    GUILD_DEFENSE_21 ("길드 방어력 21퍼", 21, "GD21"),
    GUILD_DEFENSE_33 ("길드 방어력 33퍼", 33, "GD33"),
    GUILD_DEFENSE_44 ("길드 방어력 44퍼", 44, "GD44"),

    // SPEED

    GUILD_SPEED_16 ("길드 공격속도 16퍼", 16, "GS16"),
    GUILD_SPEED_24 ("길드 공격속도 24퍼", 24, "GS24"),
    GUILD_SPEED_33 ("길드 공격속도 33퍼", 33, "GS33"),

    // CRITICAL RATE

    GUILD_RATE_16 ("길드 치명타 확률 16퍼", 16, "GC16"),
    GUILD_RATE_24 ("길드 치명타 확률 24퍼", 24, "GC24"),
    GUILD_RATE_33 ("길드 치명타 확률 33퍼", 33, "GC33"),

    // ACCURACY

    GUILD_ACCURACY_26 ("길드 효과 적중 26퍼", 26, "GU26"),
    GUILD_ACCURACY_40 ("길드 효과 적중 40퍼", 40, "GU40"),
    GUILD_ACCURACY_55 ("길드 효과 적중 55퍼", 55, "GU55"),

    // RESISTANCE

    GUILD_RESISTANCE_26 ("길드 효과 저항 26퍼", 26, "GR26"),
    GUILD_RESISTANCE_40 ("길드 효과 저항 40퍼", 40, "GR40"),

    // DUNGEON (D) ###########################################################################################

    // HEALTH

    DUNGEON_HEALTH_17 ("던전 체력 17퍼", 17, "DH17"),
    DUNGEON_HEALTH_18 ("던전 체력 18퍼", 18, "DH18"),
    DUNGEON_HEALTH_21 ("던전 체력 21퍼", 21, "DH21"),
    DUNGEON_HEALTH_33 ("던전 체력 33퍼", 33, "DH33"),
    DUNGEON_HEALTH_38 ("던전 체력 38퍼", 38, "DH38"),
    DUNGEON_HEALTH_44 ("던전 체력 44퍼", 44, "DH44"),

    // ATTACK

    DUNGEON_ATTACK_21 ("던전 공격력 21퍼", 21, "DA21"),
    DUNGEON_ATTACK_33 ("던전 공격력 33퍼", 33, "DA33"),
    DUNGEON_ATTACK_38 ("던전 공격력 38퍼", 38, "DA38"),
    DUNGEON_ATTACK_44 ("던전 공격력 44퍼", 44, "DA44"),

    // DEFENSE

    DUNGEON_DEFENSE_33 ("던전 방어력 33퍼", 33, "DD33"),
    DUNGEON_DEFENSE_44 ("던전 방어력 44퍼", 44, "DD44"),

    // SPEED

    DUNGEON_SPEED_24 ("던전 공격속도 24퍼", 24, "DS24"),
    DUNGEON_SPEED_28 ("던전 공격속도 28퍼", 28, "DS28"),

    // CRITICAL RATE

    DUNGEON_RATE_24 ("던전 치명타 확률 24퍼", 24, "DC24"),
    DUNGEON_RATE_28 ("던전 치명타 확률 28퍼", 28, "DC28"),
    DUNGEON_RATE_33 ("던전 치명타 확률 33퍼", 33, "DC33"),

    // ACCURACY

    DUNGEON_ACCURACY_26 ("던전 효과 적중 26퍼", 26,"DU26"),
    DUNGEON_ACCURACY_40 ("던전 효과 적중 40퍼", 40,"DU40"),
    DUNGEON_ACCURACY_55 ("던전 효과 적중 55퍼", 55,"DU55"),

    // RESISTANCE

    DUNGEON_RESISTANCE_33 ("던전 효과 저항 33퍼", 33, "DR33"),
    DUNGEON_RESISTANCE_40 ("던전 효과 저항 40퍼", 40, "DR40"),
    DUNGEON_RESISTANCE_48 ("던전 효과 저항 48퍼", 48, "DR48"),
    DUNGEON_RESISTANCE_55 ("던전 효과 저항 55퍼", 55, "DR55"),

    // FIRE (F) ###########################################################################################

    // HEALTH

    FIRE_HEALTH_30 ("불 속성 체력 30퍼", 30, "FH30"),
    FIRE_HEALTH_50 ("불 속성 체력 50퍼", 50, "FH50"),

    // ATTACK

    FIRE_ATTACK_30 ("불 속성 공격력 30퍼", 30, "FA30"),
    FIRE_ATTACK_35 ("불 속성 공격력 35퍼", 35, "FA35"),
    FIRE_ATTACK_40 ("불 속성 공격력 40퍼", 40, "FA40"),

    // DEFENSE

    FIRE_DEFENSE_30 ("불 속성 방어력 30퍼", 30, "FD30"),
    FIRE_DEFENSE_40 ("불 속성 방어력 40퍼", 40, "FD40"),
    FIRE_DEFENSE_50 ("불 속성 방어력 50퍼", 50, "FD50"),

    // SPEED

    FIRE_SPEED_23 ("불 속성 공격속도 23퍼", 23, "FS23"),
    FIRE_SPEED_30 ("불 속성 공격속도 30퍼", 30, "FS30"),

    // CRITICAL RATE

    FIRE_RATE_23 ("불 속성 치명타 확률 23퍼", 23, "FC23"),
    FIRE_RATE_38 ("불 속성 치명타 확률 38퍼", 38, "FC38"),

    // ACCURACY

    FIRE_ACCURACY_50 ("불 속성 효과 적중 50퍼", 50, "FU50"),

    // RESISTANCE

    FIRE_RESISTANCE_38 ("불 속성 효과 저항 38퍼", 38, "FR38"),
    FIRE_RESISTANCE_50 ("불 속성 효과 저항 50퍼", 50, "FR50"),

    // WATER (H) ###########################################################################################

    // HEALTH

    WATER_HEALTH_30 ("물 속성 체력 30퍼", 30, "HH30"),
    WATER_HEALTH_50 ("물 속성 체력 50퍼", 50, "HH50"),

    // ATTACK

    WATER_ATTACK_30 ("물 속성 공격력 30퍼", 30, "HA30"),
    WATER_ATTACK_35 ("물 속성 공격력 35퍼", 35, "HA35"),
    WATER_ATTACK_40 ("물 속성 공격력 40퍼", 40, "HA40"),

    // DEFENSE

    WATER_DEFENSE_30 ("물 속성 방어력 30퍼", 30, "HD30"),
    WATER_DEFENSE_40 ("물 속성 방어력 40퍼", 40, "HD40"),
    WATER_DEFENSE_50 ("물 속성 방어력 50퍼", 50, "HD50"),

    // SPEED

    WATER_SPEED_23 ("물 속성 공격속도 23퍼", 23, "HS23"),
    WATER_SPEED_30 ("물 속성 공격속도 30퍼", 30, "HS30"),

    // CRITICAL RATE

    WATER_RATE_23 ("물 속성 치명타 확률 23퍼", 23, "HC23"),
    WATER_RATE_38 ("물 속성 치명타 확률 38퍼", 38, "HC38"),

    // ACCURACY

    WATER_ACCURACY_50 ("물 속성 효과 적중 50퍼", 50, "HU50"),

    // RESISTANCE

    WATER_RESISTANCE_38 ("물 속성 효과 저항 38퍼", 38, "HR38"),
    WATER_RESISTANCE_50 ("물 속성 효과 저항 50퍼", 50, "HR50"),

    // WIND (W) ###########################################################################################

    // HEALTH

    WIND_HEALTH_30 ("바람 속성 체력 30퍼", 30, "WH30"),
    WIND_HEALTH_50 ("바람 속성 체력 50퍼", 50, "WH50"),

    // ATTACK

    WIND_ATTACK_30 ("바람 속성 공격력 30퍼", 30, "WA30"),
    WIND_ATTACK_35 ("바람 속성 공격력 35퍼", 35, "WA35"),
    WIND_ATTACK_40 ("바람 속성 공격력 40퍼", 40, "WA40"),

    // DEFENSE

    WIND_DEFENSE_30 ("바람 속성 방어력 30퍼", 30, "WD30"),
    WIND_DEFENSE_40 ("바람 속성 방어력 40퍼", 40, "WD40"),
    WIND_DEFENSE_50 ("바람 속성 방어력 50퍼", 50, "WD50"),

    // SPEED

    WIND_SPEED_23 ("바람 속성 공격속도 23퍼", 23, "WS23"),
    WIND_SPEED_30 ("바람 속성 공격속도 30퍼", 30, "WS30"),

    // CRITICAL RATE

    WIND_RATE_23 ("바람 속성 치명타 확률 23퍼", 23, "WC23"),
    WIND_RATE_38 ("바람 속성 치명타 확률 38퍼", 38, "WC38"),

    // ACCURACY

    WIND_ACCURACY_50 ("바람 속성 효과 적중 50퍼", 50, "WU50"),

    // RESISTANCE

    WIND_RESISTANCE_38 ("바람 속성 효과 저항 38퍼", 38, "WR38"),
    WIND_RESISTANCE_50 ("바람 속성 효과 저항 50퍼", 50, "WR50"),

    // LIGHT (L)###########################################################################################
    // HEALTH

    LIGHT_HEALTH_30 ("빛 속성 체력 30퍼", 30, "LH30"),
    LIGHT_HEALTH_50 ("빛 속성 체력 50퍼", 50, "LH50"),

    // ATTACK

    LIGHT_ATTACK_30 ("빛 속성 공격력 30퍼", 30, "LA30"),
    LIGHT_ATTACK_35 ("빛 속성 공격력 35퍼", 35, "LA35"),
    LIGHT_ATTACK_40 ("빛 속성 공격력 40퍼", 40, "LA40"),

    // DEFENSE

    LIGHT_DEFENSE_25 ("빛 속성 방어력 25퍼", 25, "LD25"),
    LIGHT_DEFENSE_30 ("빛 속성 방어력 30퍼", 30, "LD30"),
    LIGHT_DEFENSE_40 ("빛 속성 방어력 40퍼", 40, "LD40"),
    LIGHT_DEFENSE_50 ("빛 속성 방어력 50퍼", 50, "LD50"),

    // SPEED

    LIGHT_SPEED_23 ("빛 속성 공격속도 23퍼", 23, "LS23"),
    LIGHT_SPEED_30 ("빛 속성 공격속도 30퍼", 30, "LS30"),

    // CRITICAL RATE

    LIGHT_RATE_23 ("빛 속성 치명타 확률 23퍼", 23, "LC23"),
    LIGHT_RATE_38 ("빛 속성 치명타 확률 38퍼", 38, "LC38"),

    // ACCURACY

    LIGHT_ACCURACY_50 ("빛 속성 효과 적중 50퍼", 50, "LU50"),

    // RESISTANCE

    LIGHT_RESISTANCE_38 ("빛 속성 효과 저항 38퍼", 38, "LR38"),
    LIGHT_RESISTANCE_50 ("빛 속성 효과 저항 50퍼", 50, "LR50"),

    // DARK (K) ###########################################################################################

    // HEALTH

    DARK_HEALTH_25 ("어둠 속성 체력 25퍼", 25, "KH25"),
    DARK_HEALTH_30 ("어둠 속성 체력 30퍼", 30, "KH30"),
    DARK_HEALTH_50 ("어둠 속성 체력 50퍼", 50, "KH50"),

    // ATTACK

    DARK_ATTACK_30 ("어둠 속성 공격력 30퍼", 30, "KA30"),
    DARK_ATTACK_35 ("어둠 속성 공격력 35퍼", 35, "KA35"),
    DARK_ATTACK_40 ("어둠 속성 공격력 40퍼", 40, "KA40"),

    // DEFENSE

    DARK_DEFENSE_25 ("어둠 속성 방어력 25퍼", 25, "KD25"),
    DARK_DEFENSE_30 ("어둠 속성 방어력 30퍼", 30, "KD30"),
    DARK_DEFENSE_40 ("어둠 속성 방어력 40퍼", 40, "KD40"),
    DARK_DEFENSE_50 ("어둠 속성 방어력 50퍼", 50, "KD50"),

    // SPEED

    DARK_SPEED_23 ("어둠 속성 공격속도 23퍼", 23, "KS23"),
    DARK_SPEED_30 ("어둠 속성 공격속도 30퍼", 30, "KS30"),

    // CRITICAL RATE

    DARK_RATE_23 ("어둠 속성 치명타 확률 23퍼", 23, "KC23"),
    DARK_RATE_38 ("어둠 속성 치명타 확률 38퍼", 38, "KC38"),

    // ACCURACY

    DARK_ACCURACY_50 ("어둠 속성 효과 적중 50퍼", 50, "KU50"),

    // RESISTANCE

    DARK_RESISTANCE_38 ("어둠 속성 효과 저항 38퍼", 38, "KR38"),
    DARK_RESISTANCE_50 ("어둠 속성 효과 저항 50퍼", 50, "KR50"),
    ;

    private final String description;

    private final int amount;

    private final String code;

    public static LeaderSkill toEnumByCode (String code) {
        for (LeaderSkill leaderSkill : LeaderSkill.values()) {
            if (leaderSkill.getCode().equals(code)) {
                return leaderSkill;
            }
        }

        return null;
    }

    public static LeaderSkill toEnumByEnumName(String enumName) {
        for (LeaderSkill leaderSkill : LeaderSkill.values()) {
            if(leaderSkill.name().equals(enumName)) {
                return leaderSkill;
            }
        }

        return null;
    }

    public static List<LeaderSkill> getLeaderSkills () {
        return List.of(LeaderSkill.values());
    }

}
