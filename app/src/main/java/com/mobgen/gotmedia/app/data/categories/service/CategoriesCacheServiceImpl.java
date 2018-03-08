package com.mobgen.gotmedia.app.data.categories.service;

import com.mobgen.gotmedia.app.data.categories.CategoriesDto;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesCacheService;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.entity.categories.CategoryDao;
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
    public Observable<List<? extends Object>> getCategories() {
        return Observable.fromCallable(new Callable<List<? extends Object>>() {
            @Override
            public List<? extends Object> call() throws Exception {
                CategoryDao categoriesDao = daoSession.getCategoryDao();
                return categoriesDao.queryBuilder().orderAsc(CategoryDao.Properties.Title).list();
            }
        })
        .subscribeOn(schedulerProvider.getIo())
        .observeOn(schedulerProvider.getMain());
    }

    @Override
    public Observable<Boolean> hasCategories() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return daoSession.getCategoryDao().loadByRowId(1) != null;
            }
        })
                .subscribeOn(schedulerProvider.getIo())
                .observeOn(schedulerProvider.getMain());
    }

    @Override
    public void writeCategoriesInfo(List<CategoriesDto> categoriesDtos) {
        CategoryDao categoryDao = daoSession.getCategoryDao();
        for(CategoriesDto result : categoriesDtos){
            Category categoriesResult = new Category(result.getId(), result.getTitle(), result.getHref());
            categoryDao.insertOrReplace(categoriesResult);
        }
    }
}
