package com.kkaopay.money.scatter.support;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenGeneratorTest {

    @DisplayName("예측 불가능한 3자리 문자열 token 생성")
    @Test
    public void generate() {
        int length = 3;
        String token = RandomTokenGenerator.of(length).generate();
        System.out.println(token);

        assertThat(token.length())
                .isEqualTo(length);
    }
}
