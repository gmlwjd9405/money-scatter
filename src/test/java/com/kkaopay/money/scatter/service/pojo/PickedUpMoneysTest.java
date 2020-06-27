package com.kkaopay.money.scatter.service.pojo;

import com.kkaopay.money.scatter.domain.entity.PickedUpMoney;
import com.kkaopay.money.scatter.domain.entity.ScatterMoney;
import com.kkaopay.money.scatter.fixture.DataFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class PickedUpMoneysTest {

    @DisplayName("PickedUpMoneysTest 생성 성공")
    @Test
    public void create() {
        assertThatCode(() -> PickedUpMoneys.of(DataFixture.getPickedUpMoneyInfo()))
                .doesNotThrowAnyException();
    }

    @DisplayName("PickedUpMoneysTest 생성 실패 : null")
    @ParameterizedTest
    @NullSource
    public void createFailure(final List<PickedUpMoney> pickedUpMoneys) {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> PickedUpMoneys.of(pickedUpMoneys));
    }

    @DisplayName("MoneyAndUserDto 리스트로 변환") // TODO check
    @Test
    public void convertToMoneyAndUserDtos() {
        PickedUpMoneys pickedUpMoneys = DataFixture.getPickedUpMoneys();

        assertThatCode(pickedUpMoneys::convertToMoneyAndUserDtos)
                .doesNotThrowAnyException();
    }

    @DisplayName("할당된 받은 돈의 총 합 (받기 완료된 금액)")
    @Test
    public void calculateCompletedMoney() {
        PickedUpMoneys pickedUpMoneys = DataFixture.getPickedUpMoneys();

        assertThat(pickedUpMoneys.calculateCompletedMoney())
                .isEqualTo(BigDecimal.valueOf(6000));
    }

    @DisplayName("할당 받을 돈 분배")
    @ParameterizedTest
    @MethodSource
    public void distributeMoney(final ScatterMoney scatterMoney, final BigDecimal expected) {
        PickedUpMoneys pickedUpMoneys = DataFixture.getPickedUpMoneys();

        assertThat(pickedUpMoneys.distributeMoney(444, scatterMoney).getMoney())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> distributeMoney() {
        return Stream.of(
                Arguments.of(DataFixture.getScatterMoney(), BigDecimal.valueOf(3000)),
                Arguments.of(DataFixture.getScatterMoney2(), BigDecimal.valueOf(1285))
        );
    }
}
