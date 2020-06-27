package com.kkaopay.money.scatter.pojo;

import com.kkaopay.money.scatter.error.exception.NoRequiredHeaderException;
import lombok.Getter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;

@Getter
public class UserAndRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String USER_IDENTIFIER_HEADER_NAME = "x-user-id";
    public static final String ROOM_IDENTIFIER_HEADER_NAME = "x-room-id";

    private final Long ownerId;
    private final String roomId;

    private UserAndRoom(final Long ownerId, final String roomId) {
        validate(ownerId, roomId);

        this.ownerId = ownerId;
        this.roomId = roomId;
    }

    public static UserAndRoom of(final Map<String, Object> headers) {
        validateHeader(headers);

        Long ownerId = Long.parseLong(headers.get(USER_IDENTIFIER_HEADER_NAME).toString());
        String roomId = (String) headers.get(ROOM_IDENTIFIER_HEADER_NAME);

        return new UserAndRoom(ownerId, roomId);
    }

    private void validate(final Long ownerId, final String roomId) {
        if (ObjectUtils.isEmpty(ownerId) || StringUtils.isEmpty(roomId)) {
            throw new NoRequiredHeaderException();
        }
    }

    private static void validateHeader(final Map<String, Object> headers) {
        if (CollectionUtils.isEmpty(headers)) {
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
