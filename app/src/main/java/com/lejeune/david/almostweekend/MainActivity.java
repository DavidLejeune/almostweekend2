package com.lejeune.david.almostweekend;

import android.app.Activity;
import android.icu.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    private TextView txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                countdown();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();






    }

    private void countdown(){

        txtDate = (TextView) findViewById(R.id.txtDate);


        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        calendar.setTime(now);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat s = new SimpleDateFormat("ss");
        SimpleDateFormat fullDayNameDF = new SimpleDateFormat("EEEE");
        SimpleDateFormat abbrDayNameDF = new SimpleDateFormat("E");
        //SimpleDateFormat intDayNameDF = calendar.get(Calendar.DAY_OF_WEEK);

        String formattedDate = df.format(now);
        String fullDayNameStr = fullDayNameDF.format(now);
        String abbrDayNameStr = abbrDayNameDF.format(now);
        String abbrMonthNameStr = (new SimpleDateFormat("MMM")).format(now);
        String fullMonthNameStr = (new SimpleDateFormat("MMMM")).format(now);

        int secondLeftInt = 60 - (Integer.parseInt(s.format(now)));
        int intDayNameStr = calendar.get(Calendar.DAY_OF_WEEK);

        txtDate.setText(formattedDate);
        txtDate.setText(txtDate.getText() + "\n" + fullDayNameStr);
        txtDate.setText(txtDate.getText() + "\n" + abbrDayNameStr);
        txtDate.setText(txtDate.getText() + "\n" + intDayNameStr);
        txtDate.setText(txtDate.getText() + "\n" + abbrMonthNameStr);
        txtDate.setText(txtDate.getText() + "\n" + fullMonthNameStr);

        Calendar nowC = Calendar.getInstance();
        int currentDay = nowC.get(Calendar.DAY_OF_WEEK);
        System.out.println(nowC.toString());

        // Settings for weekend and workweekstart
        int workStartDay = Calendar.MONDAY;
        int workStartHour = 8;
        int workStartMinute = 30;
        Calendar work = Calendar.getInstance();
        work.set(Calendar.DAY_OF_WEEK, workStartDay);
        work.set(Calendar.HOUR_OF_DAY, workStartHour);
        work.set(Calendar.MINUTE, workStartMinute);

        int weekendStartDay = Calendar.FRIDAY;
        int weekendStartHour = 15;
        int weekendStartMinute = 30;
        Calendar weekend = Calendar.getInstance();
        weekend.set(Calendar.HOUR_OF_DAY, weekendStartHour);
        weekend.set(Calendar.MINUTE, weekendStartMinute);
        weekend.add(Calendar.DAY_OF_YEAR, weekendStartDay - currentDay);



        // calulcating time to weekend from workweek start
        long millisLeft = weekend.getTimeInMillis() - nowC.getTimeInMillis() - 60000;
        // days + hours
        long hoursLeft = millisLeft  / (60 * 60 * 1000);
        long rawQuotient = hoursLeft;
        int remainderHours = (int) rawQuotient % 24;
        int nrDays = (int) (rawQuotient - remainderHours) / 24 ;
        // minutes
        long minutesLeft =  (millisLeft % (60 * 60 * 1000)) / (60 * 1000);


        //int remainderSeconds = (int) rawQuotient % 60;
        //int nrSeconds = (int) (rawQuotient - remainderSeconds) / 60;

        // outputting timeleft
        String timeleft = Integer.toString(nrDays) + " Days " +  Integer.toString(remainderHours) + " Hours " + Long.toString(minutesLeft) + " Minutes" ;
        txtDate.setText(txtDate.getText() + "\n" + "time left since start of workweek  : " + timeleft);
        txtDate.setText(txtDate.getText() + "\n" + "seconds  : " + Integer.toString(secondLeftInt));


        System.out.println("time left since start of workweek  : " + timeleft);
        System.out.println("Seconds : " + Integer.toString(secondLeftInt));


        // calulcating time to weekend from now
        millisLeft = weekend.getTimeInMillis() - nowC.getTimeInMillis() - 60000;
        // days + hours
        hoursLeft = millisLeft  / (60 * 60 * 1000);
        rawQuotient = hoursLeft;
        remainderHours = (int) rawQuotient % 24;
        nrDays = (int) (rawQuotient - remainderHours) / 24 ;
        // minutes
        minutesLeft =  (millisLeft % (60 * 60 * 1000)) / (60 * 1000);
        // outputting timeleft
        timeleft = Integer.toString(nrDays) + " Days " +  Integer.toString(remainderHours) + " Hours " + Long.toString(minutesLeft) + " Minutes" ;
        txtDate.setText(txtDate.getText() + "\n" + "time left since now  : " + timeleft);
        txtDate.setText(txtDate.getText() + "\n" + "seconds  : " + Integer.toString(secondLeftInt));

        System.out.println("time left since now  : " + timeleft);


    }
}
