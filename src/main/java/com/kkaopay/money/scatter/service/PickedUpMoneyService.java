package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import com.kkaopay.money.scatter.repository.PickedUpMoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PickedUpMoneyService {

    private final PickedUpMoneyRepository pickedUpMoneyRepository;

    public List<PickedUpMoney> findAllByScatterMoneyId(final Long scatterMoneyId)  {
        return pickedUpMoneyRepository.findByScatterMoneyId(scatterMoneyId);
    }
}
