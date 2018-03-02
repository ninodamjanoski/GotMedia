package com.mobgen.gotmedia.app.data.categories.service;

import com.mobgen.gotmedia.app.data.categories.CategoriesDto;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface GotPollService {

    @GET("index")
    Observable<Response<List<CategoriesDto>>> pollCategories();
}
