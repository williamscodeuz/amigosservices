package com.williamscode.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse createFraudCheckHistoty(
            @PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer: {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
