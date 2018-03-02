package com.mobgen.gotmedia.app.di;

import android.content.Context;

import com.mobgen.gotmedia.BuildConfig;
import com.mobgen.gotmedia.app.di.common.ActivityScoped;
import com.mobgen.gotmedia.app.entity.categories.DaoMaster;
import com.mobgen.gotmedia.app.entity.categories.DaoSession;
import com.mobgen.gotmedia.core.utilities.rx.SchedulerProvider;
import com.mobgen.gotmedia.core.utilities.rx.SchedulerProviderImpl;

import org.greenrobot.greendao.database.Database;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created on 3/1/18.
 */

@Module
public abstract class GotCommonModule {


    @Provides
    @ActivityScoped
    static Retrofit provideServiceRetrofit() {
        OkHttpClient httpClient = new OkHttpClient()
                .newBuilder()
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient).build();
    }

    @Provides
    @ActivityScoped
    static SchedulerProvider provideScheduler(){
        return new SchedulerProviderImpl();
    }

    @Provides
    @ActivityScoped
    static DaoSession provideDaoSession(Context context){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "gotic.db");
        Database database = devOpenHelper.getWritableDb();
        return new DaoMaster(database).newSession();
    }
}
