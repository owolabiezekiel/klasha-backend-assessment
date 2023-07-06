package com.example.klashabackendassessment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.klashabackendassessment.app.model.ResponseModel;
import com.example.klashabackendassessment.model.request.CurrencyConvertRequest;
import com.example.klashabackendassessment.model.response.countrydetails.CountryDetailsResponseModel;
import com.example.klashabackendassessment.model.response.countrystates.CountryStateResponseData;
import com.example.klashabackendassessment.model.response.currencyconvert.CurrencyConvertResponse;
import com.example.klashabackendassessment.service.abstracts.PlacesService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/country", produces = APPLICATION_JSON_VALUE)
public class AssessmentController {
  private final PlacesService placesService;

  @GetMapping(value = "/details")
  @Operation(
      description = "Get a country's details like location, currency, population, capital etc.")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success", response = CountryDetailsResponseModel.class)
  })
  public CompletableFuture<ResponseModel<CountryDetailsResponseModel>> getCountryDetails(
      @RequestParam String country) {
    return placesService.getCountryDetails(country).thenApply(ResponseModel::new);
  }

  @GetMapping(value = "/states-cities")
  @Operation(description = "Get a country's list of states and the states respective cities")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success", response = CountryStateResponseData.class)
  })
  public CompletableFuture<ResponseModel<CountryStateResponseData>> getCountryStatesAndCities(
      @RequestParam String country) {
    return placesService.getCountryStateAndCities(country).thenApply(ResponseModel::new);
  }

  @PostMapping(value = "/convert-currency", consumes = APPLICATION_JSON_VALUE)
  @Operation(
      description =
          "Convert a country's currency. Currently supports conversion between GDP, USD, EUR and JPY to any of NGN and UGX")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success", response = CurrencyConvertResponse.class)
  })
  public CompletableFuture<ResponseModel<CurrencyConvertResponse>> convertCurrency(
      @Valid @RequestBody CurrencyConvertRequest request) {
    return placesService.convertMoneyToTargetCurrency(request).thenApply(ResponseModel::new);
  }
}
