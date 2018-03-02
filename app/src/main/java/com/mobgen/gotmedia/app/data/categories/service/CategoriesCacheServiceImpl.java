package com.mobgen.gotmedia.app.data.categories.service;

import com.mobgen.gotmedia.app.domain.categories.service.CategoriesCacheService;
import com.mobgen.gotmedia.app.entity.categories.CategoriesResult;
import com.mobgen.gotmedia.app.entity.categories.CategoriesResultDao;
import com.mobgen.gotmedia.app.entity.categories.DaoSession;
import com.mobgen.gotmedia.core.utilities.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created on 3/2/18.
 */

public class CategoriesCacheServiceImpl implements CategoriesCacheService {


    private final DaoSession daoSession;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public CategoriesCacheServiceImpl(DaoSession daoSession, SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.daoSession = daoSession;
    }

    @Override
    public Observable<List<CategoriesResult>> getCategories() {
        return Observable.fromCallable(new Callable<List<CategoriesResult>>() {
            @Override
            public List<CategoriesResult> call() throws Exception {
                return daoSession.getCategoriesResultDao().loadAll();
            }
        })
        .subscribeOn(schedulerProvider.getIo())
        .observeOn(schedulerProvider.getMain());
    }

    @Override
    public void writeCategoriesInfo(List<CategoriesResult> categoriesResult) {
        CategoriesResultDao categoriesResultDao = daoSession.getCategoriesResultDao();
        for(CategoriesResult result : categoriesResult){
            categoriesResultDao.insertOrReplace(result);
        }
    }
}
