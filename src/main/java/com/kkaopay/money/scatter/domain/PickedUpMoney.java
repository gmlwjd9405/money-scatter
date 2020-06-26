package com.kkaopay.money.scatter.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class PickedUpMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "scatterMoney_id")
    private ScatterMoney scatterMoney;

    @Column(nullable = false)
    private BigDecimal money;

    @Column(nullable = false)
    private Long userId;

//    @Builder
//    public PickedUpMoney(final ScatterMoney scatterMoney, final BigDecimal money, final Long userId) {
//        this.scatterMoney = scatterMoney;
//        this.money = money;
//        this.userId = userId;
//    }
}
