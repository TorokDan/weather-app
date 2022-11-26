package com.torokdan.weatherapp.controller;

import com.torokdan.weatherapp.configuration.WeatherAppProperties;
import com.torokdan.weatherapp.model.Data;
import com.torokdan.weatherapp.model.Url;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherController {

  private final WeatherAppProperties weatherAppProperties;
  private final RestTemplate restTemplate;

  public WeatherController(WeatherAppProperties weatherAppProperties, RestTemplate restTemplate) {
    this.weatherAppProperties = weatherAppProperties;
    this.restTemplate = restTemplate;
  }

  @PostMapping("/{location}")
  public String location(@PathVariable String location) {
    Url url = new Url(weatherAppProperties, location);
    Data data = restTemplate.getForObject(url.toString(), Data.class);
    return data.toString();
  }

}
