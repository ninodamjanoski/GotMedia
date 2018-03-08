package com.mobgen.gotmedia.app.data.categories.service;

import com.mobgen.gotmedia.app.data.categories.CategoriesDto;
import com.mobgen.gotmedia.app.entity.categories.Book;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface GotPollService {

    @GET("api1/index")
    Observable<Response<List<CategoriesDto>>> pollCategories();

    @GET("{category}")
    Observable<Response<List<Book>>> pollCategory(@Path(value = "category", encoded = true) String category);

    @GET("{category}")
    Observable<Response<List<CategoriesDto>>> pollCategory(@Path(value = "category", encoded = true) String category,
                                                           @Query("_page") int page, @Query("_limit") int limit);


}
