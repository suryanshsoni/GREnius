package com.tensai.grenius.di.component;

import com.tensai.grenius.di.PerService;
import com.tensai.grenius.di.module.ServiceModule;
import com.tensai.grenius.services.NotificationService1;
import com.tensai.grenius.services.NotificationServiceRemember;

import dagger.Component;

/**
 * Created by Pavilion on 23-08-2017.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(NotificationService1 service);
    void inject(NotificationServiceRemember service);
}