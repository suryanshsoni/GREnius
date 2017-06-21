package com.tensai.grenius.di.component;

import com.tensai.grenius.MainActivity;
import com.tensai.grenius.di.PerActivity;
import com.tensai.grenius.di.module.ActivityModule;

import dagger.Component;
/**
 * Created by Pavilion on 20-06-2017.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
