package com.joshua.summonerswar.global.security.provider;

import com.joshua.summonerswar.global.security.common.MemberContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /**
         * authentication 객체는 AuthenticationManager (UserDetailsService 를 통해) 로부터 전달 받은 객체. 즉 인증을 위임 받음
         * 즉, 사용자로 부터 전달받은 ID, PW 값을 가지고 있다.
         */

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 실질적으로 CustomUserDetailsService 에서 반환 값이 AccountContext (UserDetails 의 구현체) 이다.
        // 이 때, userDetailsService 에서 ID 검증이 일어난다.
        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(username);

        // Password 검증
        if (!passwordEncoder.matches(password, memberContext.getMember().getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

        // 인증을 성공한 인증 객체를 만들어서 provider 를 호출한 manager 에게 리턴해준다.
        return new UsernamePasswordAuthenticationToken(memberContext.getMember(), null, memberContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
