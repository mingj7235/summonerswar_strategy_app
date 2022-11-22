package com.joshua.summonerswar.domain.siege.entity.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.AtkDeck;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter (AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonsterAtkDeck {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "monster_id")
    private Monster monster;

    @ManyToOne
    @JoinColumn (name = "atk_deck_id")
    private AtkDeck atkDeck;

    public static MonsterAtkDeck toEntity(final AtkDeck atkDeck, final Monster monster) {
        return MonsterAtkDeck.builder()
                .monster(monster)
                .atkDeck(atkDeck)
                .build();
    }

}
