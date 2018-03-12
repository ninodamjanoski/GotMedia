package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.enums.CategoryType;
import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.presentation.categories.CategoriesFragment;
import com.mobgen.gotmedia.core.utilities.EspressoIdlingResource;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

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

        EspressoIdlingResource.increment();
        categoriesRepository.getCategories()
                .subscribe(new Subscriber<List<? extends Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }

                    @Override
                    public void onNext(List<? extends Object> categories) {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
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
        CategoryType type = CategoryType.fromString(data.getTitle());
        if(type.equals(CategoryType.UNKNOWN)){
            fragment.handleUnknownType();
            return;
        }
        fragment.showCategoryListFragment(data);
    }

    @Override
    public void dropView() {

    }
}
