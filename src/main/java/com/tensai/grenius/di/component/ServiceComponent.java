package com.tensai.grenius.di.component;

import com.tensai.grenius.di.PerService;
import com.tensai.grenius.di.module.ServiceModule;
import com.tensai.grenius.services.NotificationService1;

import dagger.Component;

/**
 * Created by Pavilion on 23-08-2017.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(NotificationService1 service);

}