package com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.changevalueexchangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ExchangeRateRequest;
import com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.changevalueexchangerate.ExchangeRateChangeValueServiceImpl;
import com.interbank.periferiait.springrestexchangerate.domain.exception.BusinessValidationException;
import com.interbank.periferiait.springrestexchangerate.domain.model.Currency;
import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import com.interbank.periferiait.springrestexchangerate.domain.repository.currency.CurrencyRepository;
import com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate.ExchangeRateRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@DisplayName("Exchange Rate Change Value Service")
class ExchangeRateChangeValueServiceImplTest {

    @MockBean
    private ExchangeRateRepository exchangeRateRepository;

    @MockBean
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private ExchangeRateChangeValueServiceImpl exchangeRateService;

    private Currency pen;
    private  Currency usd;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        pen = new Currency(UUID.randomUUID().toString(), "PEN", "SOLES");
        usd = new Currency(UUID.randomUUID().toString(), "USD", "DOLLARS");
        Double exchangeRateAmount = 3.71;
        LocalDate date = LocalDate.now().minusDays(1);
        Mockito.when(currencyRepository.getByCode("PEN")).thenReturn(Optional.of(pen));
        Mockito.when(currencyRepository.getByCode("USD")).thenReturn(Optional.of(usd));
        Mockito.when(exchangeRateRepository.getByCurrencyFromAndCurrencyToAndDate(pen.getCode(), usd.getCode(), date)).thenReturn(Optional.of(new ExchangeRate(UUID.randomUUID().toString(), pen, usd, date, exchangeRateAmount)));
    }

    @Test
    void testSaveExchangeRateWithCurrencyFromDoesNotExist(){
        String currencyFrom = "EUR";
        String currencyTo = "USD";
        Double amount = 120.56;
        String currencyType = "From";
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest(currencyFrom, currencyTo, date, amount);
        BusinessValidationException error = assertThrows(BusinessValidationException.class, ()->exchangeRateService.saveExchangeRate(exchangeRateRequest));
        assertEquals(String.format("Currency%s %s doesn't exist", currencyType, currencyFrom), error.getMessage());
    }

    @Test
    void testSaveExchangeRateWithCurrencyToDoesNotExist(){
        String currencyFrom = "PEN";
        String currencyTo = "EUR";
        Double amount = 120.56;
        String currencyType = "To";
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest(currencyFrom, currencyTo, date, amount);
        BusinessValidationException error = assertThrows(BusinessValidationException.class, ()->exchangeRateService.saveExchangeRate(exchangeRateRequest));
        assertEquals(String.format("Currency%s %s doesn't exist", currencyType, currencyTo ), error.getMessage());
    }

    @Test
    void testSaveOk() throws Exception {
        String currencyFrom = "PEN";
        String currencyTo = "USD";
        Double amount = 120.56;
        Double exchangeRateAmount = 3.71;
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest(currencyFrom, currencyTo, date, amount);
        Mockito.when(exchangeRateRepository.getByCurrencyFromAndCurrencyToAndDate(currencyFrom, currencyTo, localDate)).thenReturn(Optional.of(new ExchangeRate(UUID.randomUUID().toString(), pen, usd, localDate, exchangeRateAmount)));
        exchangeRateService.saveExchangeRate(exchangeRateRequest);
        Mockito.verify(exchangeRateRepository, Mockito.times(2)).save(any());
    }
}