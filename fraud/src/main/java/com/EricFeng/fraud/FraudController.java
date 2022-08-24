package com.EricFeng.fraud;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(".api/v1/fraud")

public record FraudController(FraudService fraudService) {

    @GetMapping(path = "{customerId}")
    public CheckFraudResponse CheckFraudHistory(@PathVariable("customerId") Integer customerId){
        boolean IsFraud = fraudService.isFraud(customerId);
        log.info("Check Fraud {}",customerId)
        return new CheckFraudResponse(IsFraud);
    };
}
