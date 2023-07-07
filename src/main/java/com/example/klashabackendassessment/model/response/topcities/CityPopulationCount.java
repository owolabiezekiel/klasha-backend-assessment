package com.example.klashabackendassessment.model.response.topcities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityPopulationCount {
  private String year;
  private String value;
  private String sex;
  private String reliability;
}
