package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.controller.dto.request.ScatterMoneyRequestDto;
import com.kkaopay.money.scatter.controller.dto.response.ScatterMoneyDto;
import com.kkaopay.money.scatter.domain.entity.PickedUpMoney;
import com.kkaopay.money.scatter.domain.entity.ScatterMoney;
import com.kkaopay.money.scatter.domain.repository.ScatterRepository;
import com.kkaopay.money.scatter.error.exception.NotExistValueException;
import com.kkaopay.money.scatter.service.pojo.PickedUpMoneys;
import com.kkaopay.money.scatter.service.pojo.UserAndRoom;
import com.kkaopay.money.scatter.support.RandomTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ScatterService {

    public static final int TOKEN_LENGTH = 3;

    private final PickedUpMoneyService pickedUpMoneyService;
    private final RedisService redisService;
    private final ValidateService validateService;
    private final ScatterRepository scatterRepository;

    public String scatter(final Long ownerId, final String roomId, final ScatterMoneyRequestDto dto) {
        UserAndRoom userAndRoom = UserAndRoom.of(ownerId, roomId);
        String token = createToken();

        redisService.set(token, userAndRoom);

        save(dto.toEntity(userAndRoom, token));

        return token;
    }

    @Transactional
    public void save(final ScatterMoney scatterMoney) {
        scatterRepository.save(scatterMoney);
    }

    private String createToken() {
        return RandomTokenGenerator.of(TOKEN_LENGTH).generate();
    }

    public BigDecimal receive(final Long ownerId, final String roomId, final String token) {
        redisService.validateExpiredKey(token);

        UserAndRoom userAndRoom = UserAndRoom.of(ownerId, roomId);
        ScatterMoney scatterMoney = this.findByToken(token);

        validateService.validateIsNotOwnerAndSameRoom(userAndRoom, scatterMoney);

        PickedUpMoneys pickedUpMoneyInfo = pickedUpMoneyService.findAllByScatterMoneyId(scatterMoney.getId());

        validateService.validateExistPickedUpMoney(scatterMoney.getPersonnel(), pickedUpMoneyInfo.size());
        validateService.validateDuplicatedReceiver(userAndRoom.getOwnerId(), pickedUpMoneyInfo.getPickedUpMoneys());

        PickedUpMoney newPickedUpMoney = pickedUpMoneyInfo.distributeMoney(userAndRoom.getOwnerId(), scatterMoney);
        savePickedUpMoney(newPickedUpMoney);

        return newPickedUpMoney.getMoney();
    }

    @Transactional
    public void savePickedUpMoney(final PickedUpMoney pickedUpMoney) {
        pickedUpMoneyService.insert(pickedUpMoney);
    }

    public ScatterMoneyDto show(final Long ownerId, final String roomId, final String token) {
        UserAndRoom userAndRoom = UserAndRoom.of(ownerId, roomId);
        ScatterMoney scatterMoney = this.findByToken(token);

        validateService.validateOwner(userAndRoom, scatterMoney.getOwnerId());
        validateService.validatePeriod(scatterMoney.getCreatedDate());

        PickedUpMoneys pickedUpMoneyInfo = pickedUpMoneyService.findAllByScatterMoneyId(scatterMoney.getId());

        return ScatterMoneyDto.valueOf(scatterMoney, pickedUpMoneyInfo);
    }

    /** * 유효하지 않은 token : 해당 token 에 대한 뿌리기 건이 없다. */
    private ScatterMoney findByToken(final String token) {
        return scatterRepository.findByToken(token)
                .orElseThrow(NotExistValueException::new);
    }
}
