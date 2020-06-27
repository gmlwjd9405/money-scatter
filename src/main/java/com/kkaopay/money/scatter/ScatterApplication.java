package com.kkaopay.money.scatter;

import com.kkaopay.money.scatter.domain.entity.ScatterMoney;
import com.kkaopay.money.scatter.domain.repository.ScatterRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@SpringBootApplication
public class ScatterApplication implements ApplicationRunner {

    private final ScatterRepository scatterRepository;

    public static void main(String[] args) {
        SpringApplication.run(ScatterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        scatterRepository.save(ScatterMoney.builder()
                .token("Sob")
                .ownerId((long) 111)
                .roomId("room")
                .money(BigDecimal.valueOf(10000))
                .personnel(3)
                .createdDate(LocalDateTime.now())
                .isExpired(false)
                .build());
        scatterRepository.save(ScatterMoney.builder()
                .token("shV")
                .ownerId((long) 222)
                .roomId("room")
                .money(BigDecimal.valueOf(9000))
                .personnel(3)
                .createdDate(LocalDateTime.now())
                .isExpired(false)
                .build());
        scatterRepository.save(ScatterMoney.builder()
                .token("abc")
                .ownerId((long) 222)
                .roomId("roomId2")
                .money(BigDecimal.valueOf(15000))
                .personnel(7)
                .createdDate(LocalDateTime.now().minusDays(8))
                .isExpired(false)
                .build());
    }
}
