package com.joshua.summonerswar.domain.member.repository;

import com.joshua.summonerswar.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail (String email);

    Member findByUsername(String username);

    @Query("select m from Member m join fetch m.authorities a where m.username = :username")
    Optional<Member> findByUsernameWithAuthority(String username);
}
