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

    Intent alarmIntent;
    PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ABCDEF:","in alarm receiver");

            alarmIntent = new Intent(context, AlarmReceiverMain.class);

        //Intent alarmIntent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,12);
        calendar.set(Calendar.MINUTE, 55);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000*60*60*24, pendingIntent);
        Log.i("ABCDEF:","in if_alarm");
    }
}
