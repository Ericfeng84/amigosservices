package com.EricFeng.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudService {

    private final FraudRepository fraudRepository;

    public boolean isFraud (Integer customerId) {
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId).
                        isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;

    }
}
