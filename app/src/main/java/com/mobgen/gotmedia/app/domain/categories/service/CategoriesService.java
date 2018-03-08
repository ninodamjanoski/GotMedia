package com.mobgen.gotmedia.app.domain.categories.service;

import com.mobgen.gotmedia.app.data.categories.CategoriesDto;
import com.mobgen.gotmedia.app.entity.categories.Book;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface CategoriesService {
    Observable<Response<List<CategoriesDto>>> pollCategories();
    Observable<Response<List<Book>>> getCategory(String url, int page, int pageSize);
}
