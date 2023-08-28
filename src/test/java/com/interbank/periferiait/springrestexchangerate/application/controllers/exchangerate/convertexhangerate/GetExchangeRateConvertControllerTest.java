package com.interbank.periferiait.springrestexchangerate.application.controllers.exchangerate.convertexhangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.exchangerate.ConversionCurrencyResponse;
import com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.convertexhangerate.ExchangeRateConvertService;
import com.interbank.periferiait.springrestexchangerate.infraestructure.security.filter.JwtAuthenticationFilter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = GetExchangeRateConvertController.class)
@EnableWebMvc
class GetExchangeRateConvertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateConvertService exchangeRateConvertService;


    @Test
    void testExchangeRateController() throws Exception {
        String currencyFrom = "PEN";
        String currencyTo = "USD";
        String amount = "120.50";
        String endpoint = String.format("/api/v1/exchange-rate/convert?currencyFrom=%s&currencyTo=%s&amount=%s", currencyFrom, currencyTo, amount);

        Mockito.when(exchangeRateConvertService.convertAmount(new ConversionCurrencyRequest(currencyFrom, currencyTo, amount))).thenReturn(new ConversionCurrencyResponse(120.50, 447.2776, "PEN", "USD", 3.71));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(endpoint)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY5MDMxMjQ1NCwiZXhwIjoyMDA1OTMxNjU0fQ.HONb6lg05UAbl-UXILys0YVsgLJYjciH3vMqJpQqthE")
                        .content(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()));
    }
}