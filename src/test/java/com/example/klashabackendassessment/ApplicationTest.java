package com.example.klashabackendassessment;

import static com.example.klashabackendassessment.TestDataFixtures.*;
import static com.example.klashabackendassessment.utils.Constants.USD_TO_NGN;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.klashabackendassessment.config.APIConfiguration;
import com.example.klashabackendassessment.exceptions.AssessmentException;
import com.example.klashabackendassessment.model.response.countrycurrency.CountryCurrencyResponseModel;
import com.example.klashabackendassessment.model.response.countrydetails.CountryDetailsResponseModel;
import com.example.klashabackendassessment.model.response.currencyconvert.CurrencyConvertResponse;
import com.example.klashabackendassessment.service.abstracts.PlacesService;
import com.example.klashabackendassessment.service.concretes.CountriesNowPlacesService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {
  @Mock private RestTemplate restTemplate;
  @Mock private APIConfiguration apiConfiguration;

  private PlacesService placesService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    placesService = new CountriesNowPlacesService(apiConfiguration, restTemplate);
  }

  @Test
  void getCountryDetailsFails_whenCountryIsNotFound() {
    String country = "aaa";

    when(apiConfiguration.getPopulationUrl()).thenReturn("dummy population url");
    Mockito.when(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any(),
                Mockito.<Class<CountryDetailsResponseModel>>any()))
        .thenThrow(new AssessmentException("Country not found", 404));

    CompletableFuture<CountryDetailsResponseModel> result =
        placesService.getCountryDetails(country);

    assertThatThrownBy(result::join)
        .isInstanceOf(CompletionException.class)
        .hasCause(new AssessmentException("Country not found", 404))
        .extracting(Throwable::getCause)
        .hasFieldOrPropertyWithValue("code", NOT_FOUND.value());
  }

  @Test
  void convertCurrencyFails_whenSourceCountryCurrencyNotSupported() {
    ResponseEntity<CountryCurrencyResponseModel> responseEntity =
        new ResponseEntity<>(buildUnsupportedCurrencyModel(), HttpStatus.OK);
    when(apiConfiguration.getCurrencyUrl()).thenReturn("dummy-currency-url");

    Mockito.when(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(),
                Mockito.<Class<CountryCurrencyResponseModel>>any()))
        .thenReturn(responseEntity);

    CompletableFuture<CurrencyConvertResponse> result =
        placesService.convertMoneyToTargetCurrency(buildCurrencyConvertRequest());
    assertThatThrownBy(result::join)
        .isInstanceOf(CompletionException.class)
        .hasCause(
            new AssessmentException(
                format("currency %s not supported", buildUnsupportedCurrencyData().getCurrency()),
                400))
        .extracting(Throwable::getCause)
        .hasFieldOrPropertyWithValue("code", BAD_REQUEST.value());
  }

  @Test
  void convertCurrencyFails_whenTargetCurrencyNotSupported() {
    ResponseEntity<CountryCurrencyResponseModel> responseEntity =
        new ResponseEntity<>(buildSupportedCurrencyModel(), HttpStatus.OK);
    when(apiConfiguration.getCurrencyUrl()).thenReturn("dummy-currency-url");

    Mockito.when(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(),
                Mockito.<Class<CountryCurrencyResponseModel>>any()))
        .thenReturn(responseEntity);

    CompletableFuture<CurrencyConvertResponse> result =
        placesService.convertMoneyToTargetCurrency(buildInvalidCurrencyConvertRequest());
    assertThatThrownBy(result::join)
        .isInstanceOf(CompletionException.class)
        .hasCause(
            new AssessmentException(
                format(
                    "invalid target currency - %s",
                    buildInvalidCurrencyConvertRequest().getTargetCurrency()),
                400))
        .extracting(Throwable::getCause)
        .hasFieldOrPropertyWithValue("code", BAD_REQUEST.value());
  }

  @Test
  void convertCurrencySuccessfully() {
    ResponseEntity<CountryCurrencyResponseModel> responseEntity =
        new ResponseEntity<>(buildSupportedCurrencyModel(), HttpStatus.OK);
    when(apiConfiguration.getCurrencyUrl()).thenReturn("dummy-currency-url");

    Mockito.when(
            restTemplate.exchange(
                Mockito.anyString(),
                Mockito.eq(HttpMethod.POST),
                Mockito.any(),
                Mockito.<Class<CountryCurrencyResponseModel>>any()))
        .thenReturn(responseEntity);

    CurrencyConvertResponse result =
        placesService.convertMoneyToTargetCurrency(buildCurrencyConvertRequest()).join();

    assertNotNull(result);
    assertThat(result.getAmount()).isEqualTo(buildCurrencyConvertRequest().getAmount());
    assertThat(result.getCountry())
        .isEqualToIgnoringCase(buildCurrencyConvertRequest().getCountry());
    assertThat(result.getTargetCurrency())
        .isEqualTo(buildCurrencyConvertRequest().getTargetCurrency());
    assertThat(result.getLocalCurrency().toString())
        .isEqualToIgnoringCase(responseEntity.getBody().getData().getCurrency());
    assertThat(result.getConvertedAmountValue()).isEqualTo(USD_TO_NGN * result.getAmount());
  }
}
