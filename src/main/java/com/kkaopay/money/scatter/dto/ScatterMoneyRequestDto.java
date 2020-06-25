package com.kkaopay.money.scatter.dto;

import com.kkaopay.money.scatter.domain.ScatterMoney;
import com.kkaopay.money.scatter.pojo.UserAndRoomInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScatterMoneyRequestDto {

    private BigDecimal money;
    private int personnel;

    public ScatterMoney toEntity(final UserAndRoomInfo userAndRoomInfo, final String token) {
        return ScatterMoney.builder()
                .token(token)
                .ownerId(userAndRoomInfo.getOwnerId())
                .roomId(userAndRoomInfo.getRoomId())
                .money(money)
                .personnel(personnel)
                .createdDate(LocalDateTime.now())
                .isExpired(false)
                .build();
    }
}
