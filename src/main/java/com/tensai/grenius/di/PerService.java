package com.tensai.grenius.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by Pavilion on 23-08-2017.
 */

@Scope
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}

