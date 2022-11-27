package com.torokdan.weatherapp;

import com.torokdan.weatherapp.configuration.property.SecurityProperties;
import com.torokdan.weatherapp.configuration.property.WeatherAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class, WeatherAppProperties.class})
public class WeatherAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeatherAppApplication.class, args);
  }

}
