package pl.marekczapla.windsurfersweatherforecastapi.location;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LocationUtils {

    private static String dateFormat="yyyy-mm-dd";
    public final static String  WIND_SPEED_KEY ="wind_spd";
    public final static String  TEMP_KEY ="temp";
    public final static String  VALID_DATE_KEY ="valid_date";

    public static boolean isValid(String date) {
        DateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
