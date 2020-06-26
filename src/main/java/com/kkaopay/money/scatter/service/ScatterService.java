package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import com.kkaopay.money.scatter.domain.ScatterMoney;
import com.kkaopay.money.scatter.dto.request.ScatterMoneyRequestDto;
import com.kkaopay.money.scatter.dto.response.ScatterMoneyDto;
import com.kkaopay.money.scatter.error.exception.NotExistValueException;
import com.kkaopay.money.scatter.error.exception.UnAuthorizationException;
import com.kkaopay.money.scatter.pojo.UserAndRoom;
import com.kkaopay.money.scatter.repository.ScatterRepository;
import com.kkaopay.money.scatter.token.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ScatterService {

    private final ScatterRepository scatterRepository;
    private final PickedUpMoneyService pickedUpMoneyService;

    public String saveScatterMoney(final Map<String, Object> headers, final ScatterMoneyRequestDto dto) {
        UserAndRoom userAndRoom = UserAndRoom.of(headers);
        String token = createToken();

        scatterRepository.save(dto.toEntity(userAndRoom, token));

        return token;
    }

    private String createToken() {
        return TokenGenerator.generate();
    }

    public ScatterMoneyDto show(final Map<String, Object> headers, final String token) {
        UserAndRoom userAndRoom = UserAndRoom.of(headers);
        ScatterMoney scatterMoney = this.findByToken(token);

        validateOwner(userAndRoom, scatterMoney.getOwnerId()) ;

        List<PickedUpMoney> PickedUpMoneyInfo = pickedUpMoneyService.findAllByScatterMoneyId(scatterMoney.getId());

        return ScatterMoneyDto.valueOf(scatterMoney, PickedUpMoneyInfo);
    }

    /** * 유효하지 않은 token : 해당 token 에 대한 뿌리기 건이 없다. */
    private ScatterMoney findByToken(final String token) {
        return scatterRepository.findByToken(token)
                .orElseThrow(NotExistValueException::new);
    }

    /** * 뿌린 사람 자신만 조회를 할 수 있습니다. */
    private void validateOwner(final UserAndRoom userAndRoom, final Long ownerId) {
        if (userAndRoom.isNotSameOwnerId(ownerId)) {
            throw new UnAuthorizationException();
        }
    }
}
