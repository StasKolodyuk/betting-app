
package by.bsu.kolodyuk.bettingapp.utility;

import java.sql.Timestamp;
import java.util.Calendar;


public class TimeUtil {
    
    public static Calendar convertToCalendar(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        return calendar;
    }
    
    public static Timestamp convertToTimestamp(Calendar calendar) {
        return new Timestamp(calendar.getTimeInMillis());
    }
    
}
