package com.example.klashabackendassessment.model.response.countrylocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryLocationData {
  @JsonProperty(value = "long")
  private double longitude;

  @JsonProperty(value = "lat")
  private double latitude;
}
