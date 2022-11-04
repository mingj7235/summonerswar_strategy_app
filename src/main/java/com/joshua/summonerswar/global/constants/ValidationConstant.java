package com.joshua.summonerswar.global.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationConstant {

    public final static String LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    public final static String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public final static String LOCAL_DATE_TIME_NOT_T_PATTERN = "yyyy-MM-dd hh24:mi:ss";

    public final static String LOCAL_DATE_DELIVERY_PATTERN = "yyyy.MM.dd";
    public final static String LOCAL_DATE_DELIVERY_NOT_COMMA_PATTERN = "yyyyMMdd";

    public final static String MEMBER_EMAIL_REGEXP = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public final static String MEMBER_PASSWORD_REGEXP_DEFAULT_MESSAGE = "비밀번호 형식이 잘못 되었습니다.";

    public final static String MEMBER_PASSWORD_REGEXP = "(?=.*[A-Za-z])(?=.*[0-9]).{8,15}";

    public final static String MEMBER_PHONE_REGEXP_DEFAULT_MESSAGE = "전화번호 형식이 잘못 되었습니다.";

    public final static String MEMBER_PHONE_REGEXP = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
}
