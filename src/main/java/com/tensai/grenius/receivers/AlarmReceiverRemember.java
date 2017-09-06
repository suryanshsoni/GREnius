package com.tensai.grenius.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tensai.grenius.services.NotificationService1;
import com.tensai.grenius.services.NotificationServiceRemember;

/**
 * Created by Pavilion on 06-09-2017.
 */

public class AlarmReceiverRemember extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, NotificationServiceRemember.class);
        service1.setData((Uri.parse("custom://"+System.currentTimeMillis())));
        context.startService(service1);
    }
}
