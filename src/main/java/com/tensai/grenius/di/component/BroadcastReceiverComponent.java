package com.tensai.grenius.di.component;

import com.tensai.grenius.di.PerBroadcastReceiver;
import com.tensai.grenius.di.module.BroadcastReceiverModule;
import com.tensai.grenius.receivers.AlarmReceiver;

import dagger.Component;

/**
 * Created by Pavilion on 11-09-2017.
 */
@PerBroadcastReceiver
@Component(dependencies = ApplicationComponent.class, modules = BroadcastReceiverModule.class)
public interface BroadcastReceiverComponent {
    void inject(AlarmReceiver alarmReceiver);
}
