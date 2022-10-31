package com.joshua.summonerswar.domain.monster.repository;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterRepository extends JpaRepository<Monster, Long>, QueryMonsterRepository {
}
