package com.joshua.summonerswar.global.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
public class JWTUtils {

    public static final String REFRESH_AUTHORIZATION = "refresh-" + AUTHORIZATION;

    public static final String BEARER_TOKEN_PREFIX = "Bearer ";

    private final String issuer;

    private final long refreshTokenTimeout;

    private final long accessTokenTimeout;

    private final String secret;


    public JWTUtils(@Value("${jwt.issuer}") String issuer,
                    @Value("${jwt.access.timeout-of-minutes}") long accessTokenTimeout,
                    @Value("${jwt.refresh.timeout-of-minutes}") long refreshTokenTimeout,
                    @Value("${jwt.secret}") String secret) {
        this.issuer = issuer;
        this.secret = secret;
        this.accessTokenTimeout = accessTokenTimeout;
        this.refreshTokenTimeout = refreshTokenTimeout;
    }

    public static String getAdminUserId(String token) {
        return JWT.decode(getToken(token))
                .getClaim("adminId").asString();
    }


    public static String getAdminName(String token) {
        return JWT.decode(getToken(token)).getAudience().get(0);
    }

    private static String getToken(String token) {
        return token.substring(BEARER_TOKEN_PREFIX.length());
    }

    public String generateAccessToken(String adminId, String adminName) {
        return generateToken(adminId,
                adminName,
                accessTokenTimeout);
    }

    public String generateRefreshToken(String adminId, String adminName) {
        return generateToken(adminId,
                adminName,
                refreshTokenTimeout);
    }

    private String generateToken(String adminId, String adminName, long timeout) {
        final Instant now = Instant.now();

        return BEARER_TOKEN_PREFIX + JWT.create()
                .withIssuer(issuer)
                .withAudience(adminName)
                .withClaim("adminId", adminId)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(timeout, ChronoUnit.MINUTES)))
                .sign(getAlgorithm(secret));
    }

    public Algorithm getAlgorithm(String secret) {
        return Algorithm.HMAC512(secret);
    }

    public void setAuthorization(HttpServletResponse response, String accessToken) {
        response.setHeader(AUTHORIZATION, accessToken);
    }

    public void setRefreshAuthorization(HttpServletResponse response, String accessToken) {
        response.setHeader(REFRESH_AUTHORIZATION, accessToken);
    }

    public String getAccessToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }

    public String getRefreshToken(HttpServletRequest request) {
        return request.getHeader(REFRESH_AUTHORIZATION);
    }


    public HttpHeaders getAuthHeaders(String adminId, String adminName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, generateAccessToken(adminId, adminName));
        headers.add(REFRESH_AUTHORIZATION, generateRefreshToken(adminId, adminName));
        return headers;
    }

    public JWTVerifier getRequireToken() {
        return JWT.require(getAlgorithm(secret))
                .withIssuer(issuer)
                .build();
    }

    public void verify(String token) {
        getRequireToken().verify(getToken(token));
    }
    public boolean verifyToken(String token){
        try {
            verify(token);
            return true;
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            System.out.println("Invalid signature/claims");
            return false;
        }
    }



}

