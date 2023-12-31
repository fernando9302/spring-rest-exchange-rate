package com.interbank.periferiait.springrestexchangerate.application.services.exchangerate.convertexhangerate;

import com.interbank.periferiait.springrestexchangerate.application.request.exchangerate.ConversionCurrencyRequest;
import com.interbank.periferiait.springrestexchangerate.application.response.exchangerate.ConversionCurrencyResponse;

public interface ExchangeRateConvertService {
    ConversionCurrencyResponse convertAmount(ConversionCurrencyRequest conversionCurrencyRequest) throws Exception;
}
