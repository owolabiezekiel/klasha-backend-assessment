package com.example.klashabackendassessment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "klasha.cities")
public class APIConfiguration {
  private String domain;
  private String baseUrl;
  private String statesUrl;
  private String populationUrl;
  private String capitalUrl;
  private String locationUrl;
  private String currencyUrl;
}
