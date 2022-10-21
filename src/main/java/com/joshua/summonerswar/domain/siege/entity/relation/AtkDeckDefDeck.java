package com.joshua.summonerswar.domain.siege.entity.relation;

import com.joshua.summonerswar.domain.siege.entity.AtkDeck;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class AtkDeckDefDeck {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn (name = "atk_deck_id")
    private AtkDeck atkDeck;

    @ManyToOne
    @JoinColumn (name = "def_deck_id")
    private DefDeck defDeck;
}
