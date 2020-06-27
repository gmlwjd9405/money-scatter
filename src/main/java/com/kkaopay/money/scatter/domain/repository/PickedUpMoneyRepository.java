package com.kkaopay.money.scatter.domain.repository;

import com.kkaopay.money.scatter.domain.model.PickedUpMoney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickedUpMoneyRepository extends JpaRepository<PickedUpMoney, Long> {

    List<PickedUpMoney> findByScatterMoneyId(Long scatterMoneyId);
}
