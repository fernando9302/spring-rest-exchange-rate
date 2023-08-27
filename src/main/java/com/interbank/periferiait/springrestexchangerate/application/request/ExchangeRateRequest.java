package com.interbank.periferiait.springrestexchangerate.application.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class ExchangeRateRequest {

    @NotNull(message = "Currency From should not be null")
    @NotEmpty(message = "Currency From should not be empty")
    private String currencyFrom;

    @NotNull(message = "Currency To should not be null")
    @NotEmpty(message = "Currency To should not be empty")
    private String currencyTo;

    @NotNull(message = "Date should not be null")
    @NotEmpty(message = "Date should not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull(message = "Amount should not be null")
    @NotEmpty(message = "Amount should not be empty")
    private Double amount;
}
