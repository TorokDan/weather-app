package com.torokdan.weatherapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config.weather.app")
public record WeatherAppProperties(String url, String apikey) {

}
