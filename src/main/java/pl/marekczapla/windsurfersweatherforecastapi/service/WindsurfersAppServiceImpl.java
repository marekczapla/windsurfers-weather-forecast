package pl.marekczapla.windsurfersweatherforecastapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.marekczapla.windsurfersweatherforecastapi.location.LocationUtils;
import pl.marekczapla.windsurfersweatherforecastapi.model.WeatherBitResponse;
import pl.marekczapla.windsurfersweatherforecastapi.model.WindsurfersAppResponse;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WindsurfersAppServiceImpl implements WindsurfersAppService {
    private final RestTemplate restTemplate;
    private String API_URL = "https://api.weatherbit.io/v2.0/forecast/daily?city=%s&key=%s";
    private String API_KEY = "4666b281bc134a8e85b873a0f63cc4bc";
    private final List<String> citiesList = List.of("jastarnia", "bridgetown", "fortaleza", "pissouri", "petite_case_noyale");
    @Override
    public List<WeatherBitResponse> getWeatherBitResponseList() {
        return citiesList.stream()
                .map(city -> restTemplate.getForObject(String.format(API_URL, city, API_KEY), WeatherBitResponse.class))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<WeatherBitResponse> getBestWeatherBitResponseOptional(List<WeatherBitResponse> weatherBitResponseList, String date) {
        return weatherBitResponseList.stream()
                .flatMap(weatherBitResponse -> weatherBitResponse.getData().stream()
                        .filter(data -> ((LinkedHashMap<?, ?>) data).get(LocationUtils.VALID_DATE_KEY).toString().equalsIgnoreCase(date))
                        .map(data -> {
                            String windSpeed = ((LinkedHashMap<?, ?>) data).get(LocationUtils.WIND_SPEED_KEY).toString();
                            String temp = ((LinkedHashMap<?, ?>) data).get(LocationUtils.TEMP_KEY).toString();
                            double windSpeedValue = Double.parseDouble(windSpeed);
                            double tempValue = Double.parseDouble(temp);
                            if (windSpeedValue > 5 && windSpeedValue < 18 && tempValue > 5 && tempValue < 35) {
                                WeatherBitResponse response = new WeatherBitResponse();
                                response.setCity_name(weatherBitResponse.getCity_name());
                                response.setLat(weatherBitResponse.getLat());
                                response.setLon(weatherBitResponse.getLon());
                                response.setCountry_code(weatherBitResponse.getCountry_code());
                                response.setData(List.of(data));
                                response.setBestLocationFormulaResult(windSpeedValue * 3 + tempValue);
                                return response;
                            }
                            return null;
                        })
                        .filter(Objects::nonNull))
                .max(Comparator.comparingDouble(WeatherBitResponse::getBestLocationFormulaResult));
    }

    @Override
    public WeatherBitResponse getBestWeatherBitResponse(String date) {
        List<WeatherBitResponse> weatherBitResponseList = getWeatherBitResponseList();
        Optional<WeatherBitResponse> bestWeatherBitResponseOptional = getBestWeatherBitResponseOptional(weatherBitResponseList, date);

        if (bestWeatherBitResponseOptional.isEmpty()) {
            throw new RuntimeException("Can't find suitable location for provided date");
        }

        return bestWeatherBitResponseOptional.get();
    }

    @Override
    public WindsurfersAppResponse getBestLocationWeatherForecast(String date) {
        WeatherBitResponse bestWeatherBitResponse = getBestWeatherBitResponse(date);

        Object data = bestWeatherBitResponse.getData().get(0);
        String windSpeed = ((LinkedHashMap<?, ?>) data).get(LocationUtils.WIND_SPEED_KEY).toString();
        String temp = ((LinkedHashMap<?, ?>) data).get(LocationUtils.TEMP_KEY).toString();
        String cityName = bestWeatherBitResponse.getCity_name();
        String latitude = bestWeatherBitResponse.getLat();
        String longitude = bestWeatherBitResponse.getLon();
        String countryName = bestWeatherBitResponse.getCountry_code();

        log.info("Best location for windsurfing for a particular date is :: {}", bestWeatherBitResponse);
        return WindsurfersAppResponse.builder()
                .city(cityName)
                .country(countryName)
                .latitude(latitude)
                .longitude(longitude)
                .averageTemp(temp)
                .windSpeed(windSpeed)
                .build();
    }
}
