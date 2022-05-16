package com.joshua.summonerswar.domain.member.repository;

import com.joshua.summonerswar.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail (String email);
    boolean existsByEmail(String email);

}
