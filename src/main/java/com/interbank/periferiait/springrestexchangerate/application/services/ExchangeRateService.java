package com.interbank.periferiait.springrestexchangerate.application.services;

import com.interbank.periferiait.springrestexchangerate.application.request.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.ConversionCurrencyResponse;

public interface ExchangeRateService {
    ConversionCurrencyResponse convertAmount(ConversionCurrencyRequest conversionCurrencyRequest) throws Exception;
}
