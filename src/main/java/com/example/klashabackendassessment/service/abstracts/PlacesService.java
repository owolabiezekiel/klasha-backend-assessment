package com.example.klashabackendassessment.service.abstracts;

import com.example.klashabackendassessment.model.request.CurrencyConvertRequest;
import com.example.klashabackendassessment.model.response.countrydetails.CountryDetailsResponseModel;
import com.example.klashabackendassessment.model.response.countrystates.CountryStateResponseData;
import com.example.klashabackendassessment.model.response.currencyconvert.CurrencyConvertResponse;
import com.example.klashabackendassessment.model.response.topcities.CountryTopCityData;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PlacesService {
  CompletableFuture<List<CountryTopCityData>> getHighestPopulatedCities(Integer size);

  CompletableFuture<CountryDetailsResponseModel> getCountryDetails(String country);

  CompletableFuture<CountryStateResponseData> getCountryStateAndCities(String country);

  CompletableFuture<CurrencyConvertResponse> convertMoneyToTargetCurrency(
      CurrencyConvertRequest request);
}
