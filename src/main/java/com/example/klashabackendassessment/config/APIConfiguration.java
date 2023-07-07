package com.example.klashabackendassessment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "klasha.places")
public class APIConfiguration {
  private String domain;
  private String baseUrl;
  private String statesUrl;
  private String stateCitiesUrl;
  private String populationUrl;
  private String capitalUrl;
  private String locationUrl;
  private String currencyUrl;
  private String countryCitiesUrl;
  private String cityPopulationUrl;
}
