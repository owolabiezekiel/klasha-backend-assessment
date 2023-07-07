package com.example.klashabackendassessment.model.response.countrypopulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryPopulationCount {
  private String year;
  private String value;
}
