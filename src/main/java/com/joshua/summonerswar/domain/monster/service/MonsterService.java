package com.joshua.summonerswar.domain.monster.service;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.monster.repository.MonsterRepository;
import com.joshua.summonerswar.global.aws.S3Upload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;

    private final S3Upload s3Upload;

    /**
     * 몬스터 등록 비지니스 로직.
     *
     * @param request
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public MonsterResponseDto register(final @NotNull MonsterRequestDto.Register request,
                                       final @NotNull MultipartFile multipartFile) throws IOException {

        Monster monster = monsterRepository.save(
                Monster.toEntity(request, multipartFile != null ? s3Upload.upload(multipartFile) : null));

        return MonsterResponseDto.toDtoFromEntity(monster);

    }

    public List<MonsterResponseDto> getList () {
        return monsterRepository.findAll().stream()
                .map(MonsterResponseDto::toDtoFromEntity)
                .collect(Collectors.toList());
    }


}
