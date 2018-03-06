package com.mobgen.gotmedia.app.di.application;



import com.mobgen.gotmedia.app.di.GotCommonModule;
import com.mobgen.gotmedia.app.di.categories.CategoriesModule;
import com.mobgen.gotmedia.app.di.common.ActivityScoped;
import com.mobgen.gotmedia.app.di.splash.SplashModule;
import com.mobgen.gotmedia.presentation.GotMediaActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = {SplashModule.class, CategoriesModule.class, GotCommonModule.class})
    abstract GotMediaActivity myCarMediaActivity();
}
