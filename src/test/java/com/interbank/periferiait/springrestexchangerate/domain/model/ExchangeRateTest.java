package com.interbank.periferiait.springrestexchangerate.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Exchange Rate Test")
class ExchangeRateTest {

    @Test
    void testConversion(){
        Double amountToConvert = 120.56;
       ExchangeRate exchangeRate = new ExchangeRate(UUID.randomUUID().toString(), "PEN", "USD", LocalDate.now(), 3.71);
       assertEquals(447.2776, exchangeRate.convert(amountToConvert));
    }
}