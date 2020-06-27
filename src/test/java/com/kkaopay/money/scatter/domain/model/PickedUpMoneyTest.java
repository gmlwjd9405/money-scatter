package com.kkaopay.money.scatter.domain.model;

import com.kkaopay.money.scatter.domain.model.PickedUpMoney;
import com.kkaopay.money.scatter.fixture.DataFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PickedUpMoneyTest {

    @DisplayName("같은 사용자 id 이면 return true")
    @ParameterizedTest
    @MethodSource
    public void isSameUser(final PickedUpMoney pickedUpMoney, final long expected) {
        assertThat(pickedUpMoney.isSameUser(expected))
                .isTrue();
    }

    private static Stream<Arguments> isSameUser() {
        return Stream.of(
                Arguments.of(DataFixture.getPickedUpMoney1(), 222),
                Arguments.of(DataFixture.getPickedUpMoney2(), 333)
        );
    }
}
