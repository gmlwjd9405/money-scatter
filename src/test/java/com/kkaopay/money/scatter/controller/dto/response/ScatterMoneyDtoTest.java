package com.kkaopay.money.scatter.controller.dto.response;

import com.kkaopay.money.scatter.domain.entity.ScatterMoney;
import com.kkaopay.money.scatter.fixture.DataFixture;
import com.kkaopay.money.scatter.service.pojo.PickedUpMoneys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ScatterMoneyDtoTest {

    @DisplayName("ScatterMoneyDto 생성")
    @Test
    public void create() {
        ScatterMoney scatterMoney = DataFixture.getScatterMoney();
        PickedUpMoneys pickedUpMoneys = DataFixture.getPickedUpMoneys();

        assertThatCode(() -> ScatterMoneyDto.valueOf(scatterMoney, pickedUpMoneys))
                .doesNotThrowAnyException();
    }

    @DisplayName("calculateCompletedMoney 확인")
    @Test
    public void calculateCompletedMoney() {
        ScatterMoneyDto dto = DataFixture.getScatterMoneyDto();

        assertThat(dto.getCompletedMoney())
                .isEqualTo(BigDecimal.valueOf(6000));
    }

    @DisplayName("convertToInfo 확인")
    @ParameterizedTest
    @MethodSource
    public void convertToInfo(final int index, final MoneyAndUserDto expected) {
        ScatterMoneyDto dto = DataFixture.getScatterMoneyDto();

        assertThat(dto.getCompletedMoneyInfo().get(index))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> convertToInfo() {
        return Stream.of(
                Arguments.of(0, DataFixture.getMoneyAndUserDto1()),
                Arguments.of(1, DataFixture.getMoneyAndUserDto2())
        );
    }
}
