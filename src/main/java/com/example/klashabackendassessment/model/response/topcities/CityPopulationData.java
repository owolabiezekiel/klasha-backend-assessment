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
public class CityPopulationData {
  private String city;
  private String country;
  private List<CityPopulationCount> populationCounts;
}
