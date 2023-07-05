package com.example.klashabackendassessment.service.abstracts;

import com.example.klashabackendassessment.model.response.countrydetails.CountryDetailsResponseModel;
import com.example.klashabackendassessment.model.response.countrystates.CountryStateResponseData;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PlacesService {
  CompletableFuture<List<CountryStateResponseData>> getCountryStatesAndCities();

  CompletableFuture<CountryDetailsResponseModel> getCountryDetails(String country);
}
