package com.kkaopay.money.scatter.domain.entity;

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
    @JoinColumn(name = "scatterMoney_id")
    @JsonBackReference
    private ScatterMoney scatterMoney;

    @Column(nullable = false)
    private BigDecimal money;

    @Column(nullable = false)
    private Long userId;

    public boolean isSameUser(final long userId) {
        return this.userId.equals(userId);
    }
}
