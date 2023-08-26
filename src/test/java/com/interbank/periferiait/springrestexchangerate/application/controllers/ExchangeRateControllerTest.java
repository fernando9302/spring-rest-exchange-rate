package com.interbank.periferiait.springrestexchangerate.application.controllers;

import com.interbank.periferiait.springrestexchangerate.application.request.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.ConversionCurrencyResponse;
import com.interbank.periferiait.springrestexchangerate.application.services.ExchangeRateService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = ExchangeRateController.class)
@EnableWebMvc
class ExchangeRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Test
    void testExchangeRateController() throws Exception {
        String currencyFrom = "PEN";
        String currencyTo = "USD";
        String amount = "120.50";
        String method = "GET";
        String endpoint = String.format("/api/v1/exchange-rate/convert?currencyFrom=%s&currencyTo=%s&amount=%s", currencyFrom, currencyTo, amount);
        Integer expectedStatusCode = 200;

        Mockito.when(exchangeRateService.convertAmount(new ConversionCurrencyRequest(currencyFrom, currencyTo, amount))).thenReturn(new ConversionCurrencyResponse(120.50, 447.2776, "PEN", "USD", 3.71));

        mockMvc.perform(request(HttpMethod.valueOf(method), endpoint))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(jsonPath("$.response", Matchers.notNullValue()));
    }
}