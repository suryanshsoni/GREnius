package com.tensai.grenius.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tensai.grenius.GREniusApplication;
import com.tensai.grenius.di.component.BroadcastReceiverComponent;
import com.tensai.grenius.di.component.DaggerBroadcastReceiverComponent;

/**
 * Created by Pavilion on 11-09-2017.
 */

public class BaseBroadcastReceiver extends BroadcastReceiver {

    BroadcastReceiverComponent broadcastReceiverComponent;

    @Override
    public void onReceive(Context context, Intent intent) {
        broadcastReceiverComponent=DaggerBroadcastReceiverComponent.builder().build();
    }
    public BroadcastReceiverComponent getBroadcastReceiverComponent() {
        return broadcastReceiverComponent;
    }
}
