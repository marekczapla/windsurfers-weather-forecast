package pl.marekczapla.windsurfersweatherforecastapi;

import org.junit.jupiter.api.Test;
import pl.marekczapla.windsurfersweatherforecastapi.location.LocationUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LocationUtilsTest {

    @Test
    public void testIsValidWithValidDate() throws ParseException {
        // Arrange
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2022-04-15";

        // Act
        boolean result = LocationUtils.isValid(date);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValidWithInvalidDate() throws ParseException {
        // Arrange
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2022/04/15";

        // Act
        boolean result = LocationUtils.isValid(date);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValidWithNullDate() throws ParseException {
        // Arrange
        String date = null;

        // Act
        boolean result = LocationUtils.isValid(date);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValidWithEmptyDate() throws ParseException {
        // Arrange
        String date = "";

        // Act
        boolean result = LocationUtils.isValid(date);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValidWithWhitespaceDate() throws ParseException {
        // Arrange
        String date = "   ";

        // Act
        boolean result = LocationUtils.isValid(date);

        // Assert
        assertFalse(result);
    }

}
