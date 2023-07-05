package com.example.klashabackendassessment.service.concretes;

import static com.example.klashabackendassessment.utils.StringUtils.capitalizeFirstCharacter;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import com.example.klashabackendassessment.config.APIConfiguration;
import com.example.klashabackendassessment.exceptions.AssessmentException;
import com.example.klashabackendassessment.model.request.CountryRequest;
import com.example.klashabackendassessment.model.response.capital.CountryCapitalResponseData;
import com.example.klashabackendassessment.model.response.capital.CountryCapitalResponseModel;
import com.example.klashabackendassessment.model.response.countrycurrency.CountryCurrencyData;
import com.example.klashabackendassessment.model.response.countrycurrency.CountryCurrencyResponseModel;
import com.example.klashabackendassessment.model.response.countrydetails.CountryDetailsResponseModel;
import com.example.klashabackendassessment.model.response.countrydetails.CountryISO;
import com.example.klashabackendassessment.model.response.countrylocation.CountryLocationData;
import com.example.klashabackendassessment.model.response.countrylocation.CountryLocationResponseModel;
import com.example.klashabackendassessment.model.response.countrypopulation.CountryPopulationResponse;
import com.example.klashabackendassessment.model.response.countrypopulation.PopulationCount;
import com.example.klashabackendassessment.model.response.countrystates.AllCountryAndStatesModel;
import com.example.klashabackendassessment.model.response.countrystates.CountryStateResponseData;
import com.example.klashabackendassessment.model.response.countrystates.CountryStateResponseModel;
import com.example.klashabackendassessment.service.abstracts.PlacesService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountriesNowPlacesService implements PlacesService {
  private final APIConfiguration apiConfiguration;
  private final RestTemplate restTemplate;

  @Override
  public CompletableFuture<List<CountryStateResponseData>> getCountryStatesAndCities() {
    return supplyAsync(
        () -> {
          AllCountryAndStatesModel stateResponse;
          HttpEntity<Void> all = new HttpEntity<>(null, setupRequestHeaders());
          try {
            log.info("Getting all states for country");
            ResponseEntity<AllCountryAndStatesModel> response =
                restTemplate.exchange(
                    apiConfiguration.getStatesUrl(), GET, all, AllCountryAndStatesModel.class);
            stateResponse = response.getBody();
          } catch (HttpClientErrorException e) {
            log.error("Error encountered: {}", e.getMessage());
            throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
          } catch (HttpServerErrorException e) {
            log.error("Error: encountered: {}", e.getMessage());
            throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
          }
          return stateResponse.getData();
        });
  }

  @Override
  public CompletableFuture<CountryDetailsResponseModel> getCountryDetails(String country) {
    return supplyAsync(
        () ->
            CountryDetailsResponseModel.builder()
                .population(getCountryPopulation(country))
                .capitalCity(getCountryCapital(country).getCapital())
                .location(getCountryLocation(country))
                .currency(getCountryCurrency(country).getCurrency())
                .iso(getIso(country))
                .country(capitalizeFirstCharacter(country))
                .build());
  }

  private CountryRequest buildCountryRequest(String country) {
    return CountryRequest.builder().country(country).build();
  }

  private HttpHeaders setupRequestHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.put("Content-Type", List.of("application/json"));
    return headers;
  }

  private PopulationCount getCountryPopulation(String country) {
    CountryPopulationResponse countryPopulationResponse;
    CountryRequest countryRequest = buildCountryRequest(country);
    HttpEntity<CountryRequest> countryRequestHttpEntity =
        new HttpEntity<>(countryRequest, setupRequestHeaders());
    try {
      log.info("Getting population count for country: {}", country);
      ResponseEntity<CountryPopulationResponse> response =
          restTemplate.exchange(
              apiConfiguration.getPopulationUrl(),
              POST,
              countryRequestHttpEntity,
              CountryPopulationResponse.class);
      countryPopulationResponse = response.getBody();
    } catch (HttpClientErrorException e) {
      log.error("Error encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    } catch (HttpServerErrorException e) {
      log.error("Error: encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    }

    List<PopulationCount> populationCounts =
        countryPopulationResponse.getData().getPopulationCounts();
    int populationCountSize = populationCounts.size();
    return populationCounts.get(populationCountSize - 1);
  }

  private CountryCapitalResponseData getCountryCapital(String country) {
    CountryCapitalResponseModel countryCapitalResponse;
    CountryRequest countryRequest = buildCountryRequest(country);
    HttpEntity<CountryRequest> countryRequestHttpEntity =
        new HttpEntity<>(countryRequest, setupRequestHeaders());
    try {
      log.info("Getting capital of country: {}", country);
      ResponseEntity<CountryCapitalResponseModel> response =
          restTemplate.exchange(
              apiConfiguration.getCapitalUrl(),
              POST,
              countryRequestHttpEntity,
              CountryCapitalResponseModel.class);
      countryCapitalResponse = response.getBody();
    } catch (HttpClientErrorException e) {
      log.error("Error encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    } catch (HttpServerErrorException e) {
      log.error("Error: encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    }

    return countryCapitalResponse.getData();
  }

  private CountryLocationData getCountryLocation(String country) {
    CountryLocationResponseModel countryLocationResponse;
    CountryRequest countryRequest = buildCountryRequest(country);
    HttpEntity<CountryRequest> countryRequestHttpEntity =
        new HttpEntity<>(countryRequest, setupRequestHeaders());
    try {
      log.info("Getting location of country: {}", country);
      ResponseEntity<CountryLocationResponseModel> response =
          restTemplate.exchange(
              apiConfiguration.getLocationUrl(),
              POST,
              countryRequestHttpEntity,
              CountryLocationResponseModel.class);
      countryLocationResponse = response.getBody();
    } catch (HttpClientErrorException e) {
      log.error("Error encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    } catch (HttpServerErrorException e) {
      log.error("Error: encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    }

    return countryLocationResponse.getData();
  }

  private CountryCurrencyData getCountryCurrency(String country) {
    CountryCurrencyResponseModel countryCurrencyResponse;
    CountryRequest countryRequest = buildCountryRequest(country);
    HttpEntity<CountryRequest> countryRequestHttpEntity =
        new HttpEntity<>(countryRequest, setupRequestHeaders());
    try {
      log.info("Getting currency of country: {}", country);
      ResponseEntity<CountryCurrencyResponseModel> response =
          restTemplate.exchange(
              apiConfiguration.getCurrencyUrl(),
              POST,
              countryRequestHttpEntity,
              CountryCurrencyResponseModel.class);
      countryCurrencyResponse = response.getBody();
    } catch (HttpClientErrorException e) {
      log.error("Error encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    } catch (HttpServerErrorException e) {
      log.error("Error: encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    }

    return countryCurrencyResponse.getData();
  }

  private CountryISO getIso(String country) {
    CountryStateResponseModel countryStateResponse;
    CountryRequest countryRequest = buildCountryRequest(country);
    HttpEntity<CountryRequest> countryRequestHttpEntity =
        new HttpEntity<>(countryRequest, setupRequestHeaders());
    try {
      log.info("Getting ISO of country: {}", country);
      ResponseEntity<CountryStateResponseModel> response =
          restTemplate.exchange(
              apiConfiguration.getCurrencyUrl(),
              POST,
              countryRequestHttpEntity,
              CountryStateResponseModel.class);
      countryStateResponse = response.getBody();
    } catch (HttpClientErrorException e) {
      log.error("Error encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    } catch (HttpServerErrorException e) {
      log.error("Error: encountered: {}", e.getMessage());
      throw new AssessmentException(e.getMessage(), e.getStatusCode().value());
    }

    return CountryISO.builder()
        .iso2(countryStateResponse.getData().getIso2())
        .iso3(countryStateResponse.getData().getIso3())
        .build();
  }
}