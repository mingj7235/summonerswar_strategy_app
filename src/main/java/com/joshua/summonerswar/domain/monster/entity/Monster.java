package com.joshua.summonerswar.domain.monster.entity;

import com.joshua.summonerswar.global.enums.Attribute;
import com.joshua.summonerswar.global.enums.LeaderSkill;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter (AccessLevel.PRIVATE)
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Monster {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Attribute attribute;

    private LeaderSkill leaderSkill;

    private String photoPath;
}
