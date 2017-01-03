package com.example.ok.madicalalatheer.DateFormatein;

/**
 * Created by ok on 01/01/2017.
 */

import android.support.v7.app.AppCompatActivity;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HajreDate extends AppCompatActivity {
    public String Dateout(String change) {
        try {
            Chronology iso = ISOChronology.getInstanceUTC();
            Chronology hijri = IslamicChronology.getInstanceUTC();
            String Date1[] =
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(Integer.parseInt(change) * 1000L)).split(" ");
            String Date2[] = Date1[0].split("/");
            LocalDate todayIso = new LocalDate(Integer.parseInt(Date2[2]), Integer.parseInt(Date2[1]), Integer.parseInt(Date2[0]), iso);
            LocalDate todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay(),
                    hijri);
            String out = todayHijri + "";
            String out1 = out.replace("-", "/");
            return out1;
        } catch (Exception ex) {

            return change;
        }


    }

    public Long toJulianDate() {
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));
        long time = (cal.getTimeInMillis() / 1000) ;

        return time;

    }

}