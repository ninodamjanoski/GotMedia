package com.mobgen.gotmedia.app.data.categories.service;

import com.mobgen.gotmedia.app.domain.categories.service.CategoriesService;
import com.mobgen.gotmedia.app.entity.categories.Book;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.entity.categories.Character;
import com.mobgen.gotmedia.app.entity.categories.House;
import com.mobgen.gotmedia.core.utilities.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created on 3/2/18.
 */

public class CategoriesServiceImpl implements CategoriesService {

    private final GotPollService gotPollService;
    private final  SchedulerProvider schedulerProvider;

    @Inject
    public CategoriesServiceImpl(GotPollService gotPollService, SchedulerProvider schedulerProvider) {
        this.gotPollService = gotPollService;
        this.schedulerProvider = schedulerProvider;
    }



    @Override
    public Observable<Response<List<Category>>> pollCategories() {
        return gotPollService.pollCategories()
                .subscribeOn(schedulerProvider.getIo())
                .observeOn(schedulerProvider.getMain());
    }

    @Override
    public Observable<Response<List<Book>>> getBooks(String url, int page, int pageSize) {
        return gotPollService.pollBooks(url, page, pageSize)
                .subscribeOn(schedulerProvider.getIo())
                .observeOn(schedulerProvider.getMain());
    }


    @Override
    public Observable<Response<List<House>>> getHouses(String url, int page, int pageSize) {
        return gotPollService.pollHouses(url, page, pageSize)
                .subscribeOn(schedulerProvider.getIo())
                .observeOn(schedulerProvider.getMain());
    }

    @Override
    public Observable<Response<List<Character>>> getCharacters(String url, int page, int pageSize) {
        return gotPollService.pollCharacters(url, page, pageSize)
                .subscribeOn(schedulerProvider.getIo())
                .observeOn(schedulerProvider.getMain());
    }
}
