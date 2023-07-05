package com.example.klashabackendassessment.model.response.countrystates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CountryStateResponseModel {
  private CountryStateResponseData data;
}
