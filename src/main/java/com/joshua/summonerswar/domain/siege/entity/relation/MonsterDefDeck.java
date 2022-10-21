package com.joshua.summonerswar.domain.siege.entity.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static MonsterDefDeck toEntity(DefDeck defDeck, Monster monsters) {
        return MonsterDefDeck.builder()
                .monster(monsters)
                .defDeck(defDeck)
                .build();
    }
}
