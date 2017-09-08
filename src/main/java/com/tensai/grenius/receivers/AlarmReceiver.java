package com.tensai.grenius.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.tensai.grenius.services.NotificationService1;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by rishabhpanwar on 22/08/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private PendingIntent pendingIntent,pendingIntentRemember;
    AlarmManager alarmManager,alarmManagerRemember;
    Intent alarmIntent,alarmIntentRemember;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ABCDEF:","in alarm receiver");

            alarmIntent = new Intent(context, AlarmReceiverMain.class);

        //Intent alarmIntent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,16);
        calendar.set(Calendar.MINUTE, 46);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000*90, pendingIntent);


        alarmManagerRemember = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmIntentRemember = new Intent(context, AlarmReceiverRemember.class);

        Intent alarmIntentRemember = new Intent(context, AlarmReceiverRemember.class);
        pendingIntentRemember = PendingIntent.getBroadcast(context, 0, alarmIntentRemember, 0);


        Calendar calendarRemember = Calendar.getInstance();
        calendarRemember.setTimeInMillis(System.currentTimeMillis());
        calendarRemember.set(Calendar.HOUR_OF_DAY, 16);
        calendarRemember.set(Calendar.MINUTE,46);
        Log.d("Notif: ",calendarRemember.getTimeInMillis()+"");
        alarmManagerRemember.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000*60,pendingIntent);
    }
}
