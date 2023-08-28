package com.interbank.periferiait.springrestexchangerate.application.controllers.exchangerate.changevalueexchangerate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interbank.periferiait.springrestexchangerate.application.controllers.exchangerate.convertexhangerate.GetExchangeRateConvertController;
import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ExchangeRateRequest;
import com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.changevalueexchangerate.ExchangeRateChangeValueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = PostExchangeRateChangeValueController.class)
@EnableWebMvc
class PostExchangeRateChangeValueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private ExchangeRateChangeValueService exchangeRateChangeValueService;

    @BeforeEach
    void init(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveExchangeRateController() throws Exception {
        String endpoint = "/api/v1/exchange-rate/change-value";
        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest("EUR", "PIK", "27/08/2023", 10.50);
        Mockito.doNothing().when(exchangeRateChangeValueService).saveExchangeRate(any());
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exchangeRateRequest))
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY5MDMxMjQ1NCwiZXhwIjoyMDA1OTMxNjU0fQ.HONb6lg05UAbl-UXILys0YVsgLJYjciH3vMqJpQqthE"))
                .andExpect(status().isOk());

    }
}