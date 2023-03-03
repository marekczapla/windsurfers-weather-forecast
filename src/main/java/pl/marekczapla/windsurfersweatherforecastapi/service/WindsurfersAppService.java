package pl.marekczapla.windsurfersweatherforecastapi.service;

import pl.marekczapla.windsurfersweatherforecastapi.model.WeatherBitResponse;
import pl.marekczapla.windsurfersweatherforecastapi.model.WindsurfersAppResponse;

import java.util.List;
import java.util.Optional;

public interface WindsurfersAppService {

    List<WeatherBitResponse> getWeatherBitResponseList();
    Optional<WeatherBitResponse> getBestWeatherBitResponseOptional(List<WeatherBitResponse> weatherBitResponseList, String date);
    WeatherBitResponse getBestWeatherBitResponse(String date);
    WindsurfersAppResponse getBestLocationWeatherForecast(String date);




}
