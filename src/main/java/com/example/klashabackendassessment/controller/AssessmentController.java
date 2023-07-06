package com.example.klashabackendassessment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.klashabackendassessment.app.model.ResponseModel;
import com.example.klashabackendassessment.model.request.CurrencyConvertRequest;
import com.example.klashabackendassessment.model.response.countrydetails.CountryDetailsResponseModel;
import com.example.klashabackendassessment.model.response.countrystates.CountryStateResponseData;
import com.example.klashabackendassessment.model.response.currencyconvert.CurrencyConvertResponse;
import com.example.klashabackendassessment.service.abstracts.PlacesService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/country", produces = APPLICATION_JSON_VALUE)
public class AssessmentController {
  private final PlacesService placesService;

  @GetMapping(value = "/states-cities/all")
  public CompletableFuture<ResponseModel<List<CountryStateResponseData>>> getAllCountriesStates() {
    return placesService.getAllCountryStatesAndCities().thenApply(ResponseModel::new);
  }

  @GetMapping(value = "/details")
  public CompletableFuture<ResponseModel<CountryDetailsResponseModel>> getCountryDetails(
      @RequestParam String country) {
    return placesService.getCountryDetails(country).thenApply(ResponseModel::new);
  }

  @GetMapping(value = "/states-cities")
  public CompletableFuture<ResponseModel<CountryStateResponseData>> getCountryStatesAndCities(
      @RequestParam String country) {
    return placesService.getCountryStateAndCities(country).thenApply(ResponseModel::new);
  }

  @PostMapping(value = "/convert-currency", consumes = APPLICATION_JSON_VALUE)
  public CompletableFuture<ResponseModel<CurrencyConvertResponse>> convertCurrency(
      @Valid @RequestBody CurrencyConvertRequest request) {
    return placesService.convertMoneyToTargetCurrency(request).thenApply(ResponseModel::new);
  }
}
