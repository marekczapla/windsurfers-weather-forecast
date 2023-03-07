package pl.marekczapla.windsurfersweatherforecastapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import pl.marekczapla.windsurfersweatherforecastapi.model.WeatherBitResponse;
import pl.marekczapla.windsurfersweatherforecastapi.model.WindsurfersAppResponse;
import pl.marekczapla.windsurfersweatherforecastapi.service.WindsurfersAppService;
import pl.marekczapla.windsurfersweatherforecastapi.service.WindsurfersAppServiceImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WindsurfersAppServiceImplTest {

    private WindsurfersAppServiceImpl windsurfersAppService;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        windsurfersAppService = new WindsurfersAppServiceImpl(restTemplate);
    }

    @Test
    public void testGetWeatherBitResponseList() {

        String city1 = "jastarnia";
        String city2 = "bridgetown";
        String city3 = "fortaleza";
        String city4 = "pissouri";
        String city5 = "petite_case_noyale";
        List<String> citiesList = List.of(city1, city2, city3, city4, city5);
        List<WeatherBitResponse> weatherBitResponses = new ArrayList<>();
        WeatherBitResponse weatherBitResponse1 = new WeatherBitResponse();
        WeatherBitResponse weatherBitResponse2 = new WeatherBitResponse();
        WeatherBitResponse weatherBitResponse3 = new WeatherBitResponse();
        WeatherBitResponse weatherBitResponse4 = new WeatherBitResponse();
        WeatherBitResponse weatherBitResponse5 = new WeatherBitResponse();
        weatherBitResponses.add(weatherBitResponse1);
        weatherBitResponses.add(weatherBitResponse2);
        weatherBitResponses.add(weatherBitResponse3);
        weatherBitResponses.add(weatherBitResponse4);
        weatherBitResponses.add(weatherBitResponse5);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(weatherBitResponse1, weatherBitResponse2, weatherBitResponse3, weatherBitResponse4, weatherBitResponse5);

        List<WeatherBitResponse> result = windsurfersAppService.getWeatherBitResponseList();

        verify(restTemplate, times(5)).getForObject(anyString(), any());
        assertEquals(citiesList.size(), result.size());
    }

    @Test
    public void testGetBestWeatherBitResponseOptional_whenWeatherBitResponseListIsEmpty() {

        List<WeatherBitResponse> weatherBitResponses = new ArrayList<>();

        Optional<WeatherBitResponse> result = windsurfersAppService.getBestWeatherBitResponseOptional(weatherBitResponses, "2023-03-01");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetBestLocationWeatherForecast() {
        Date date = new Date();
        LocalDateTime tomorrowDate = LocalDateTime.from(date.toInstant().atZone(ZoneId.of("UTC"))).plusDays(1);
        String strDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(tomorrowDate);
        WindsurfersAppService service = new WindsurfersAppServiceImpl(new RestTemplate());
        WindsurfersAppResponse response = service.getBestLocationWeatherForecast(strDate);
        assertNotNull(response);
        assertNotNull(response.getCity());
        assertNotNull(response.getCountry());
        assertNotNull(response.getLatitude());
        assertNotNull(response.getLongitude());
        assertNotNull(response.getAverageTemp());
        assertNotNull(response.getWindSpeed());
    }
}