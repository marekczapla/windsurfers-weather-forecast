package pl.marekczapla.windsurfersweatherforecastapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {

    private Date timestamp;

    private int status;

    private String error;

    private String message;

    private String details;

}
