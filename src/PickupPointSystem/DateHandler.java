package PickupPointSystem;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHandler {

    public Date addDays(Date date, int days){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public int differenceInDays(Date firstDate, Date secondDate){
        long millisDif = firstDate.getTime() - secondDate.getTime();
        int days = (int) millisDif/86400000;
        if(days<0) days*=-1;
        return days;
    }


}
