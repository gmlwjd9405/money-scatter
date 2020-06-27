package com.kkaopay.money.scatter.controller;

import com.kkaopay.money.scatter.dto.request.ScatterMoneyRequestDto;
import com.kkaopay.money.scatter.dto.response.ScatterMoneyDto;
import com.kkaopay.money.scatter.error.exception.UnAuthorizationException;
import com.kkaopay.money.scatter.service.ScatterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/money/scatter")
    public ResponseEntity<String> scatter(@RequestHeader(USER_IDENTIFIER_HEADER_NAME) final Long userId,
                                          @RequestHeader(ROOM_IDENTIFIER_HEADER_NAME) final String roomId,
                                          @RequestBody ScatterMoneyRequestDto dto) {
        String token;

        try {
            token = scatterService.scatter(userId, roomId, dto);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok().body(token);
    }

    @PatchMapping("/money/scatter")
    public ResponseEntity<?> receive(@RequestHeader(USER_IDENTIFIER_HEADER_NAME) final Long userId,
                                     @RequestHeader(ROOM_IDENTIFIER_HEADER_NAME) final String roomId,
                                     @RequestHeader(TOKEN_HEADER_NAME) String token) {
        BigDecimal money;

        try {
            money = scatterService.receive(userId, roomId, token);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok().body(money);
    }

    @GetMapping("/money/scatter")
    public ResponseEntity<?> show(@RequestHeader(USER_IDENTIFIER_HEADER_NAME) final Long userId,
                                  @RequestHeader(ROOM_IDENTIFIER_HEADER_NAME) final String roomId,
                                  @RequestHeader(TOKEN_HEADER_NAME) String token) {
        ScatterMoneyDto responseDto;

        try {
            responseDto = scatterService.show(userId, roomId, token);
        } catch (UnAuthorizationException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body(responseDto);
    }
}
