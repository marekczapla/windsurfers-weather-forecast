package pl.marekczapla.windsurfersweatherforecastapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.marekczapla.windsurfersweatherforecastapi.model.WindsurfersAppResponse;
import pl.marekczapla.windsurfersweatherforecastapi.service.WindsurfersAppService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AppController {

    private final WindsurfersAppService service;

    @GetMapping("/{date}")
    public ResponseEntity<WindsurfersAppResponse> getLocationForecast(@PathVariable String date) {

        log.info("Getting best windsurfing location for a particular date :: {}", date);

        WindsurfersAppResponse windsurfersAppResponse = service.getBestLocationWeatherForecast(date);

        log.info("Best weather for windsurfing is :: {}", windsurfersAppResponse);
        return ResponseEntity.ok().body(windsurfersAppResponse);
    }


}
