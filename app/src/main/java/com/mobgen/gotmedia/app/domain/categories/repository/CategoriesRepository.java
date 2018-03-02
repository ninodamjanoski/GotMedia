package com.mobgen.gotmedia.app.domain.categories.repository;

import com.mobgen.gotmedia.app.entity.categories.CategoriesResult;

import java.util.List;

import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface CategoriesRepository {
    Observable<List<CategoriesResult>> getCategories();
}
