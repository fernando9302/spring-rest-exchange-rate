package com.interbank.periferiait.springrestexchangerate.domain.repository.currency;

import com.interbank.periferiait.springrestexchangerate.domain.model.Currency;
import com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.repository.CurrencySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository{
    @Autowired
    private CurrencySqlRepository currencySqlRepository;

    @Override
    public Optional<Currency> getByCode(String code) {
        var optionalCurrency = currencySqlRepository.getByCode(code.toUpperCase());
        return optionalCurrency.isPresent() ? Optional.of(optionalCurrency.get().toDomain()) : Optional.empty();

    }
}
