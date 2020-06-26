package com.kkaopay.money.scatter.pojo;

import com.kkaopay.money.scatter.error.exception.NoRequiredHeaderException;
import com.kkaopay.money.scatter.fixture.DataFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class UserAndRoomTest {

    @DisplayName("생성 성공: 필수 헤더가 모두 있는 경우")
    @Test
    public void create() {
        final Map<String, Object> headers = new HashMap<>();
        headers.put(UserAndRoom.ROOM_IDENTIFIER_HEADER_NAME, "roomId");
        headers.put(UserAndRoom.USER_IDENTIFIER_HEADER_NAME, "111");

        assertThatCode(() -> UserAndRoom.of(headers))
                .doesNotThrowAnyException();
    }

    @DisplayName("생성 실패: 필수 헤더가 Null 또는 Empty")
    @ParameterizedTest
    @NullAndEmptySource
    public void createFailureByNullOrEmpty(final Map<String, Object> headers) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> UserAndRoom.of(headers));
    }

    @DisplayName("생성 실패: 필수 헤더가 없는 경우 예외 발생")
    @ParameterizedTest
    @MethodSource
    public void createFailure(final Map<String, Object> headers) {
        assertThatExceptionOfType(NoRequiredHeaderException.class)
                .isThrownBy(() -> UserAndRoom.of(headers));
    }

    private static Stream<Arguments> createFailure() {
        Map<String, String> ownerIdHeaderIsNull = new HashMap<>();
        ownerIdHeaderIsNull.put(UserAndRoom.ROOM_IDENTIFIER_HEADER_NAME, "roomId");

        Map<String, String> roomIdHeaderIsNull = new HashMap<>();
        roomIdHeaderIsNull.put(UserAndRoom.USER_IDENTIFIER_HEADER_NAME, "111");

        return Stream.of(
                Arguments.of(ownerIdHeaderIsNull),
                Arguments.of(roomIdHeaderIsNull)
        );
    }

    @DisplayName("뿌리기 머니를 만든 사용자가 맞으면 return true")
    @Test
    public void isSameOwnerId()  {
        UserAndRoom userAndRoom = DataFixture.getUserAndRoom();

        assertThat(userAndRoom.isSameOwnerId((long) 111))
                .isTrue();
    }

    @DisplayName("뿌리기 머니를 생성한 방과 일치하지 않으면 return true")
    @Test
    public void isNotSameRoomId()  {
        UserAndRoom userAndRoom = DataFixture.getUserAndRoom();

        assertThat(userAndRoom.isNotSameRoomId("otherRoomId"))
                .isTrue();
    }
}
