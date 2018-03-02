package com.mobgen.gotmedia.app.presentation;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.mobgen.gotmedia.app.di.application.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created on 3/1/18.
 */

public class GotMediaApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
