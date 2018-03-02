package com.mobgen.gotmedia.app.presentation.splash.presenter;

import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.CategoriesResult;
import com.mobgen.gotmedia.app.presentation.splash.SplashFragment;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created on 3/1/18.
 */

public class SplashPresenterImpl implements SplashContract.SplashPresenter {

    private SplashFragment fragment;

    private CategoriesRepository categoriesRepository;

    @Inject
    public SplashPresenterImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void takeView(SplashFragment fragment) {
        this.fragment = fragment;
        pollCategories();
    }

    private void pollCategories() {
        categoriesRepository.storeCategories()
                .subscribe(new Subscriber<List<CategoriesResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<CategoriesResult> categoriesResults) {
                        fragment.openCategories();
                    }
                });
    }

    @Override
    public void dropView() {
        fragment = null;
    }
}
