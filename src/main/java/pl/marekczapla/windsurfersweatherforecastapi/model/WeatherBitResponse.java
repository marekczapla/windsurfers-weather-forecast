package pl.marekczapla.windsurfersweatherforecastapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherBitResponse {

    List<Object> data = new ArrayList<Object>();
    private String city_name;
    private String lat;
    private String lon;
    private String country_code;
    private double bestLocationFormulaResult;

}
