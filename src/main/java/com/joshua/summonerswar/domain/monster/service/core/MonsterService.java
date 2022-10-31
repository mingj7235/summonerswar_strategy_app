package com.joshua.summonerswar.domain.monster.service.core;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public Monster register (final @NotNull MultipartFile multipartFile,
                             final @NotNull MonsterRequestDto.Register request) throws IOException {

        return monsterRepository.save(
                Monster.toEntity(request, multipartFile != null ? s3Upload.upload(multipartFile) : null));
    }

    /**
     * 몬스터 업데이트 비지니스 로직
     *
     * @param request
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public Monster update (final @NotNull Monster monster,
                           final @NotNull MultipartFile multipartFile,
                           final @NotNull MonsterRequestDto.Update request) throws IOException {

        return monsterRepository.save(Objects.requireNonNull(
                Monster.update(monster, request, multipartFile != null ? s3Upload.upload(multipartFile) : null)));
    }

    public void delete(final @NotBlank String id) {
        monsterRepository.deleteById(Long.valueOf(id));
    }

    /**
     * 몬스터 단일 조회 로직
     *
     * @param id
     * @return
     */
    public Monster findById(Long id) {
        return monsterRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Monster Not Found"));
    }

    /**
     * 몬스터 전체 조회 로직
     *
     * @return
     */
    public List<Monster> getList () {
        return monsterRepository.findAll();
    }


    public List<Monster> search(final MonsterRequestDto.@NotNull Search condition) {
        return monsterRepository.searchByConditions(condition);
    }

}
