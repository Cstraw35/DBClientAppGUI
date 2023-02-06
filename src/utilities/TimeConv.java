package utilities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeConv {
    /**
     *
     * @param stringDate
     * @return Date from a string
     * @throws ParseException
     */
    public static Date stringToDate(String stringDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(stringDate);
        return date;
    }

    /**
     *
     * @param stringDate
     * @return ZonedDateTime from string
     * @throws ParseException
     */
    public static ZonedDateTime stringToZoneDate(String stringDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));
        ZonedDateTime zoneDate = ZonedDateTime.parse(stringDate, formatter);
        return zoneDate;
    }

    /**
     *
     * @param date
     * @return String from Date
     * @throws ParseException
     */
    public static String DateToString(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate = sdf.format(date);
        return stringDate;
    }

    /**
     *
     * @param zonedDateTime
     * @return String from ZonedDateTime
     */
    public static String zdtToString(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = formatter.format(zonedDateTime);
        return dateTime;
    }

    /**
     *
     * @param localDateTime
     * @return Calendar for local date time.
     */
    public static Calendar ldtToCalendar(ZonedDateTime localDateTime) {

        Calendar checkCalendar = GregorianCalendar.from(localDateTime);
        return checkCalendar;

    }

}
