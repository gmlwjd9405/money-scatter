package com.kkaopay.money.scatter.fixture;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import com.kkaopay.money.scatter.domain.ScatterMoney;
import com.kkaopay.money.scatter.dto.response.MoneyAndUserDto;
import com.kkaopay.money.scatter.dto.response.ScatterMoneyDto;
import com.kkaopay.money.scatter.pojo.UserAndRoom;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static MoneyAndUserDto getMoneyAndUserDto1() {
        return MoneyAndUserDto.of(BigDecimal.valueOf(3000), (long) 222);
    }

    public static MoneyAndUserDto getMoneyAndUserDto2() {
        return MoneyAndUserDto.of(BigDecimal.valueOf(3000), (long) 333);
    }

    public static PickedUpMoney getPickedUpMoney1() {
        return PickedUpMoney.builder()
                .scatterMoney(getScatterMoney())
                .money(getMoneyAndUserDto1().getMoney())
                .userId(getMoneyAndUserDto1().getUserId())
                .build();
    }

    public static PickedUpMoney getPickedUpMoney2() {
        return PickedUpMoney.builder()
                .scatterMoney(getScatterMoney())
                .money(getMoneyAndUserDto2().getMoney())
                .userId(getMoneyAndUserDto2().getUserId())
                .build();
    }

    public static List<PickedUpMoney> getPickedUpMoneyInfo() {
        List<PickedUpMoney> info = new ArrayList<>();
        info.add(getPickedUpMoney1());
        info.add(getPickedUpMoney2());

        return info;
    }

    public static ScatterMoneyDto getScatterMoneyDto() {
        return ScatterMoneyDto.valueOf(getScatterMoney(), getPickedUpMoneyInfo());
    }
}
