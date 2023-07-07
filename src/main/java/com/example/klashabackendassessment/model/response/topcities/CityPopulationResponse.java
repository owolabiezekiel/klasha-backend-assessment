package com.example.klashabackendassessment.model.response.topcities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityPopulationResponse {
  private CityPopulationData data;
}
