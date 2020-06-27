package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.domain.model.PickedUpMoney;
import com.kkaopay.money.scatter.service.pojo.PickedUpMoneys;
import com.kkaopay.money.scatter.domain.repository.PickedUpMoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PickedUpMoneyService {

    private final PickedUpMoneyRepository pickedUpMoneyRepository;

    public PickedUpMoneys findAllByScatterMoneyId(final Long scatterMoneyId)  {
        List<PickedUpMoney> pickedUpMonies = pickedUpMoneyRepository.findByScatterMoneyId(scatterMoneyId);

        return PickedUpMoneys.of(pickedUpMonies);
    }

    public void insert(final PickedUpMoney pickedUpMoney) {
        pickedUpMoneyRepository.save(pickedUpMoney);
    }
}
