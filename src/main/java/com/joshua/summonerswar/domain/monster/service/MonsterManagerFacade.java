package com.joshua.summonerswar.domain.monster.service;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.monster.service.core.MonsterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MonsterManagerFacade {

    private final MonsterService monsterService;

    /**
     * 몬스터 단건 조회
     *
     * @param id
     * @return
     */
    public MonsterResponseDto findById(final @NotBlank String id) {
        return MonsterResponseDto.toDtoFromEntity(monsterService.findById(Long.valueOf(id)));
    }

    /**
     * 몬스터 리스트 조회
     *
     * @return
     */
    public List<MonsterResponseDto> getList() {
        return monsterService.getList().stream()
                .map(MonsterResponseDto::toDtoFromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 몬스터 등록
     *
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     */
    public MonsterResponseDto register (final @NotNull MultipartFile multipartFile,
                                        final @NotNull MonsterRequestDto.Register request) throws IOException {

        Monster monster = monsterService.register(multipartFile, request);
        return MonsterResponseDto.toDtoFromEntity(monster);
    }

    /**
     * 몬스터 업데이트
     *
     * @param id
     * @param multipartFile
     * @param request
     * @return
     */
    public MonsterResponseDto update(final @NotBlank String id,
                                     final @NotNull MultipartFile multipartFile,
                                     final @NotNull MonsterRequestDto.Update request) throws IOException {
        Monster monster = monsterService.findById(Long.valueOf(id));
        return MonsterResponseDto.toDtoFromEntity(monsterService.update(monster, multipartFile, request));
    }
}
