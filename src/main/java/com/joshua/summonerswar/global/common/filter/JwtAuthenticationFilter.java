package com.joshua.summonerswar.global.common.filter;

import com.joshua.summonerswar.global.auth.MemberDetailService;
import com.joshua.summonerswar.global.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@WebFilter(urlPatterns = "/admin/**/**")
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private MemberDetailService memberDetailService;

    @Value("${spring.redis.timeout-of-minutes}")
    private Long redisTime;

    @Autowired
    private RefreshRedisRepository refreshRedisRepository;
    private AntPathMatcher antPathMatcher;
    private String pattern;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if(request.getRequestURI().contains(ValidationConstant.FILTER_ACCESS_URL)){
                String accessToken = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("accessToken")).findFirst().map(Cookie::getValue).orElse(null);
                String encAdminId = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("adminId")).findFirst().map(Cookie::getValue).orElse(null);
                if (accessToken != null && !accessToken.isEmpty() && jwtUtils.verifyToken(accessToken)) {
                    log.info("accessToken Valid");
                    //인증객체 생성 권한은 무시
                    String username = jwtUtils.getAdminName(accessToken);
                    UserDetails userDetails = adminUserDetailsFacade.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else {
                    if (encAdminId != null) {
                        log.info("refreshToken Not Null");
                        RefreshRedisToken refreshRedisToken = refreshRedisRepository.findById(encAdminId).get();
                        if (Base64Utils.stringDecoder(encAdminId).equals(Base64Utils.stringDecoder(refreshRedisToken.getUserId()))) {
                            log.info("refreshToken redis Valid");
                            //인증객체 생성 권한은 무시
                            UserDetails userDetails = adminUserDetailsFacade.loadUserByUsername(Base64Utils.stringDecoder(refreshRedisToken.getUserId()));
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            //쿠키 초기화
                            CookieUtils.setCookie(response, "accessToken", "", 0);
                            CookieUtils.setCookie(response, "adminId", "", 0);
                            //JWT 토큰및 쿠키 재발행
                            String reAccessToken = jwtUtils.generateAccessToken(Base64Utils.stringDecoder(refreshRedisToken.getUserId()), userDetails.getUsername());
                            String reRefreshToken = jwtUtils.generateRefreshToken(Base64Utils.stringDecoder(refreshRedisToken.getUserId()), userDetails.getUsername());
                            CookieUtils.setCookie(response, "accessToken", reAccessToken);
                            RefreshRedisToken newRedisToken = RefreshRedisToken.createToken(refreshRedisToken.getUserId(), reRefreshToken);
                            refreshRedisRepository.save(newRedisToken);// refreshToken 레디스 재발행
                        } else {
                            SecurityContextHolder.clearContext();
                        }
                    } else {
                        SecurityContextHolder.clearContext();
                    }
                }
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}

