package com.interbank.periferiait.springrestexchangerate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExchangeRate {
    private String id;
    private String currencyFrom;
    private String currencyTo;
    private LocalDate date;
    private Double amount;

    public Double convert(Double amountToConvert){
        BigDecimal value = BigDecimal.valueOf(amountToConvert).multiply(BigDecimal.valueOf(amount));
        return value.doubleValue();
    }
}
