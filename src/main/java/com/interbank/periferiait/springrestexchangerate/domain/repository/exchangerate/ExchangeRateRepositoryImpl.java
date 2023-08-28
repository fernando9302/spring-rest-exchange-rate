package com.interbank.periferiait.springrestexchangerate.domain.repository.exchangerate;

import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.entity.ExchangeRateEntity;
import com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.repository.ExchangeRateSqlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository{

    @Autowired
    private ExchangeRateSqlRepository exchangeRateSqlRepository;

    @Override
    public Optional<ExchangeRate> getByCurrencyFromAndCurrencyToAndDate(String currencyFrom, String currencyTo, LocalDate date) {
        var exchangeRateEntity = exchangeRateSqlRepository.getByCurrencyFromCodeAndCurrencyToCodeAndDate(currencyFrom.toUpperCase(), currencyTo.toUpperCase(), date);
        return exchangeRateEntity.isPresent() ? Optional.of(exchangeRateEntity.get().toDomain()) : Optional.empty();
    }

    @Override
    public void save(ExchangeRate exchangeRate) {
        exchangeRateSqlRepository.save(new ExchangeRateEntity(exchangeRate));
    }
}
