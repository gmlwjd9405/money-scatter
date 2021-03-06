package com.kkaopay.money.scatter.error;

public class ErrorMessage {

    // common
    public static final String NO_REQUIRED_HEADER_INFO = "필수 헤더 정보가 없습니다.";
    public static final String IS_NULL_VALUE = "입력 값이 null 값 입니다.";
    public static final String INVALID_INPUT_VALUE = "유효하지 않은 입력값입니다.";

    // 받기 API
    public static final String CANNOT_RECEIVE_BECAUSE_OWNER = "뿌린 사람은 받을 수 없습니다.";
    public static final String REQUIRED_SAME_ROOM = "뿌리기가 호출된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다.";
    public static final String CANNOT_RECEIVE_BECAUSE_ALL_ALLOCATED = "모든 분배 건이 할당되어 돈을 받을 수 없습니다.";
    public static final String USER_IS_ALREADY_ALLOCATED = "뿌리기 당한 사용자는 한번만 받을 수 있습니다.";
    public static final String EXPIRED_SCATTER_MONEY = "뿌리기 머니가 만료되었습니다. (뿌린 건은 10분간만 유효)";

    // 조회 API
    public static final String UN_AUTHORIZATION = "권한이 없습니다.";
    public static final String NOT_EXIST_VALUE = "해당하는 데이터가 없습니다.";
    public static final String EXPIRED_INQUIRY_PERIOD = "뿌린 건에 대한 조회는 생성 후 7일 동안만 할 수 있습니다.";

    private ErrorMessage() {
    }
}
