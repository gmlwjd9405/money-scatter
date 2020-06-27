package com.kkaopay.money.scatter.controller.dto.response;

import com.kkaopay.money.scatter.fixture.DataFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MoneyAndUserDtoTest {

    @DisplayName("MoneyAndUserDto 생성")
    @Test
    public void create() {
        assertThatCode(() -> MoneyAndUserDto.of(BigDecimal.valueOf(3000), (long) 222))
                .doesNotThrowAnyException();
    }

    @DisplayName("MoneyAndUserDto 로 변환")
    @Test
    public void fromPickedUpMoney() {
        MoneyAndUserDto dto = MoneyAndUserDto.fromPickedUpMoney(DataFixture.getPickedUpMoney1());

        MoneyAndUserDto expected = MoneyAndUserDto.builder()
                .money(BigDecimal.valueOf(3000))
                .userId((long) 222)
                .build();

        assertThat(dto)
                .isEqualTo(expected);
    }
}
