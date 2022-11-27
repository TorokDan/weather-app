package com.torokdan.weatherapp.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config.weather.app")
public record WeatherAppProperties(String url, String apikey) {

}
