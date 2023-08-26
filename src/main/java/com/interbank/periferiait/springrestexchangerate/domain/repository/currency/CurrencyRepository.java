package com.interbank.periferiait.springrestexchangerate.domain.repository.currency;

import com.interbank.periferiait.springrestexchangerate.domain.model.Currency;

import java.util.Optional;

public interface CurrencyRepository {
    Optional<Currency> getByCode(String code);
}
