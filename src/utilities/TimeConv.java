package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConv {
    public static Date stringToDate(String stringDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(stringDate);
        return date;
    }
    public static String DateToString(Date date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String stringDate = sdf.format(date);
        return stringDate;
    }
}
