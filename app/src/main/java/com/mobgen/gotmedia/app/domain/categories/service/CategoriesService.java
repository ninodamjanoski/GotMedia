package com.mobgen.gotmedia.app.domain.categories.service;

import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface CategoriesService {
    Observable pollCategories();
}
