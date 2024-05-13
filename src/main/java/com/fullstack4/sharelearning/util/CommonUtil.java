package com.fullstack4.sharelearning.util;

import java.security.SecureRandom;

public class CommonUtil {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%&*()_+-=[]?";
    private static final int PASSWORD_LENGTH = 8;
    private static SecureRandom random = new SecureRandom();

    public static String generate() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        // 앞의 7자리는 숫자나 영어 알파벳으로 생성
        String lettersAndNumbers = LETTERS + NUMBERS;
        for (int i = 0; i < PASSWORD_LENGTH - 1; i++) {
            password.append(lettersAndNumbers.charAt(random.nextInt(lettersAndNumbers.length())));
        }

        // 마지막 8번째 글자는 특수문자로 생성
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        return password.toString();
    }

}
