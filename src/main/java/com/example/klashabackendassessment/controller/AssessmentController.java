package com.example.klashabackendassessment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.klashabackendassessment.model.ResponseModel;
import com.example.klashabackendassessment.model.response.countrydetails.CountryDetailsResponseModel;
import com.example.klashabackendassessment.model.response.countrystates.CountryStateResponseData;
import com.example.klashabackendassessment.service.abstracts.PlacesService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/country", produces = APPLICATION_JSON_VALUE)
public class AssessmentController {
  private final PlacesService placesService;

  @GetMapping()
  public CompletableFuture<ResponseModel<List<CountryStateResponseData>>> getAllCountriesStates() {
    return placesService.getCountryStatesAndCities().thenApply(ResponseModel::new);
  }

  @GetMapping(value = "/details")
  public CompletableFuture<ResponseModel<CountryDetailsResponseModel>> getCountryDetails(
      @RequestParam String country) {
    return placesService.getCountryDetails(country).thenApply(ResponseModel::new);
  }
}
