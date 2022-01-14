package com.mycompany.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponce isFraudster(@PathVariable Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("isFraudster? - {}", isFraudulentCustomer);
        return new FraudCheckResponce(isFraudulentCustomer);
    }
}
