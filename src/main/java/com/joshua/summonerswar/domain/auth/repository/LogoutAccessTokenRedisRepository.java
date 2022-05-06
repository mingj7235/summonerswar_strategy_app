package com.joshua.summonerswar.domain.auth.repository;

import com.joshua.summonerswar.domain.auth.token.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;

public interface LogoutAccessTokenRedisRepository extends CrudRepository<LogoutAccessToken, String> {
}
