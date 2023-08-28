package com.interbank.periferiait.springrestexchangerate.application.controllers.exchangerate.convertexhangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.exchangerate.ConversionCurrencyResponse;
import com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.convertexhangerate.ExchangeRateConvertService;
import com.interbank.periferiait.springrestexchangerate.infraestructure.generic.GenericResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange-rate")
@Tag(name = "Convert Exchange Rate", description = "Convert currency")
public class GetExchangeRateConvertController {

    @Autowired
    private ExchangeRateConvertService exchangeRateConvertService;

    @GetMapping("/convert")
    public ResponseEntity<GenericResponse<ConversionCurrencyResponse>> convert(@RequestParam("currencyFrom") String currencyFrom, @RequestParam("currencyTo") String currencyTo, @RequestParam("amount") String amount) throws Exception {
        ConversionCurrencyResponse response = exchangeRateConvertService.convertAmount(new ConversionCurrencyRequest(currencyFrom, currencyTo, amount));
        return ResponseEntity.ok(new GenericResponse<>(response));
    }
}
