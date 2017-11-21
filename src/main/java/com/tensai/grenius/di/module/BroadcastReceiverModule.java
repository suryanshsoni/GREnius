package com.tensai.grenius.di.module;

import android.content.BroadcastReceiver;

import dagger.Module;

/**
 * Created by Pavilion on 11-09-2017.
 */
@Module
public class BroadcastReceiverModule {
    private final BroadcastReceiver mReceiver;

    public BroadcastReceiverModule(BroadcastReceiver receiver) {
        mReceiver = receiver;
    }
}
