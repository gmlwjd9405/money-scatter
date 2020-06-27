package com.kkaopay.money.scatter.controller.dto.request;

import com.kkaopay.money.scatter.domain.entity.ScatterMoney;
import com.kkaopay.money.scatter.service.pojo.UserAndRoom;
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

    public ScatterMoney toEntity(final UserAndRoom userAndRoom, final String token) {
        return ScatterMoney.builder()
                .token(token)
                .ownerId(userAndRoom.getOwnerId())
                .roomId(userAndRoom.getRoomId())
                .money(money)
                .personnel(personnel)
                .createdDate(LocalDateTime.now())
                .isExpired(false)
                .build();
    }
}
