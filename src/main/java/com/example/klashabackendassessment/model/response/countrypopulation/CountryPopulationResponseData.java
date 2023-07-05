package com.example.klashabackendassessment.model.response.countrypopulation;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryPopulationResponseData {
  private List<PopulationCount> populationCounts;
}
