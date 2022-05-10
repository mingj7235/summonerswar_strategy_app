package com.joshua.summonerswar.domain.defMonster.service;

import com.joshua.summonerswar.domain.defMonster.dto.request.DefMonsterRequestDto;
import com.joshua.summonerswar.domain.defMonster.dto.response.DefMonsterResponseDto;
import com.joshua.summonerswar.domain.defMonster.entity.DefMonsters;
import com.joshua.summonerswar.domain.defMonster.repository.DefMonsterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefMonsterService {

    private final DefMonsterRepository defMonsterRepository;

    public DefMonsterResponseDto register(final DefMonsterRequestDto.@NotNull Register request) {
        return DefMonsterResponseDto.from(defMonsterRepository.save(DefMonsters.toEntity(request)));
    }

    public Page<DefMonsterResponseDto> findByLeaderMonster (final @NotBlank String leaderMonster,
                                                            Pageable pageable) {
        return defMonsterRepository.findByLeaderMonster(leaderMonster, pageable)
                .map(DefMonsterResponseDto::from);
    }

}
