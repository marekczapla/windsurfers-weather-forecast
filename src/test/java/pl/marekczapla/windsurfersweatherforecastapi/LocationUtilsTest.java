package pl.marekczapla.windsurfersweatherforecastapi;

import org.junit.jupiter.api.Test;
import pl.marekczapla.windsurfersweatherforecastapi.location.LocationUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationUtilsTest {

    @Test
    public void testIsValidWithValidDate() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2023-03-15";

        boolean result = LocationUtils.isValid(date);

        assertTrue(result);
    }

    @Test
    public void testIsValidWithInvalidDate() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2023/03/15";

        boolean result = LocationUtils.isValid(date);

        assertFalse(result);
    }

    @Test
    public void testIsValidWithDoubleDashesDate() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2023-03--15";

        boolean result = LocationUtils.isValid(date);

        assertFalse(result);
    }

    @Test
    public void testIsValidWithNoDashesDate() throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "20230315";

        boolean result = LocationUtils.isValid(date);

        assertFalse(result);
    }

    @Test
    public void testIsValidWithEmptyDate() throws ParseException {

        String date = "";

        boolean result = LocationUtils.isValid(date);

        assertFalse(result);
    }
}
