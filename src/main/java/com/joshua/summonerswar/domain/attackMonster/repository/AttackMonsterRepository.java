package com.joshua.summonerswar.domain.attackMonster.repository;

import com.joshua.summonerswar.domain.attackMonster.entity.AttackMonsters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackMonsterRepository extends JpaRepository<AttackMonsters, Long>, QueryAtkMonsterRepository {
}
