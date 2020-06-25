package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.dto.ScatterMoneyRequestDto;
import com.kkaopay.money.scatter.pojo.UserAndRoomInfo;
import com.kkaopay.money.scatter.repository.ScatterRepository;
import com.kkaopay.money.scatter.token.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ScatterService {

    private final ScatterRepository scatterRepository;

    public String saveScatterMoney(final Map<String, Object> headers, final ScatterMoneyRequestDto dto) {
        UserAndRoomInfo userAndRoomInfo = UserAndRoomInfo.of(headers);
        String token = createToken();

        scatterRepository.save(dto.toEntity(userAndRoomInfo, token));

        return token;
    }

    private String createToken() {
        return TokenGenerator.generate();
    }
}
