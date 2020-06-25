package com.kkaopay.money.scatter.controller;

import com.kkaopay.money.scatter.service.ScatterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiScatterController {

    private final ScatterService scatterService;
}
