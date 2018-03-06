package com.mobgen.gotmedia.app.di.categories;

import com.mobgen.gotmedia.app.di.common.FragmentScoped;
import com.mobgen.gotmedia.app.presentation.categories.CategoriesFragment;
import com.mobgen.gotmedia.app.presentation.categories.presenter.CategoriesContract;
import com.mobgen.gotmedia.app.presentation.categories.presenter.CategoriesPresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created on 3/5/18.
 */

@Module
public abstract class CategoriesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CategoriesFragment categoriesFragment();

    @FragmentScoped
    @Binds abstract CategoriesContract.CategoriesPresenter categoriesPresenter(CategoriesPresenterImpl categoriesPresenter);
}
