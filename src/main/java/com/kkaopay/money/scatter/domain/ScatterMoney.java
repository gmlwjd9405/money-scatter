package com.kkaopay.money.scatter.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ScatterMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private BigDecimal money;

    @Column(nullable = false)
    private int personnel;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private boolean isExpired;

    @Builder
    public ScatterMoney(String token, Long ownerId, String roomId, BigDecimal money, int personnel,
                        LocalDateTime createdDate, boolean isExpired) {
        this.token = token;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.money = money;
        this.personnel = personnel;
        this.createdDate = createdDate;
        this.isExpired = isExpired;
    }
}
