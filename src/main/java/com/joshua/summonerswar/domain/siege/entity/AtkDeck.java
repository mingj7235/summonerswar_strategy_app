package com.joshua.summonerswar.domain.siege.entity;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.entity.relation.AtkDeckDefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterAtkDeck;
import com.joshua.summonerswar.global.base.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @ManyToOne
    @JoinColumn (name = "member_id")
    private Member member;

    @OneToMany (mappedBy = "atkDeck")
    private List<MonsterAtkDeck> monsterAtkDecks = new ArrayList<>();

    @OneToMany (mappedBy = "atkDeck")
    private List<AtkDeckDefDeck> atkDeckDefDecks = new ArrayList<>();

    public static AtkDeck toEntityForRegister(final Member member, final AtkDeckRequestDto.Register request) {
        return AtkDeck.builder()
                .deckName(request.getDeckName())
                .deckDescription(request.getDeckDescription())
                .member(member)
                .build();
    }

    public static AtkDeck update(final @NotNull AtkDeck atkDeck,
                                 final @NotNull AtkDeckRequestDto.Update request) {

        if (StringUtils.hasText(request.getDeckName()))
            atkDeck.setDeckName(request.getDeckName());

        if (StringUtils.hasText(request.getDeckDescription()))
            atkDeck.setDeckDescription(request.getDeckDescription());

        return atkDeck;
    }

}
