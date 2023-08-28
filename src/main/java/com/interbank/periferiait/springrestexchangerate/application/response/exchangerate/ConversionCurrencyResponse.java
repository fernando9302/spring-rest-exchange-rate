package com.interbank.periferiait.springrestexchangerate.application.response.exchangerate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversionCurrencyResponse {
    private Double amount;
    private Double convertedAmount;
    private String currencyFrom;
    private String currencyTo;
    private Double exchangeRateAmount;
}
