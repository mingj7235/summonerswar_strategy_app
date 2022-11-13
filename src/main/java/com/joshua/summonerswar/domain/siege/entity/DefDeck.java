package com.joshua.summonerswar.domain.siege.entity;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.entity.relation.AtkDeckDefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
import com.joshua.summonerswar.global.base.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
public class DefDeck extends BaseTime {

    @Id
    @Column (name = "DEK_DECK_ID")
    @GeneratedValue
    private Long id;

    private String deckName;

    private String deckDescription;

    @ManyToOne
    @JoinColumn (name = "member_id")
    private Member member;

    @OneToMany (mappedBy = "defDeck")
    private List<MonsterDefDeck> monsterDefDecks = new ArrayList<>();

    @OneToMany (mappedBy = "defDeck")
    private List<AtkDeckDefDeck> atkDeckDefDecks = new ArrayList<>();

    public static DefDeck toEntityForRegister(final @NotBlank Member member,
                                              final @NotNull DefDeckRequestDto.Register request) {

        return DefDeck.builder()
                .deckName(request.getDeckName())
                .deckDescription(request.getDeckDescription())
                .member(member)
                .build();
    }

    public static DefDeck update(final @NotNull DefDeck defDeck,
                                 final @NotNull DefDeckRequestDto.Update request) {

        if(StringUtils.hasText(request.getDeckName())) {
            defDeck.setDeckName(request.getDeckName());
        }

        if (StringUtils.hasText(request.getDeckDescription())) {
            defDeck.setDeckDescription(request.getDeckDescription());
        }

        return defDeck;
    }
}
