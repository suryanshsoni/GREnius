package com.tensai.grenius.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;

import com.tensai.grenius.services.NotificationService1;

/**
 * Created by rishabhpanwar on 23/08/17.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable()) {
            Intent service1 = new Intent(context, NotificationService1.class);
            service1.setData((Uri.parse("custom://"+System.currentTimeMillis())));
            context.startService(service1);

            Log.d("Network Available ", "Flag No 1");
        }
    }
}