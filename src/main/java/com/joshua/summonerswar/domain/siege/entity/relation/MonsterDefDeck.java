package com.joshua.summonerswar.domain.siege.entity.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class MonsterDefDeck {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn (name = "monster_id")
    private Monster monster;

    @ManyToOne
    @JoinColumn (name = "def_deck_id")
    private DefDeck defDeck;
}
