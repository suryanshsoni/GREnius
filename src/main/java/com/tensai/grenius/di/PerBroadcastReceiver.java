package com.tensai.grenius.di;

/**
 * Created by Pavilion on 11-09-2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

@Scope
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PerBroadcastReceiver {
}
