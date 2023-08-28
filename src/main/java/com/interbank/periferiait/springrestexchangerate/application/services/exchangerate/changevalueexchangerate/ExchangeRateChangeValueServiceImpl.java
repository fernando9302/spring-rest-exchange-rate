package com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.changevalueexchangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ExchangeRateRequest;
import com.interbank.periferiait.springrestexchangerate.domain.model.Currency;
import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import com.interbank.periferiait.springrestexchangerate.domain.repository.currency.CurrencyRepository;
import com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate.ExchangeRateRepository;
import com.interbank.periferiait.springrestexchangerate.domain.exception.BusinessValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class ExchangeRateChangeValueServiceImpl implements ExchangeRateChangeValueService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;



    @Override
    public void saveExchangeRate(ExchangeRateRequest exchangeRateRequest) throws Exception {
        save(exchangeRateRequest.getCurrencyFrom(), exchangeRateRequest.getCurrencyTo(), exchangeRateRequest.getDate(), exchangeRateRequest.getAmount());
        save(exchangeRateRequest.getCurrencyTo(), exchangeRateRequest.getCurrencyFrom(), exchangeRateRequest.getDate(), 1/ exchangeRateRequest.getAmount());
    }

    private void save(String currencyFrom, String currencyTo, String date, Double amount) throws Exception {
        LocalDate localDate = getLocalDate(date);
        validCurrencyExist(currencyFrom, currencyTo);
        Optional<ExchangeRate> exchangeRate = getExchangeRate(currencyFrom, currencyTo,  localDate);
        if(exchangeRate.isEmpty())
            exchangeRateRepository.save(ExchangeRate.created(getCurrency(currencyFrom), getCurrency(currencyTo), localDate, amount));
        else
            exchangeRateRepository.save(ExchangeRate.updatedAmount(exchangeRate.get(), amount));
    }

    private void validCurrencyExist(String currencyFrom, String currencyTo) throws Exception {
        validateIfCurrencyExists(currencyFrom, "From");
        validateIfCurrencyExists(currencyTo, "To");
    }

    private void validateIfCurrencyExists(String currency, String currencyType) {
        if(currencyRepository.getByCode(currency).isEmpty())
            throw new BusinessValidationException(String.format("Currency%s %s doesn't exist", currencyType, currency));
    }

    private Optional<ExchangeRate> getExchangeRate(String currencyFrom, String currencyTo, LocalDate date){
        return exchangeRateRepository.getByCurrencyFromAndCurrencyToAndDate(currencyFrom, currencyTo, date);
    }

    private LocalDate getLocalDate(String date){
        try{
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch(DateTimeParseException e){
            throw new BusinessValidationException("Date is not valid");
        }

    }

    private Currency getCurrency(String currency){
        return currencyRepository.getByCode(currency).get();
    }
}
