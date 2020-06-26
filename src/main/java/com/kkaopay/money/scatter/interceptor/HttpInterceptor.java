package com.kkaopay.money.scatter.interceptor;

import com.kkaopay.money.scatter.error.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {

    public static final String USER_IDENTIFIER_HEADER_NAME = "x-user-id";
    public static final String ROOM_IDENTIFIER_HEADER_NAME = "x-room-id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        boolean isEmptyUserId = isEmptyHeader(request.getHeader(USER_IDENTIFIER_HEADER_NAME));
        boolean isEmptyRoomId = isEmptyHeader(request.getHeader(ROOM_IDENTIFIER_HEADER_NAME));

        if (isEmptyUserId || isEmptyRoomId) {
            response.getWriter().write(ErrorMessage.NO_REQUIRED_HEADER_INFO);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpStatus.BAD_REQUEST.value());

            return false;
        }
        return true;
    }

    private boolean isEmptyHeader(final String requiredHeader) {
        return StringUtils.isEmpty(requiredHeader);
    }
}
