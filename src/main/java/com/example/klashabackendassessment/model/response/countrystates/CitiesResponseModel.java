package com.example.klashabackendassessment.model.response.countrystates;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CitiesResponseModel {
  private List<String> data;
}
