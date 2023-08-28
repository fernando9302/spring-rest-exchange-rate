package com.interbank.periferiait.springrestexchangerate.application.controllers.exchangerate.changevalueexchangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ExchangeRateRequest;
import com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.changevalueexchangerate.ExchangeRateChangeValueService;
import com.interbank.periferiait.springrestexchangerate.infraestructure.generic.GenericResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exchange-rate")
@Tag(name = "Change Value Exchange Rate", description = "Change value of currency")
public class PostExchangeRateChangeValueController {

    @Autowired
    private ExchangeRateChangeValueService exchangeRateChangeValueService;

    @PostMapping("/change-value")
    public ResponseEntity<GenericResponse> save(@RequestBody ExchangeRateRequest exchangeRateRequest) throws Exception {
        exchangeRateChangeValueService.saveExchangeRate(exchangeRateRequest);
        return ResponseEntity.ok(new GenericResponse());
    }
}
