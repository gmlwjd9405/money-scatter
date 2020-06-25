package com.kkaopay.money.scatter.controller;

import com.kkaopay.money.scatter.dto.ScatterMoneyRequestDto;
import com.kkaopay.money.scatter.service.ScatterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ApiScatterController {

    private final ScatterService scatterService;

    @PostMapping("/money/scatter")
    public ResponseEntity<String> saveScatterMoney(@RequestHeader Map<String, Object> headers,
                                                   @RequestBody ScatterMoneyRequestDto dto){
        String token;

        try {
            token = scatterService.saveScatterMoney(headers, dto);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        return ResponseEntity.ok()
                .body(token);
    }
}
