package com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate;

import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


public interface ExchangeRateRepository {
    Optional<ExchangeRate> getByCurrencyFromAndCurrencyToAndDate(String currencyFrom, String currencyTo, LocalDate date);
}
