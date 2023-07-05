package com.example.klashabackendassessment.model.response.countrystates;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class StateModel {
  private String name;

  @JsonProperty("state_code")
  private String stateCode;

  private List<String> cities;
}
