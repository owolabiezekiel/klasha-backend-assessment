package com.example.klashabackendassessment.model.response.capital;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryCapitalResponseModel {
  private CountryCapitalResponseData data;
}
