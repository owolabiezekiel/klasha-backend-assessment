package com.example.klashabackendassessment.model.response.countrydetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryISO {
  private String iso2;
  private String iso3;
}
