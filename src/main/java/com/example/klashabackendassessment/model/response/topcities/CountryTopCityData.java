package com.example.klashabackendassessment.model.response.topcities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryTopCityData {
  private String country;
  private List<CityPopulationDetails> topCities;
}
