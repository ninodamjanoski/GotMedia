package com.mobgen.gotmedia.app.data.categories.repository;

import com.mobgen.gotmedia.app.data.categories.CategoriesDto;
import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesCacheService;
import com.mobgen.gotmedia.app.domain.categories.service.CategoriesService;
import com.mobgen.gotmedia.app.entity.categories.CategoriesResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Action1;
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
        return categoriesCacheService.hasCategories()
                .flatMap(new Func1<Boolean, Observable<?>>() {
                    @Override
                    public Observable<?> call(Boolean hasCategoriesResults) {
                        if(!hasCategoriesResults){
                            return categoriesService.pollCategories()
                                    .map(new Func1<Response<List<CategoriesDto>>, Object>(){
                                        @Override
                                        public Object call(Response<List<CategoriesDto>> categoriesResults) {

                                            if(categoriesResults.code() == 200){
                                                categoriesCacheService.writeCategoriesInfo(categoriesResults.body());
                                                return categoriesResults.body();
                                            }
                                            return null;
                                        }
                                    });
                        }
                        return Observable.just(new ArrayList<>());
                    }
                });
    }

    @Override
    public Observable<List<CategoriesResult>> getCategories() {
        return categoriesCacheService.getCategories();
    }
}
