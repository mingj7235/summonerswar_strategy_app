package com.joshua.summonerswar.domain.auth.repository;

import com.joshua.summonerswar.domain.auth.token.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}
