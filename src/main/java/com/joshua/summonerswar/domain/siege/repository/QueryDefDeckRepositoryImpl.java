package com.joshua.summonerswar.domain.siege.repository;

import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.joshua.summonerswar.domain.siege.entity.QDefDeck.defDeck;

@RequiredArgsConstructor
public class QueryDefDeckRepositoryImpl implements QueryDefDeckRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<DefDeckResponseDto> search(final DefDeckRequestDto.Search request) {
        return null;
    }

    @Override
    public List<DefDeck> findAllByOptions(final DefDeckRequestDto.Search request) {
        return jpaQueryFactory.select(defDeck)
                .from(defDeck)
                .where(
                        deckNameLike(request.getDeckName()),
                        makerNickNameLike(request.getMakerName())
                )
                .fetch();
    }

    private BooleanExpression deckNameLike (final @NotBlank String deckName) {
        return StringUtils.hasText(deckName) ? defDeck.deckName.contains(deckName) : null;
    }

    private BooleanExpression makerNickNameLike (final @NotBlank String nickName) {
        return StringUtils.hasText(nickName) ? defDeck.makerNickName.contains(nickName) : null;
    }

}
