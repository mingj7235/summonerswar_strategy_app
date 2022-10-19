package com.joshua.summonerswar.domain.monster.service;

import com.joshua.summonerswar.domain.monster.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;
}
