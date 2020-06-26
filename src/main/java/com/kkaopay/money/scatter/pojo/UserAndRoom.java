package com.kkaopay.money.scatter.pojo;

import com.kkaopay.money.scatter.error.exception.NoRequiredHeaderException;

import java.util.Map;

public class UserAndRoom {

    public static final String USER_IDENTIFIER_HEADER_NAME = "x-user-id";
    public static final String ROOM_IDENTIFIER_HEADER_NAME = "x-room-id";

    private final Long ownerId;
    private final String roomId;

    private UserAndRoom(final Long ownerId, final String roomId) {
        this.ownerId = ownerId;
        this.roomId = roomId;
    }

    public static UserAndRoom of(final Map<String, Object> headers) {
        Long ownerId = Long.parseLong(getValueOrException(headers, USER_IDENTIFIER_HEADER_NAME).toString());
        String roomId = (String) getValueOrException(headers, ROOM_IDENTIFIER_HEADER_NAME);

        return new UserAndRoom(ownerId, roomId);
    }

    private static Object getValueOrException(final Map<String, Object> headers, final String headerName) {
        if (headers.containsKey(headerName)) {
            return headers.get(headerName);
        }
        throw new NoRequiredHeaderException();
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getRoomId() {
        return roomId;
    }
}
