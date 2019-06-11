package PickupPointSystem;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Andrea Stella
 * @version 1.0
 */

public class DateHandler {

    /**
     * This method adds the number of days passed as an argument to the date.
     * @param date
     * @param days
     * @return Date
     */

    public Date addDays(Date date, int days){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * This method calculates the difference in days between two dates.
     * @param firstDate
     * @param secondDate
     * @return the number of days
     */

    public int differenceInDays(Date firstDate, Date secondDate){
        long millisDif = firstDate.getTime() - secondDate.getTime();
        int days = (int) millisDif/86400000;
        if(days<0) days*=-1;
        return days;
    }


}
