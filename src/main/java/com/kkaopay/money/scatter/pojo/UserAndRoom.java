package com.kkaopay.money.scatter.pojo;

import com.kkaopay.money.scatter.error.exception.NoRequiredHeaderException;
import lombok.Getter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Getter
public class UserAndRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long ownerId;
    private final String roomId;

    private UserAndRoom(final Long ownerId, final String roomId) {
        validate(ownerId, roomId);

        this.ownerId = ownerId;
        this.roomId = roomId;
    }

    public static UserAndRoom of(final Long ownerId, final String roomId) {
        return new UserAndRoom(ownerId, roomId);
    }

    private void validate(final Long ownerId, final String roomId) {
        if (ObjectUtils.isEmpty(ownerId) || StringUtils.isEmpty(roomId)) {
            throw new NoRequiredHeaderException();
        }
    }

    public boolean isSameOwnerId(final Long ownerId) {
        return this.ownerId.equals(ownerId);
    }

    public boolean isNotSameRoomId(final String roomId) {
        return !this.roomId.equals(roomId);
    }
}
