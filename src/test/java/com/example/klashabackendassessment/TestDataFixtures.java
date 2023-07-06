package com.example.klashabackendassessment;

import com.example.klashabackendassessment.app.enums.Currency;
import com.example.klashabackendassessment.model.request.CurrencyConvertRequest;
import com.example.klashabackendassessment.model.response.countrycurrency.CountryCurrencyData;
import com.example.klashabackendassessment.model.response.countrycurrency.CountryCurrencyResponseModel;

public class TestDataFixtures {
  public static CountryCurrencyResponseModel buildSupportedCurrencyModel() {
    return CountryCurrencyResponseModel.builder().data(buildSupportedCurrencyData()).build();
  }

  public static CountryCurrencyResponseModel buildUnsupportedCurrencyModel() {
    return CountryCurrencyResponseModel.builder().data(buildUnsupportedCurrencyData()).build();
  }

  public static CountryCurrencyData buildUnsupportedCurrencyData() {
    return CountryCurrencyData.builder().currency("YYY").build();
  }

  public static CountryCurrencyData buildSupportedCurrencyData() {
    return CountryCurrencyData.builder().currency("USD").build();
  }

  public static CurrencyConvertRequest buildCurrencyConvertRequest() {
    return CurrencyConvertRequest.builder()
        .amount(200.0)
        .country("Spain")
        .targetCurrency(Currency.NGN)
        .build();
  }

  public static CurrencyConvertRequest buildInvalidCurrencyConvertRequest() {
    return CurrencyConvertRequest.builder()
        .amount(200.0)
        .country("Japan")
        .targetCurrency(Currency.JPY)
        .build();
  }
}
