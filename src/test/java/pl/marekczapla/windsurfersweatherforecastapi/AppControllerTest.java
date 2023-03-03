package pl.marekczapla.windsurfersweatherforecastapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.marekczapla.windsurfersweatherforecastapi.controller.AppController;
import pl.marekczapla.windsurfersweatherforecastapi.exception.InvalidDateException;
import pl.marekczapla.windsurfersweatherforecastapi.model.WindsurfersAppResponse;
import pl.marekczapla.windsurfersweatherforecastapi.service.WindsurfersAppService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest {

    @Mock
    private WindsurfersAppService windsurfersAppService;

    @InjectMocks
    private AppController appController;

    @Test
    public void testGetLocationForecastValidDate() {
        String date = "2023-03-03";
        WindsurfersAppResponse windsurfersAppResponse = WindsurfersAppResponse.builder().build();
        when(windsurfersAppService.getBestLocationWeatherForecast(date)).thenReturn(windsurfersAppResponse);

        ResponseEntity<WindsurfersAppResponse> response = appController.getLocationForecast(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(windsurfersAppResponse, response.getBody());
    }

    @Test(expected = InvalidDateException.class)
    public void testGetLocationForecastInvalidDate() {
        String date = "2023-03";
        appController.getLocationForecast(date);
    }
}
