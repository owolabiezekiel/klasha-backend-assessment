package com.example.klashabackendassessment.model.response.countrylocation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryLocationResponseModel {
  private CountryLocationData data;
}
