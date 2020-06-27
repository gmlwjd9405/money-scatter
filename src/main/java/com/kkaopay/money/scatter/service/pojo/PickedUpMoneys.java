package com.kkaopay.money.scatter.service.pojo;

import com.kkaopay.money.scatter.domain.model.PickedUpMoney;
import com.kkaopay.money.scatter.domain.model.ScatterMoney;
import com.kkaopay.money.scatter.controller.dto.response.MoneyAndUserDto;
import com.kkaopay.money.scatter.error.ErrorMessage;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PickedUpMoneys {

    private final List<PickedUpMoney> pickedUpMoneys;

    private PickedUpMoneys(final List<PickedUpMoney> pickedUpMoneys) {
        this.pickedUpMoneys = pickedUpMoneys;
    }

    public static PickedUpMoneys of(final List<PickedUpMoney> pickedUpMoneys) {
        validate(pickedUpMoneys);
        return new PickedUpMoneys(pickedUpMoneys);
    }

    private static void validate(final List<PickedUpMoney> pickedUpMoneys) {
        if (pickedUpMoneys == null) {
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_VALUE);
        }
    }

    public BigDecimal calculateCompletedMoney() {
        BigDecimal total = BigDecimal.ZERO;
        for (PickedUpMoney picked : this.pickedUpMoneys) {
            total = total.add(picked.getMoney());
        }

        return total;
    }

    public List<MoneyAndUserDto> convertToMoneyAndUserDtos() {
        return this.pickedUpMoneys.stream()
                .map(MoneyAndUserDto::fromPickedUpMoney)
                .collect(Collectors.toList());
    }

    public PickedUpMoney distributeMoney(final long userId, final ScatterMoney scatterMoney) {
        return PickedUpMoney.builder()
                .scatterMoney(scatterMoney)
                .money(this.distributeMoney(scatterMoney))
                .userId(userId)
                .build();
    }

    private BigDecimal distributeMoney(final ScatterMoney scatterMoney) {
        BigDecimal originMoney = scatterMoney.getMoney();
        BigDecimal completedMoney = this.calculateCompletedMoney();
        BigDecimal sparedMoney = originMoney.subtract(completedMoney);

        int originPersonnel = scatterMoney.getPersonnel();
        int completedPersonnel = this.pickedUpMoneys.size();
        int sparePersonnel = originPersonnel - completedPersonnel;

        if (sparePersonnel == 1) { // 남은 인원이 1명이면
            return sparedMoney; // 남은 돈 모두 할당
        }
        return sparedMoney.divide(BigDecimal.valueOf(sparePersonnel), BigDecimal.ROUND_DOWN); // 소숫점 버림
    }

    public int size() {
        return this.pickedUpMoneys.size();
    }
}
