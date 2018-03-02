package com.mobgen.gotmedia.app.data.categories.service;

import com.mobgen.gotmedia.app.domain.categories.service.CategoriesService;
import com.mobgen.gotmedia.core.utilities.rx.SchedulerProvider;

import javax.inject.Inject;

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
    public Observable pollCategories() {
        return gotPollService.pollCategories()
                .subscribeOn(schedulerProvider.getIo())
                .observeOn(schedulerProvider.getMain());
    }
}
