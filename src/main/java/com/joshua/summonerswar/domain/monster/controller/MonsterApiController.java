package com.joshua.summonerswar.domain.monster.controller;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.service.MonsterManagerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping ("/v1")
@RequiredArgsConstructor
public class MonsterApiController {

    private final MonsterManagerFacade monsterManagerFacade;
    /**
     * 몬스터 전체 목록 API
     */
    @PostMapping("/monsters/search")
    public ResponseEntity<List<MonsterResponseDto>> getList (final MonsterRequestDto.@NotNull Search condition) {

        return ResponseEntity.ok()
                .body(monsterManagerFacade.search(condition));
    }

    /**
     * 몬스터 등록 API
     *
     * @param request
     * @return
     */
    @PostMapping("/monsters")
    public ResponseEntity<MonsterResponseDto> register (@RequestPart(name = "file", required = false) MultipartFile multipartFile,
                                                        final @RequestPart (name = "request") @NotNull MonsterRequestDto.Register request) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(monsterManagerFacade.register(multipartFile, request));
    }

    /**
     * 몬스터 정보 수정 API
     *
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     */
    @PatchMapping ("/monsters/{id}")
    public ResponseEntity<MonsterResponseDto> update (@PathVariable String id,
                                                      final @RequestPart(name = "file", required = false) MultipartFile multipartFile,
                                                      final @RequestPart (name = "request") @NotNull MonsterRequestDto.Update request) throws IOException {

        return ResponseEntity.ok()
                .body(monsterManagerFacade.update(id, multipartFile, request));
    }

    @DeleteMapping("/monsters/{id}")
    public ResponseEntity<?> delete (@PathVariable String id) {

        monsterManagerFacade.delete(id);

        return ResponseEntity.noContent()
                .build();
    }
}
