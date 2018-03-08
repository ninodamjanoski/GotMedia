package com.mobgen.gotmedia.app.domain.categories.service;

import com.mobgen.gotmedia.app.entity.categories.Book;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.entity.categories.Character;
import com.mobgen.gotmedia.app.entity.categories.House;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created on 3/2/18.
 */

public interface CategoriesService {
    Observable<Response<List<Category>>> pollCategories();
    Observable<Response<List<Book>>> getBooks(String url, int page, int pageSize);
    Observable<Response<List<House>>> getHouses(String url, int page, int pageSize);
    Observable<Response<List<Character>>> getCharacters(String url, int page, int pageSize);
}
