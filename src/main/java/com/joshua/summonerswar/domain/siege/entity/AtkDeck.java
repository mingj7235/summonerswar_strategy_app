package com.joshua.summonerswar.domain.siege.entity;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.entity.relation.AtkDeckDefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterAtkDeck;
import com.joshua.summonerswar.global.base.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
public class AtkDeck extends BaseTime {

    @Id
    @Column (name = "ATK_DECK_ID")
    @GeneratedValue
    private Long id;

    private String deckName;

    private String deckDescription;

    private String makerNickName;

    @OneToMany (mappedBy = "atkDeck")
    private List<MonsterAtkDeck> monsterAtkDecks = new ArrayList<>();

    @OneToMany (mappedBy = "atkDeck")
    private List<AtkDeckDefDeck> atkDeckDefDecks = new ArrayList<>();

    public static AtkDeck toEntityForRegister(final Member member, final AtkDeckRequestDto.Register request) {
        return null;
    }

}
