package com.example.klashabackendassessment.model.response.countrydetails;

import com.example.klashabackendassessment.model.response.countrylocation.CountryLocationData;
import com.example.klashabackendassessment.model.response.countrypopulation.CountryPopulationCount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDetailsResponseModel {
  private String country;
  private CountryPopulationCount population;
  private String capitalCity;
  private CountryLocationData location;
  private String currency;
  private CountryISO iso;
}
