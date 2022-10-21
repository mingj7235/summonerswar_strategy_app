package com.joshua.summonerswar.domain.siege.service.core;

import com.joshua.summonerswar.domain.siege.repository.DefDeckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefDeckService {

    private final DefDeckRepository defDeckRepository;
}
