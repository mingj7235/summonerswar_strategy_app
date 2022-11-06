package com.joshua.summonerswar.domain.siege.repository;

import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefDeckRepository extends JpaRepository<DefDeck, Long>, QueryDefDeckRepository {


}
