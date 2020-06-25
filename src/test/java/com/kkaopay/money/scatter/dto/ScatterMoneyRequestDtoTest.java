package com.kkaopay.money.scatter.dto;

import com.kkaopay.money.scatter.pojo.UserAndRoomInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;

public class ScatterMoneyRequestDtoTest {

    @DisplayName("entity 로 변환")
    @Test
    public void toEntity() {
        final Map<String, Object> headers = new HashMap<>();
        headers.put(UserAndRoomInfo.ROOM_IDENTIFIER_HEADER_NAME, "roomId");
        headers.put(UserAndRoomInfo.USER_IDENTIFIER_HEADER_NAME, "111");
        final UserAndRoomInfo userAndRoomInfo = UserAndRoomInfo.of(headers);
        final String token = "aaa";

        ScatterMoneyRequestDto dto = new ScatterMoneyRequestDto(new BigDecimal("10000"), 3);

        assertThatCode(() -> dto.toEntity(userAndRoomInfo, token))
                .doesNotThrowAnyException();
    }
}
