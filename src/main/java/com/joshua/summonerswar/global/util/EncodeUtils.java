package com.joshua.summonerswar.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EncodeUtils implements InitializingBean {

    private static PasswordEncoder encoder;

    private final PasswordEncoder beanEncoder;

    @Override
    public void afterPropertiesSet() {
        encoder = this.beanEncoder;
    }

    public static String encode(final @NotBlank String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean isNotMatches(final @NotBlank String rawPassword,
                                       final @NotBlank String encodePassword) {
        return !encoder.matches(rawPassword, encodePassword);
    }
}



