package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConv {
    public static Calendar stringToCalendar(String stringDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(stringDate);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
