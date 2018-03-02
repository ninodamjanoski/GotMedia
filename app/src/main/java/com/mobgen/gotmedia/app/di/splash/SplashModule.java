package com.mobgen.gotmedia.app.di.splash;

import com.mobgen.gotmedia.app.data.categories.repository.CategoriesRepositoryImpl;
import com.mobgen.gotmedia.app.data.categories.service.CategoriesCacheServiceImpl;
import com.mobgen.gotmedia.app.data.categories.service.CategoriesServiceImpl;
import com.mobgen.gotmedia.app.data.categories.service.GotPollService;
import com.mobgen.gotmedia.app.di.common.ActivityScoped;
import com.mobgen.gotmedia.app.di.common.FragmentScoped;
import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesCacheService;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesService;
import com.mobgen.gotmedia.app.presentation.splash.SplashFragment;
import com.mobgen.gotmedia.app.presentation.splash.presenter.SplashContract;
import com.mobgen.gotmedia.app.presentation.splash.presenter.SplashPresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import retrofit2.Retrofit;

/**
 * Created on 3/1/18.
 */
@Module
public abstract class SplashModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();

    @FragmentScoped
    @Binds abstract SplashContract.SplashPresenter splashPresenter(SplashPresenterImpl splashPresenter);

    @Binds
    @ActivityScoped
    abstract CategoriesService categoriesService(CategoriesServiceImpl categoriesService);

    @Binds
    @ActivityScoped
    abstract CategoriesCacheService categoriesCacheService(CategoriesCacheServiceImpl categoriesCacheService);

    @Binds
    @ActivityScoped
    abstract CategoriesRepository categoriesRepository(CategoriesRepositoryImpl categoriesRepository);

    @Provides
    @ActivityScoped
    static GotPollService gotPollService(Retrofit baseService){
        return baseService.create(GotPollService.class);
    }


}
