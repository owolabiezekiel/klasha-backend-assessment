package com.example.klashabackendassessment.model.response.countrycurrency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryCurrencyResponseModel {
  private CountryCurrencyData data;
}
