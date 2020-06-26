package com.kkaopay.money.scatter.repository;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickedUpMoneyRepository extends JpaRepository<PickedUpMoney, Long> {

    List<PickedUpMoney> findByScatterMoneyId(Long scatterMoneyId);
}
