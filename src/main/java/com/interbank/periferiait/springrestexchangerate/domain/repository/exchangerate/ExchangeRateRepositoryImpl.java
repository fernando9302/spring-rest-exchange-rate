package com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate;

import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.ExchangeRateEntity;
import com.interbank.periferiait.springrestexchangerate.infraestructure.repository.ExchangeRateSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository{

    @Autowired
    private ExchangeRateSqlRepository exchangeRateSqlRepository;

    @Override
    public Optional<ExchangeRate> getByCurrencyFromAndCurrencyToAndDate(String currencyFrom, String currencyTo, LocalDate date) {
        var exchangeRateEntity = exchangeRateSqlRepository.getByCurrencyFromCodeAndCurrencyToCodeAndDate(currencyFrom.toUpperCase(), currencyTo.toUpperCase(), date);
        return exchangeRateEntity.isPresent() ? Optional.of(exchangeRateEntity.get().toDomain()) : Optional.empty();
    }
}