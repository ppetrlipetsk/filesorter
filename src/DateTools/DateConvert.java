package DateTools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateConvert {
    public static  int getYear(Date t){
        Calendar c= Calendar.getInstance();
        c.setTime(t);
        return c.get(Calendar.YEAR);
    }

    public static  String getMonthString(Date t){
        Calendar c= Calendar.getInstance();
        c.setTime(t);
        SimpleDateFormat f= new SimpleDateFormat("MMMM", new Locale("ru"));
        String month=f.format(t.getTime());
        return month;
    }
}
