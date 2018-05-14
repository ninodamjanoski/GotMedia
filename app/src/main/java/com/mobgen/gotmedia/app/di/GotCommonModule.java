package com.mobgen.gotmedia.app.di;

import android.content.Context;

import com.mobgen.gotmedia.BuildConfig;
import com.mobgen.gotmedia.app.data.categories.repository.CategoriesRepositoryImpl;
import com.mobgen.gotmedia.app.data.categories.service.CategoriesCacheServiceImpl;
import com.mobgen.gotmedia.app.data.categories.service.CategoriesServiceImpl;
import com.mobgen.gotmedia.app.data.categories.service.GotPollService;
import com.mobgen.gotmedia.app.di.common.ActivityScoped;
import com.mobgen.gotmedia.app.di.common.FragmentScoped;
import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesCacheService;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesService;
import com.mobgen.gotmedia.app.entity.categories.DaoMaster;
import com.mobgen.gotmedia.app.entity.categories.DaoSession;
import com.mobgen.gotmedia.app.presentation.view.fragment.GotCategoriesParentFragment;
import com.mobgen.gotmedia.core.net.HttpClient;
import com.mobgen.gotmedia.core.utilities.rx.SchedulerProvider;
import com.mobgen.gotmedia.core.utilities.rx.SchedulerProviderImpl;

import org.greenrobot.greendao.database.Database;


import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created on 3/1/18.
 */

@Module
public abstract class GotCommonModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract GotCategoriesParentFragment gotCategoriesParentFragment();

    @Provides
    @ActivityScoped
    static Retrofit provideServiceRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient).build();
    }

    @Provides
    @ActivityScoped
    static OkHttpClient provideOkHttpClient(){
        return HttpClient.getAllCertsClient();
    }

    @Provides
    @ActivityScoped
    static SchedulerProvider provideScheduler(){
        return new SchedulerProviderImpl();
    }

    @Provides
    @ActivityScoped
    static GotPollService gotPollService(Retrofit baseService){
        return baseService.create(GotPollService.class);
    }

    @Provides
    @ActivityScoped
    static DaoSession provideDaoSession(Context context){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "gotic.db");
        Database database = devOpenHelper.getWritableDb();
        return new DaoMaster(database).newSession();
    }
    
    @Binds
    @ActivityScoped
    abstract CategoriesService categoriesService(CategoriesServiceImpl categoriesService);

    @Binds
    @ActivityScoped
    abstract CategoriesCacheService categoriesCacheService(CategoriesCacheServiceImpl categoriesCacheService);

    @Binds
    @ActivityScoped
    abstract CategoriesRepository categoriesRepository(CategoriesRepositoryImpl categoriesRepository);

}
