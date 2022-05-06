package com.joshua.summonerswar.domain.member.service;

import com.joshua.summonerswar.domain.auth.Key.CacheKey;
import com.joshua.summonerswar.domain.auth.repository.LogoutAccessTokenRedisRepository;
import com.joshua.summonerswar.domain.auth.repository.RefreshTokenRedisRepository;
import com.joshua.summonerswar.domain.auth.token.LogoutAccessToken;
import com.joshua.summonerswar.domain.auth.token.RefreshToken;
import com.joshua.summonerswar.domain.auth.token.TokenDto;
import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.dto.response.MemberResponseDto;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import com.joshua.summonerswar.global.enums.JwtExpirationEnums;
import com.joshua.summonerswar.global.util.EncodeUtils;
import com.joshua.summonerswar.global.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;

import static com.joshua.summonerswar.global.enums.JwtExpirationEnums.REFRESH_TOKEN_EXPIRATION_TIME;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public void join(final MemberRequestDto.@NotNull Join request) {
        log.info("일반 유저 가입 : {}", request.getNickname());
        memberRepository.save(Member.ofUser(request));
    }

    public void joinAdmin(final MemberRequestDto.@NotNull Join request) {
        log.info("Admin 유저 가입 : {}", request.getEmail());
        memberRepository.save(Member.ofAdmin(request));
    }

    public TokenDto login (final MemberRequestDto.@NotNull Login request) {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));
        checkPassword(request.getPassword(), member.getPassword());

        String username = member.getUsername();
        String accessToken = jwtTokenUtil.generateAccessToken(username);
        RefreshToken refreshToken = saveRefreshToken(username);
        log.info("login 성공");
        return TokenDto.of(accessToken, refreshToken.getRefreshToken());
    }

    private void checkPassword (String rawPassword, String encodedPassword) {
        if (EncodeUtils.isNotMatches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }

    private RefreshToken saveRefreshToken (String username) {
        return refreshTokenRedisRepository.save(RefreshToken
                .createRefreshToken(
                    username,
                    jwtTokenUtil.generateRefreshToken(username),
                    REFRESH_TOKEN_EXPIRATION_TIME.getValue()
                )
        );
    }

    public MemberResponseDto.MemberInfo getMemberInfo (String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));
        if(!member.getUsername().equals(getCurrentUsername())) {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다.");
        }

        return MemberResponseDto.MemberInfo.builder()
                .username(member.getUsername())
                .email(member.getEmail())
                .build();
    }

    private String getCurrentUsername () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

    @CacheEvict (value = CacheKey.USER, key = "#username")
    public void logout (TokenDto tokenDto, String username) {
        String accessToken = resolveToken(tokenDto.getAccessToken());
        Long remainMilliSeconds = jwtTokenUtil.getRemainMilliSeconds(accessToken);
        refreshTokenRedisRepository.deleteById(username);
        logoutAccessTokenRedisRepository.save(LogoutAccessToken.of(accessToken, username, remainMilliSeconds));
    }

    private String resolveToken (String token) {
        return token.substring(7);
    }

    public TokenDto reissue (String refreshToken) {
        refreshToken = resolveToken(refreshToken);
        String username = getCurrentUsername();
        RefreshToken redisRefreshToken = refreshTokenRedisRepository.findById(username).orElseThrow(NoSuchElementException::new);

        if (refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            return reissueRefreshToken(refreshToken, username);
        }

        throw new IllegalArgumentException("토큰이 일치하지 않음");
    }

    private TokenDto reissueRefreshToken (String refreshToken, String username) {
        if (lessThanReissueExpirationTimesLeft(refreshToken)) {
            String accessToken = jwtTokenUtil.generateAccessToken(username);
            return TokenDto.of(accessToken, saveRefreshToken(username).getRefreshToken());
        }
        return TokenDto.of(jwtTokenUtil.generateAccessToken(username), refreshToken);
    }

    private boolean lessThanReissueExpirationTimesLeft (String refreshToken) {
        return jwtTokenUtil.getRemainMilliSeconds(refreshToken) < JwtExpirationEnums.REISSUE_EXPIRATION_TIME.getValue();
    }


}
