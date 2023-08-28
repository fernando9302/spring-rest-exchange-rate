package com.interbank.periferiait.springrestexchangerate.application.request.exchangerate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversionCurrencyRequest {
    private String currencyFrom;
    private String currencyTo;
    private String amount;
}
