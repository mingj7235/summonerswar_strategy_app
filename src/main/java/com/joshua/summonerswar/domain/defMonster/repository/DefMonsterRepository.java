package com.joshua.summonerswar.domain.defMonster.repository;

import com.joshua.summonerswar.domain.defMonster.entity.DefMonsters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefMonsterRepository extends JpaRepository<DefMonsters, Long>, QueryDefMonsterRepository {

}
