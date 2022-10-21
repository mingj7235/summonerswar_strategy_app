package com.joshua.summonerswar.domain.siege.service.core.relation;

import com.joshua.summonerswar.domain.siege.repository.relation.RelAtkDeckDefDeckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RelAtkDeckDefDeckService {

    private final RelAtkDeckDefDeckRepository repository;

}
