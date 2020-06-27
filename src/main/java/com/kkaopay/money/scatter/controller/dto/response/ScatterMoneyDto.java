package com.kkaopay.money.scatter.controller.dto.response;

import com.kkaopay.money.scatter.domain.entity.ScatterMoney;
import com.kkaopay.money.scatter.service.pojo.PickedUpMoneys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScatterMoneyDto {

    private LocalDateTime createdDate;
    private BigDecimal money;
    private BigDecimal completedMoney; // 받기 완료 금액
    private List<MoneyAndUserDto> completedMoneyInfo; // 받기 완료 정보 (받은 금액, 받은 사용자 아이디) 리스트

    public static ScatterMoneyDto valueOf(final ScatterMoney scatterMoney,
                                          final PickedUpMoneys pickedUpMoneys) {
        return ScatterMoneyDto.builder()
                .createdDate(scatterMoney.getCreatedDate())
                .money(scatterMoney.getMoney())
                .completedMoney(pickedUpMoneys.calculateCompletedMoney())
                .completedMoneyInfo(pickedUpMoneys.convertToMoneyAndUserDtos())
                .build();
    }
}
