package com.mobgen.gotmedia.app.data.categories.service;

import com.mobgen.gotmedia.app.entity.categories.Book;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.entity.categories.Character;
import com.mobgen.gotmedia.app.entity.categories.House;

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
    Observable<Response<List<Category>>> pollCategories();

    @GET("{category}")
    Observable<Response<List<Book>>> pollBooks(@Path(value = "category", encoded = true) String category);


    @GET("{category}")
    Observable<Response<List<House>>> pollHouses(@Path(value = "category", encoded = true) String category);

    @GET("{category}")
    Observable<Response<List<Character>>> pollCharacters(@Path(value = "category", encoded = true) String category);

    @GET("{category}")
    Observable<Response<List<Book>>> pollCategory(@Path(value = "category", encoded = true) String category,
                                                           @Query("_page") int page, @Query("_limit") int limit);


}
