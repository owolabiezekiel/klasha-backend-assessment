package com.example.klashabackendassessment;

import com.example.klashabackendassessment.app.enums.Currency;
import com.example.klashabackendassessment.model.request.CurrencyConvertRequest;
import com.example.klashabackendassessment.model.response.countrycurrency.CountryCurrencyData;
import com.example.klashabackendassessment.model.response.countrycurrency.CountryCurrencyResponseModel;

public class TestDataFixtures {
  //  public static CountryRequest buildCountryRequest(String country){
  //    return CountryRequest.builder().country(country).build();
  //  }
  //
  //  public static CountryISO buildCountryIso(){
  //    return CountryISO.builder().iso3("NGA").iso2("NG").build();
  //  }

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

  //  public static CountryLocationData buildLocationData() {
  //    return CountryLocationData.builder().latitude(1.5).latitude(1.2).build();
  //  }
  //
  //  public static PopulationCount buildPopulationCount() {
  //    return PopulationCount.builder().year("2019").value("200000").build();
  //  }

  //  public static CountryDetailsResponseModel buildCountryDetailsResponse(){
  //    return CountryDetailsResponseModel.builder()
  //        .country("Nigeria")
  //        .iso(buildCountryIso())
  //        .currency("NGN")
  //        .location(buildLocationData())
  //        .capitalCity("Lagos")
  //        .population(buildPopulationCount())
  //        .build();
  //  }
  //
  //  public static CurrencyConvertResponse buildCurrencyConvertResponse(){
  //    return CurrencyConvertResponse.builder()
  //        .country("Nigeria")
  //        .localCurrency(Currency.NGN)
  //        .amount(200.0)
  //        .targetCurrency(Currency.NGN)
  //        .convertedAmountValue(3500.0)
  //        .build();
  //  }

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
