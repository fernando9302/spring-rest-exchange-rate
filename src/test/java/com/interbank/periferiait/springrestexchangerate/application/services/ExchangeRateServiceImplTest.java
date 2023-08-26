package com.interbank.periferiait.springrestexchangerate.application.services;

import com.interbank.periferiait.springrestexchangerate.application.request.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.ConversionCurrencyResponse;
import com.interbank.periferiait.springrestexchangerate.domain.model.Currency;
import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import com.interbank.periferiait.springrestexchangerate.domain.repository.currency.CurrencyRepository;
import com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate.ExchangeRateRepository;
import com.interbank.periferiait.springrestexchangerate.infraestructure.exception.BusinessValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Exchange Rate Service")
class ExchangeRateServiceImplTest {

    @MockBean
    private ExchangeRateRepository exchangeRateRepository;

    @MockBean
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        Currency pen = new Currency(UUID.randomUUID().toString(), "PEN", "SOLES");
        Currency usd = new Currency(UUID.randomUUID().toString(), "USD", "DOLLARS");
        Double exchangeRateAmount = 3.71;
        LocalDate date = LocalDate.now().minusDays(1);
        Mockito.when(currencyRepository.getByCode("PEN")).thenReturn(Optional.of(pen));
        Mockito.when(currencyRepository.getByCode("USD")).thenReturn(Optional.of(usd));
        Mockito.when(exchangeRateRepository.getByCurrencyFromAndCurrencyToAndDate(pen.getCode(), usd.getCode(), date)).thenReturn(Optional.of(new ExchangeRate(UUID.randomUUID().toString(), pen.getCode(), usd.getCode(), date, exchangeRateAmount)));
    }

    @Test
    void testConvertWithCurrencyFromDoesNotExist(){
        String currencyFrom = "EUR";
        String currencyTo = "USD";
        String amount = "120.56";
        String currencyType = "From";
        ConversionCurrencyRequest conversionCurrencyRequest = new ConversionCurrencyRequest(currencyFrom, currencyTo, amount);
        BusinessValidationException error = assertThrows(BusinessValidationException.class, ()->exchangeRateService.convertAmount(conversionCurrencyRequest));
        assertEquals(String.format("Currency %s %s doesn't exist", currencyType, currencyFrom), error.getMessage());
    }

    @Test
    void testConvertWithCurrencyToDoesNotExist(){
        String currencyFrom = "PEN";
        String currencyTo = "EUR";
        String amount = "120.56";
        String currencyType = "To";
        ConversionCurrencyRequest conversionCurrencyRequest = new ConversionCurrencyRequest(currencyFrom, currencyTo, amount);
        BusinessValidationException error = assertThrows(BusinessValidationException.class, ()->exchangeRateService.convertAmount(conversionCurrencyRequest));
        assertEquals(String.format("Currency %s %s doesn't exist", currencyType, currencyTo ), error.getMessage());
    }

    @Test
    void testConvertWithExchangeRateDoesNotExist(){
        String currencyFrom = "PEN";
        String currencyTo = "USD";
        String amount = "120.56";
        ConversionCurrencyRequest conversionCurrencyRequest = new ConversionCurrencyRequest(currencyFrom, currencyTo, amount);
        BusinessValidationException error = assertThrows(BusinessValidationException.class, ()->exchangeRateService.convertAmount(conversionCurrencyRequest));
        assertEquals("Exchange rate doesn't exist", error.getMessage());
    }

    @Test
    void testConvertOk() throws Exception {
        String currencyFrom = "PEN";
        String currencyTo = "USD";
        String amount = "120.56";
        Double convertedAmount = 447.2776;
        Double exchangeRateAmount = 3.71;
        LocalDate date = LocalDate.now();
        ConversionCurrencyRequest conversionCurrencyRequest = new ConversionCurrencyRequest(currencyFrom, currencyTo, amount);
        Mockito.when(exchangeRateRepository.getByCurrencyFromAndCurrencyToAndDate(currencyFrom, currencyTo, date)).thenReturn(Optional.of(new ExchangeRate(UUID.randomUUID().toString(), currencyFrom, currencyTo, date, exchangeRateAmount)));
        ConversionCurrencyResponse conversionCurrencyResponse = exchangeRateService.convertAmount(conversionCurrencyRequest);
        ConversionCurrencyResponse expectedConversionCurrencyResponse = new ConversionCurrencyResponse(Double.parseDouble(amount), convertedAmount , currencyFrom, currencyTo, exchangeRateAmount);
        assertNotNull(conversionCurrencyResponse);
        assertEquals(expectedConversionCurrencyResponse, conversionCurrencyResponse);
    }
}