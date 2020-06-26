package com.kkaopay.money.scatter.dto.response;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import com.kkaopay.money.scatter.domain.ScatterMoney;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                                          final List<PickedUpMoney> PickedUpMoneyInfo) {
        return ScatterMoneyDto.builder()
                .createdDate(scatterMoney.getCreatedDate())
                .money(scatterMoney.getMoney())
                .completedMoney(calculateCompletedMoney(PickedUpMoneyInfo))
                .completedMoneyInfo(convertToInfo(PickedUpMoneyInfo))
                .build();
    }

    private static BigDecimal calculateCompletedMoney(final List<PickedUpMoney> PickedUpMoneyInfo) {
        BigDecimal total = BigDecimal.ZERO;
        for (PickedUpMoney picked : PickedUpMoneyInfo) {
            total = total.add(picked.getMoney());
        }

        return total;
    }

    private static List<MoneyAndUserDto> convertToInfo(final List<PickedUpMoney> PickedUpMoneyInfo) {
        return PickedUpMoneyInfo.stream()
                .map(MoneyAndUserDto::fromPickedUpMoney)
                .collect(Collectors.toList());
    }
}
