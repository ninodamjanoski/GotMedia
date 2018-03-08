package com.mobgen.gotmedia.app.domain.categories.repository;

import com.mobgen.gotmedia.app.domain.categories.enums.CategoryType;
import com.mobgen.gotmedia.app.entity.categories.Category;

import java.util.List;

import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface CategoriesRepository {
    Observable storeCategories();
    Observable<List<? extends Object>> getCategories();
    Observable<List<? extends Object>> getCategory(String url, CategoryType type, int from, int pageSize);

}
