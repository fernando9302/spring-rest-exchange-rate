package com.interbank.periferiait.springrestexchangerate.application.controllers;

import com.interbank.periferiait.springrestexchangerate.application.request.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.ConversionCurrencyResponse;
import com.interbank.periferiait.springrestexchangerate.infraestructure.generic.GenericResponse;
import com.interbank.periferiait.springrestexchangerate.application.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange-rate")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/convert")
    public ResponseEntity<GenericResponse<ConversionCurrencyResponse>> convert(@RequestParam("currencyFrom") String currencyFrom, @RequestParam("currencyTo") String currencyTo, @RequestParam("amount") String amount) throws Exception {
        ConversionCurrencyResponse response = exchangeRateService.convertAmount(new ConversionCurrencyRequest(currencyFrom, currencyTo, amount));
        return ResponseEntity.ok(new GenericResponse<>(response));
    }
}
