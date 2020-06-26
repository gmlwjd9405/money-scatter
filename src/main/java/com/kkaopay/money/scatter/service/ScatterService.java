package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import com.kkaopay.money.scatter.domain.ScatterMoney;
import com.kkaopay.money.scatter.dto.request.ScatterMoneyRequestDto;
import com.kkaopay.money.scatter.dto.response.ScatterMoneyDto;
import com.kkaopay.money.scatter.error.exception.NotExistValueException;
import com.kkaopay.money.scatter.error.exception.UnAuthorizationException;
import com.kkaopay.money.scatter.pojo.PickedUpMoneys;
import com.kkaopay.money.scatter.pojo.UserAndRoom;
import com.kkaopay.money.scatter.repository.ScatterRepository;
import com.kkaopay.money.scatter.token.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public BigDecimal receive(final Map<String, Object> headers, final String token) {
        UserAndRoom userAndRoom = UserAndRoom.of(headers);
        ScatterMoney scatterMoney = this.findByToken(token);

        validateIsNotOwnerAndSameRoom(userAndRoom, scatterMoney);

        PickedUpMoneys pickedUpMoneyInfo = pickedUpMoneyService.findAllByScatterMoneyId(scatterMoney.getId());

        validateExistPickedUpMoney(scatterMoney.getPersonnel(), pickedUpMoneyInfo.size());
        validateDuplicatedReceiver(userAndRoom.getOwnerId(), pickedUpMoneyInfo.getPickedUpMoneys());

        PickedUpMoney newPickedUpMoney = pickedUpMoneyInfo.distributeMoney(userAndRoom.getOwnerId(), scatterMoney);
        pickedUpMoneyService.insert(newPickedUpMoney);

        return newPickedUpMoney.getMoney();
    }

    /**
     * 뿌린 사람은 받을 수 없습니다.
     * 뿌리기가 호출된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다.
     */
    private void validateIsNotOwnerAndSameRoom(final UserAndRoom userAndRoom, final ScatterMoney scatterMoney) {
        if (userAndRoom.isSameOwnerId(scatterMoney.getOwnerId())) {
            throw new RuntimeException("뿌린 사람은 받을 수 없습니다.");
        }
        if (userAndRoom.isNotSameRoomId(scatterMoney.getRoomId())) {
            throw new RuntimeException("뿌리기가 호출된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다.");
        }
    }

    /** * 할당된 분배건의 수와 지정한 인원의 수가 같으면 모두 할당된 것으로, 더이상 할당할 수 없다. */
    private void validateExistPickedUpMoney(final int personnel, final int pickedUpMoneyCount) {
        if (personnel == pickedUpMoneyCount) {
            throw new RuntimeException("모든 분배건 할당되어 돈을 받을 수 없습니다.");
        }
    }

    /** * 분배 건에 요청한 사용자 id가 있으면 받을 수 없다. */
    private void validateDuplicatedReceiver(long ownerId, List<PickedUpMoney> pickedUpMoneyInfo) {
        final boolean isExistUser = pickedUpMoneyInfo.stream()
                .anyMatch(pickedUpMoney -> pickedUpMoney.isSameUser(ownerId));

        if (isExistUser) {
            throw new RuntimeException("뿌리기 당한 사용자는 한번만 받을 수 있습니다.");
        }
    }

    public ScatterMoneyDto show(final Map<String, Object> headers, final String token) {
        UserAndRoom userAndRoom = UserAndRoom.of(headers);
        ScatterMoney scatterMoney = this.findByToken(token);

        validateOwner(userAndRoom, scatterMoney.getOwnerId());

        PickedUpMoneys pickedUpMoneyInfo = pickedUpMoneyService.findAllByScatterMoneyId(scatterMoney.getId());

        return ScatterMoneyDto.valueOf(scatterMoney, pickedUpMoneyInfo);
    }

    /** * 유효하지 않은 token : 해당 token 에 대한 뿌리기 건이 없다. */
    private ScatterMoney findByToken(final String token) {
        return scatterRepository.findByToken(token)
                .orElseThrow(NotExistValueException::new);
    }

    /** * 뿌린 사람 자신만 조회를 할 수 있습니다. */
    private void validateOwner(final UserAndRoom userAndRoom, final Long ownerId) {
        if (!userAndRoom.isSameOwnerId(ownerId)) {
            throw new UnAuthorizationException();
        }
    }
}
