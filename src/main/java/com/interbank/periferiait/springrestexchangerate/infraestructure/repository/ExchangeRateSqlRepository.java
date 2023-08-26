package com.interbank.periferiait.springrestexchangerate.infraestructure.repository;

import com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeRateSqlRepository extends JpaRepository<ExchangeRateEntity, String> {

    Optional<ExchangeRateEntity> getByCurrencyFromCodeAndCurrencyToCodeAndDate(String currencyFrom, String currencyTo, LocalDate date);
}
