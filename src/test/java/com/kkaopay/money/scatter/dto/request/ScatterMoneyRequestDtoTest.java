package com.kkaopay.money.scatter.dto.request;

import com.kkaopay.money.scatter.fixture.DataFixture;
import com.kkaopay.money.scatter.pojo.UserAndRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatCode;

public class ScatterMoneyRequestDtoTest {

    @DisplayName("entity 로 변환")
    @Test
    public void toEntity() {
        final UserAndRoom userAndRoom = DataFixture.getUserAndRoom();
        final String token = "aaa";

        ScatterMoneyRequestDto dto = new ScatterMoneyRequestDto(new BigDecimal("10000"), 3);

        assertThatCode(() -> dto.toEntity(userAndRoom, token))
                .doesNotThrowAnyException();
    }
}
