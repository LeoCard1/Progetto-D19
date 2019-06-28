package DeliveryManSystem;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This class contains methods to manage dates
 * @author Andrea Stella
 * @version 1.0
 */

public class DateHandler {

    /**
     * This method adds the number of days passed as an argument to the date.
     *
     * @param date date to add days
     * @param days days to add
     * @return updated date
     */

    public Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * This method calculates the difference in days between two dates.
     *
     * @param firstDate  date to compare
     * @param secondDate date to compare
     * @return the number of days of difference
     */

    public int differenceInDays(Date firstDate, Date secondDate) {
        long millisDif = firstDate.getTime() - secondDate.getTime();
        int days = (int) millisDif / 86400000;
        if (days < 0) days *= -1;
        return days;
    }
}
