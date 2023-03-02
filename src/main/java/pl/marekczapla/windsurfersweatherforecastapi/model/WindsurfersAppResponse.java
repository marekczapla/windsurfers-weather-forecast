package pl.marekczapla.windsurfersweatherforecastapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WindsurfersAppResponse implements Serializable {

    private String city;
    private String country;
    private String latitude;
    private String longitude;
    private String averageTemp;
    private String windSpeed;

}
