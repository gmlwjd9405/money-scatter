package com.kkaopay.money.scatter.pojo;

import com.kkaopay.money.scatter.error.exception.NoRequiredHeaderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UserAndRoomInfoTest {

    @DisplayName("생성 성공: 필수 헤더가 모두 있는 경우")
    @Test
    public void create() {
        final Map<String, Object> headers = new HashMap<>();
        headers.put(UserAndRoomInfo.ROOM_IDENTIFIER_HEADER_NAME, "roomId");
        headers.put(UserAndRoomInfo.USER_IDENTIFIER_HEADER_NAME, "111");

        assertThatCode(() -> UserAndRoomInfo.of(headers))
                .doesNotThrowAnyException();
    }

    @DisplayName("생성 실패: 필수 헤더가 없는 경우 예외 발생")
    @ParameterizedTest
    @MethodSource
    public void createFailure(final Map<String, Object> headers) {
        assertThatExceptionOfType(NoRequiredHeaderException.class)
                .isThrownBy(() -> UserAndRoomInfo.of(headers));
    }

    private static Stream<Arguments> createFailure() {
        Map<String, String> ownerIdHeaderIsNull = new HashMap<>();
        ownerIdHeaderIsNull.put(UserAndRoomInfo.ROOM_IDENTIFIER_HEADER_NAME, "roomId");

        Map<String, String> roomIdHeaderIsNull = new HashMap<>();
        roomIdHeaderIsNull.put(UserAndRoomInfo.USER_IDENTIFIER_HEADER_NAME, "111");

        return Stream.of(
                Arguments.of(ownerIdHeaderIsNull),
                Arguments.of(roomIdHeaderIsNull)
        );
    }
}
