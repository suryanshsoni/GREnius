package com.tensai.grenius.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tensai.grenius.services.NotificationService1;

/**
 * Created by rishabhpanwar on 23/08/17.
 */

public class AlarmReceiverMain extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, NotificationService1.class);
        service1.setData((Uri.parse("custom://"+System.currentTimeMillis())));
        context.startService(service1);
    }
}
