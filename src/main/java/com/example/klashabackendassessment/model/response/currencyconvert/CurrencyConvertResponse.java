package com.example.klashabackendassessment.model.response.currencyconvert;

import com.example.klashabackendassessment.app.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyConvertResponse {
  private String country;
  private Currency localCurrency;
  private Double amount;
  private Currency targetCurrency;
  private Double convertedAmountValue;
}
