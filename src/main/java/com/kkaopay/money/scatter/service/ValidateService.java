package com.kkaopay.money.scatter.service;

import com.kkaopay.money.scatter.domain.PickedUpMoney;
import com.kkaopay.money.scatter.domain.ScatterMoney;
import com.kkaopay.money.scatter.error.ErrorCode;
import com.kkaopay.money.scatter.error.exception.NotExistValueException;
import com.kkaopay.money.scatter.error.exception.UnAuthorizationException;
import com.kkaopay.money.scatter.pojo.UserAndRoom;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValidateService {

    private static final long EXPIRED_DAYS = 7;

    /**
     * 뿌린 사람은 받을 수 없습니다.
     * 뿌리기가 호출된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다.
     */
    public void validateIsNotOwnerAndSameRoom(final UserAndRoom userAndRoom, final ScatterMoney scatterMoney) {
        if (userAndRoom.isSameOwnerId(scatterMoney.getOwnerId())) {
            throw new UnAuthorizationException(ErrorCode.CANNOT_RECEIVE_BECAUSE_OWNER);
        }
        if (userAndRoom.isNotSameRoomId(scatterMoney.getRoomId())) {
            throw new UnAuthorizationException(ErrorCode.REQUIRED_SAME_ROOM);
        }
    }

    /** * 할당된 분배건의 수와 지정한 인원의 수가 같으면 모두 할당된 것으로, 더이상 할당할 수 없다. */
    public void validateExistPickedUpMoney(final int personnel, final int pickedUpMoneyCount) {
        if (personnel == pickedUpMoneyCount) {
            throw new NotExistValueException(ErrorCode.CANNOT_RECEIVE_BECAUSE_ALL_ALLOCATED);
        }
    }

    /** * 분배 건에 요청한 사용자 id가 있으면 받을 수 없다. */
    public void validateDuplicatedReceiver(long ownerId, List<PickedUpMoney> pickedUpMoneyInfo) {
        final boolean isExistUser = pickedUpMoneyInfo.stream()
                .anyMatch(pickedUpMoney -> pickedUpMoney.isSameUser(ownerId));

        if (isExistUser) {
            throw new UnAuthorizationException(ErrorCode.USER_IS_ALREADY_ALLOCATED);
        }
    }

    /** * 뿌린 사람 자신만 조회를 할 수 있습니다. */
    public void validateOwner(final UserAndRoom userAndRoom, final Long ownerId) {
        if (!userAndRoom.isSameOwnerId(ownerId)) {
            throw new UnAuthorizationException();
        }
    }

    /** * 뿌린 건에 대한 조회는 7일 동안 할 수 있습니다. */
    public void validatePeriod(final LocalDateTime createdDate) {
        if (LocalDateTime.now().minusDays(EXPIRED_DAYS).isAfter(createdDate)) {
            throw new NotExistValueException(ErrorCode.EXPIRED_INQUIRY_PERIOD);
        }
    }
}
