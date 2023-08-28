package com.interbank.periferiait.springrestexchangerate.application.request.exchangerate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRequest {

    @NotNull(message = "Currency From should not be null")
    @NotEmpty(message = "Currency From should not be empty")
    private String currencyFrom;

    @NotNull(message = "Currency To should not be null")
    @NotEmpty(message = "Currency To should not be empty")
    private String currencyTo;

    @NotNull(message = "Date should not be null")
    @NotEmpty(message = "Date should not be empty")
    private String date;

    @NotNull(message = "Amount should not be null")
    @NotEmpty(message = "Amount should not be empty")
    private Double amount;
}
