package com.kkaopay.money.scatter.repository;

import com.kkaopay.money.scatter.domain.ScatterMoney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScatterRepository extends JpaRepository<ScatterMoney, Long> {

    Optional<ScatterMoney> findByToken(String token);
}
