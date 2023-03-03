package pl.marekczapla.windsurfersweatherforecastapi.exception;

public class SuitableLocationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SuitableLocationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
