package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.DataResponseDto;
import com.torokdan.weatherapp.model.json.Data;

public interface ConnectionService {
  public DataResponseDto gatherDataFromLocation(String location);
}
