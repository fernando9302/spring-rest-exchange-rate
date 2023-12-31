package com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.convertexhangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.exchangerate.ConversionCurrencyResponse;
import com.interbank.periferiait.springrestexchangerate.domain.exception.BusinessValidationException;
import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import com.interbank.periferiait.springrestexchangerate.domain.repository.currency.CurrencyRepository;
import com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeRateConvertServiceImpl implements ExchangeRateConvertService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public ConversionCurrencyResponse convertAmount(ConversionCurrencyRequest conversionCurrencyRequest) throws Exception {
        validCurrencyExist(conversionCurrencyRequest.getCurrencyFrom(), conversionCurrencyRequest.getCurrencyTo());
        ExchangeRate exchangeRate = getExchangeRateWithCurrentDate(conversionCurrencyRequest.getCurrencyFrom(), conversionCurrencyRequest.getCurrencyTo());
        Double amount = getAmount(conversionCurrencyRequest.getAmount());
        Double amountConverted = exchangeRate.convert(amount);
        return new ConversionCurrencyResponse(amount, amountConverted, exchangeRate.getCurrencyFrom().getCode(), exchangeRate.getCurrencyTo().getCode(), exchangeRate.getAmount().doubleValue());
    }

    private Double getAmount(String amount){
        try {
            return Double.parseDouble(amount);
        }catch (NumberFormatException e){
            throw new BusinessValidationException("Amount is not valid");
        }
    }

    private void validCurrencyExist(String currencyFrom, String currencyTo) throws Exception {
        validateIfCurrencyExists(currencyFrom, "From");
        validateIfCurrencyExists(currencyTo, "To");
    }

    private void validateIfCurrencyExists(String currency, String currencyType) {
        if(currencyRepository.getByCode(currency).isEmpty())
            throw new BusinessValidationException(String.format("Currency%s %s doesn't exist", currencyType, currency));
    }

    private ExchangeRate getExchangeRateWithCurrentDate(String currencyFrom, String currencyTo){
        Optional<ExchangeRate> exchangeRate  = exchangeRateRepository.getByCurrencyFromAndCurrencyToAndDate(currencyFrom, currencyTo, LocalDate.now());
        if(exchangeRate.isEmpty())
            throw new BusinessValidationException("Exchange rate doesn't exist");
        return exchangeRate.get();
    }
}
