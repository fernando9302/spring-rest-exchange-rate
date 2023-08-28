package com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.changevalueexchangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ExchangeRateRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.exchangerate.ConversionCurrencyResponse;

public interface ExchangeRateChangeValueService {
    void saveExchangeRate(ExchangeRateRequest exchangeRateRequest) throws Exception;
}
