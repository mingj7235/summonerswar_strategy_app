package com.joshua.summonerswar.domain.member.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //runtime 까지 유지되도록함
@Target(ElementType.PARAMETER) // parameter에만 사용할 수 있도록 함
// 현재 참조하고 있는 객체가 'anonymousUser' 라면 (anonymousUser는 spring에서 정해진 문자열이다.)
// account라는 속성명은 UserAccount에 있는 account 필드 이름과 매핑이되는 것이다.
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
public @interface CurrentMember {

}
