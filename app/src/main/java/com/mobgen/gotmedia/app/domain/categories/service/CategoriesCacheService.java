package com.mobgen.gotmedia.app.domain.categories.service;

import com.mobgen.gotmedia.app.data.categories.CategoriesDto;
import com.mobgen.gotmedia.app.entity.categories.Category;

import java.util.List;

import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface CategoriesCacheService {

    Observable<List<Category>> getCategories();
    Observable<Boolean> hasCategories();
    void writeCategoriesInfo(List<CategoriesDto> categoriesResult);
}
