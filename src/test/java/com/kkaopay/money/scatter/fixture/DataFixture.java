package com.kkaopay.money.scatter.fixture;

import com.kkaopay.money.scatter.domain.model.PickedUpMoney;
import com.kkaopay.money.scatter.domain.model.ScatterMoney;
import com.kkaopay.money.scatter.controller.dto.response.MoneyAndUserDto;
import com.kkaopay.money.scatter.controller.dto.response.ScatterMoneyDto;
import com.kkaopay.money.scatter.service.pojo.PickedUpMoneys;
import com.kkaopay.money.scatter.service.pojo.UserAndRoom;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataFixture {

    public DataFixture() {
    }

    public static UserAndRoom getUserAndRoom() {
        return UserAndRoom.of((long) 111, "roomId");
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

    public static ScatterMoney getScatterMoney2() {
        return ScatterMoney.builder()
                .token("ABC")
                .ownerId((long) 111)
                .roomId("roomId")
                .money(BigDecimal.valueOf(15000))
                .personnel(9)
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

    public static PickedUpMoneys getPickedUpMoneys() {
        return PickedUpMoneys.of(getPickedUpMoneyInfo());
    }

    public static ScatterMoneyDto getScatterMoneyDto() {
        return ScatterMoneyDto.valueOf(getScatterMoney(), getPickedUpMoneys());
    }
}
