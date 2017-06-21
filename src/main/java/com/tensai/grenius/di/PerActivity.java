package com.tensai.grenius.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by Pavilion on 20-06-2017.
 */
@Qualifier @Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
