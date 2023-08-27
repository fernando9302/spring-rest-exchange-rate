package com.interbank.periferiait.springrestexchangerate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ExchangeRate {
    private String id;
    private Currency currencyFrom;
    private Currency currencyTo;
    private LocalDate date;
    private Double amount;

    public Double convert(Double amountToConvert){
        BigDecimal value = BigDecimal.valueOf(amountToConvert).multiply(BigDecimal.valueOf(amount));
        return value.doubleValue();
    }

    public static ExchangeRate created(Currency currencyFrom, Currency currencyTo, LocalDate date, Double amount){
        return new ExchangeRate(UUID.randomUUID().toString(), currencyFrom, currencyTo, date, amount);
    }

    public static ExchangeRate updatedAmount(ExchangeRate exchangeRate, Double amount){
        exchangeRate.setAmount(amount);
        return exchangeRate;
    }
}
