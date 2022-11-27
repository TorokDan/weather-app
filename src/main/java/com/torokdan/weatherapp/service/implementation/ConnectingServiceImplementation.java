package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.configuration.property.WeatherAppProperties;
import com.torokdan.weatherapp.model.Url;
import com.torokdan.weatherapp.model.dto.DataResponseDto;
import com.torokdan.weatherapp.model.json.Data;
import com.torokdan.weatherapp.service.ConnectionService;
import com.torokdan.weatherapp.service.ModelCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectingServiceImplementation implements ConnectionService {

  private final RestTemplate restTemplate;
  private final WeatherAppProperties weatherAppProperties;
  private final ModelCreator modelCreator;

  public ConnectingServiceImplementation(RestTemplate restTemplate,
      WeatherAppProperties weatherAppProperties, ModelCreator modelCreator) {
    this.restTemplate = restTemplate;
    this.weatherAppProperties = weatherAppProperties;
    this.modelCreator = modelCreator;
  }


  @Override
  public DataResponseDto gatherDataFromLocation(String location) {
    Url url = new Url(weatherAppProperties, location);
    Data data = restTemplate.getForObject(url.toString(), Data.class);
    return modelCreator.createDataResponseDto(data);
  }
}
