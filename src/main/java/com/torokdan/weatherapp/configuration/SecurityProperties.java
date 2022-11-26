package com.torokdan.weatherapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config.security")
public record SecurityProperties(String secret, int minutes, String bearer) {

}
