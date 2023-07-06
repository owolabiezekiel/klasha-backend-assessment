package com.example.klashabackendassessment.model.request;

import com.example.klashabackendassessment.app.enums.Currency;
import com.example.klashabackendassessment.app.validators.ValidTargetCurrency;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyConvertRequest {
  @NotNull(message = "country string should not be null")
  @NotEmpty(message = "country string should not be empty")
  @NotBlank(message = "country string should not be blank")
  private String country;

  @NotNull(message = "amount should not be null")
  @Positive(message = "amount should not be negative")
  private Double amount;

  @NotNull(message = "targetCurrency should not be null")
  @ValidTargetCurrency()
  private Currency targetCurrency;
}
