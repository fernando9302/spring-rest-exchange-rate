package com.interbank.periferiait.springrestexchangerate.application.services;

import com.interbank.periferiait.springrestexchangerate.application.request.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.ConversionCurrencyResponse;
import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import com.interbank.periferiait.springrestexchangerate.domain.repository.currency.CurrencyRepository;
import com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate.ExchangeRateRepository;
import com.interbank.periferiait.springrestexchangerate.infraestructure.exception.BusinessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public ConversionCurrencyResponse convertAmount(ConversionCurrencyRequest conversionCurrencyRequest) throws Exception {
        validCurrencyExist(conversionCurrencyRequest);
        ExchangeRate exchangeRate = getExchangeRate(conversionCurrencyRequest.getCurrencyFrom(), conversionCurrencyRequest.getCurrencyTo());
        Double amount = getAmount(conversionCurrencyRequest.getAmount());
        Double amountConverted = exchangeRate.convert(amount);
        return new ConversionCurrencyResponse(amount, amountConverted, exchangeRate.getCurrencyFrom(), exchangeRate.getCurrencyTo(), exchangeRate.getAmount().doubleValue());
    }

    private void validCurrencyExist(ConversionCurrencyRequest conversionCurrencyRequest) throws Exception {
        validateIfCurrencyExists(conversionCurrencyRequest.getCurrencyFrom());
        validateIfCurrencyExists(conversionCurrencyRequest.getCurrencyTo());
    }

    private void validateIfCurrencyExists(String currency) {
        if(currencyRepository.getByCode(currency).isEmpty())
            throw new BusinessValidationException(String.format("Currency %s doesn't exist", currency));
    }

    private ExchangeRate getExchangeRate(String currencyFrom, String currencyTo){
        Optional<ExchangeRate> exchangeRate  = exchangeRateRepository.getByCurrencyFromAndCurrencyToAndDate(currencyFrom, currencyTo, LocalDate.now());
        if(exchangeRate.isEmpty())
            throw new BusinessValidationException("Exchange rate doesn't exist");
        return exchangeRate.get();
    }

    private Double getAmount(String amount){
        try {
            return Double.parseDouble(amount);
        }catch (NumberFormatException e){
            throw new BusinessValidationException("Amount is not valid");
        }
    }
}
