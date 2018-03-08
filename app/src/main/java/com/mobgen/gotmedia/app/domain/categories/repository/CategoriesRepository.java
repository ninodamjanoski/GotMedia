package com.mobgen.gotmedia.app.domain.categories.repository;

import com.mobgen.gotmedia.app.entity.categories.Category;

import java.util.List;

import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface CategoriesRepository {
    Observable storeCategories();
    Observable<List<Category>> getCategories();
    Observable<List<? extends Object>> getCategory(String url, int from, int pageSize);

}
