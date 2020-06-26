package com.kkaopay.money.scatter.fixture;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import com.kkaopay.money.scatter.domain.ScatterMoney;
import com.kkaopay.money.scatter.pojo.UserAndRoom;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DataFixture {

    public DataFixture() {
    }

    public static Map<String, Object> getHeaders() {
        final Map<String, Object> headers = new HashMap<>();
        headers.put(UserAndRoom.ROOM_IDENTIFIER_HEADER_NAME, "roomId");
        headers.put(UserAndRoom.USER_IDENTIFIER_HEADER_NAME, "111");

        return headers;
    }

    public static UserAndRoom getUserAndRoom() {
        return UserAndRoom.of(getHeaders());
    }

    public static ScatterMoney getScatterMoney() {
        return ScatterMoney.builder()
                .token("ABC")
                .ownerId((long) 111)
                .roomId("roomId")
                .money(BigDecimal.valueOf(9000))
                .personnel(3)
                .createdDate(LocalDateTime.now())
                .isExpired(false)
                .build();
    }

    public static PickedUpMoney getPickedUpMoney() {
        return PickedUpMoney.builder()
                .scatterMoney(getScatterMoney())
                .money(BigDecimal.valueOf(3000))
                .userId((long) 222)
                .build();
    }
}
