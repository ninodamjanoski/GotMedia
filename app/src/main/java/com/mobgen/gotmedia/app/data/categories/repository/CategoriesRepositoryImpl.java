package com.mobgen.gotmedia.app.data.categories.repository;

import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesCacheService;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesService;
import com.mobgen.gotmedia.app.entity.categories.CategoriesResult;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created on 3/2/18.
 */

public class CategoriesRepositoryImpl implements CategoriesRepository {

    private final CategoriesService categoriesService;
    private final CategoriesCacheService categoriesCacheService;

    @Inject
    public CategoriesRepositoryImpl(CategoriesService categoriesService, CategoriesCacheService categoriesCacheService) {
        this.categoriesService = categoriesService;
        this.categoriesCacheService = categoriesCacheService;
    }

    @Override
    public Observable storeCategories() {
        return categoriesService.pollCategories()
                .map(new Func1<List<CategoriesResult>, Object>(){
            @Override
            public List<CategoriesResult> call(List<CategoriesResult> categoriesResults) {
                if(categoriesResults != null){
                    categoriesCacheService.writeCategoriesInfo(categoriesResults);
                }
                return categoriesResults;
            }
        });
    }

    @Override
    public Observable<List<CategoriesResult>> getCategories() {
        return categoriesCacheService.getCategories();
    }
}
