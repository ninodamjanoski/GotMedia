package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.presentation.categories.CategoriesFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created on 3/5/18.
 */

public class CategoriesPresenterImpl implements CategoriesContract.CategoriesPresenter {


    private CategoriesRepository categoriesRepository;
    private CategoriesFragment fragment;

    @Inject
    public CategoriesPresenterImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void takeView(CategoriesFragment view) {
        fragment = view;
        visualizeData();
    }

    public void visualizeData() {
        categoriesRepository.getCategories()
                .subscribe(new Subscriber<List<? extends Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<? extends Object> categories) {
                        if(categories == null || categories.size() == 0){
                            fragment.updateState(true);
                            return;
                        }
                        fragment.updateState(false);
                        fragment.showData(categories);
                    }
                });
    }

    @Override
    public void onItemSelected(Category data) {
        fragment.showCategoryListFragment(data);
    }

    @Override
    public void dropView() {

    }
}
