package com.kkaopay.money.scatter.controller.dto.response;

import com.kkaopay.money.scatter.domain.model.PickedUpMoney;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class MoneyAndUserDto {

    private BigDecimal money;
    private Long userId;

    private MoneyAndUserDto(final BigDecimal money, final Long userId) {
        this.money = money;
        this.userId = userId;
    }

    public static MoneyAndUserDto of(final BigDecimal money, final Long userId) {
        return new MoneyAndUserDto(money, userId);
    }

    public static MoneyAndUserDto fromPickedUpMoney(final PickedUpMoney pickedUpMoney) {
        return MoneyAndUserDto.builder()
                .money(pickedUpMoney.getMoney())
                .userId(pickedUpMoney.getUserId())
                .build();
    }
}
