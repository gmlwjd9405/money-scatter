package com.kkaopay.money.scatter.controller;

import com.kkaopay.money.scatter.dto.request.ScatterMoneyRequestDto;
import com.kkaopay.money.scatter.dto.response.ScatterMoneyDto;
import com.kkaopay.money.scatter.service.ScatterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ApiScatterController {

    public static final String USER_IDENTIFIER_HEADER_NAME = "X-USER-ID";
    public static final String ROOM_IDENTIFIER_HEADER_NAME = "X-ROOM-ID";
    public static final String TOKEN_HEADER_NAME = "X-TOKEN-VALUE";

    private final ScatterService scatterService;

    @ExceptionHandler(value = NullPointerException.class)
    @PostMapping("/money/scatter")
    public ResponseEntity<String> scatter(@RequestHeader(USER_IDENTIFIER_HEADER_NAME) final Long userId,
                                          @RequestHeader(ROOM_IDENTIFIER_HEADER_NAME) final String roomId,
                                          @RequestBody ScatterMoneyRequestDto dto) {
        final String token = scatterService.scatter(userId, roomId, dto);

        return ResponseEntity.ok().body(token);
    }

    @PatchMapping("/money/scatter")
    public ResponseEntity<BigDecimal> receive(@RequestHeader(USER_IDENTIFIER_HEADER_NAME) final Long userId,
                                     @RequestHeader(ROOM_IDENTIFIER_HEADER_NAME) final String roomId,
                                     @RequestHeader(TOKEN_HEADER_NAME) String token) {
        final BigDecimal money = scatterService.receive(userId, roomId, token);

        return ResponseEntity.ok().body(money);
    }

    @GetMapping("/money/scatter")
    public ResponseEntity<ScatterMoneyDto> show(@RequestHeader(USER_IDENTIFIER_HEADER_NAME) final Long userId,
                                  @RequestHeader(ROOM_IDENTIFIER_HEADER_NAME) final String roomId,
                                  @RequestHeader(TOKEN_HEADER_NAME) String token) {
        final ScatterMoneyDto responseDto = scatterService.show(userId, roomId, token);

        return ResponseEntity.ok().body(responseDto);
    }
}
