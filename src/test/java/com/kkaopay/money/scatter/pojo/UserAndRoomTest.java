package com.kkaopay.money.scatter.pojo;

import com.kkaopay.money.scatter.fixture.DataFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class UserAndRoomTest {

    @DisplayName("생성 성공: 필수 헤더가 모두 있는 경우")
    @Test
    public void create() {
        assertThatCode(() -> UserAndRoom.of((long) 111, "roomId"))
                .doesNotThrowAnyException();
    }

    @DisplayName("생성 실패: 필수 헤더가 없는 경우 예외 발생")
    @ParameterizedTest
    @MethodSource
    public void createFailure(final Long ownerId, final String roomId) {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> UserAndRoom.of(ownerId, roomId));
    }

    private static Stream<Arguments> createFailure() {
        return Stream.of(
                Arguments.of(null, "roomId"),
                Arguments.of((long) 111, null)
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
