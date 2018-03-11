package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.enums.CategoryType;
import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.presentation.categories.CategoryListFragment;
import com.mobgen.gotmedia.core.utilities.EspressoIdlingResource;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created on 3/7/18.
 */

public class CategoryListPresenterImpl implements CategoriesContract.CategoryListPresenter {

    public static final int PAGE_SIZE = 5;
    private final CategoriesRepository categoriesRepository;
    private CategoryListFragment fragment;
    private boolean isReachedEnd;

    @Inject
    public CategoryListPresenterImpl(CategoriesRepository categoriesRepository){
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void takeView(CategoryListFragment view) {
        fragment = view;
    }

    @Override
    public void dropView() {
        fragment = null;
    }

    @Override
    public void visualizeData(Category item, final int size) {

        EspressoIdlingResource.increment();
        fragment.updateState(false);
        int page = (size / PAGE_SIZE) + 1;
        CategoryType type = CategoryType.fromString(item.getTitle());
        String url = item.getHref().substring(1, item.getHref().length());
        categoriesRepository.getCategory(url, type, page, PAGE_SIZE)
        .subscribe(new Subscriber<List<? extends Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement(); // Set app as idle.
                }
                fragment.updateState(true);
            }

            @Override
            public void onNext(List<? extends Object> objects) {
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement(); // Set app as idle.
                }
                isReachedEnd = size > 0 && objects.size() < PAGE_SIZE;
                if(isReachedEnd){
                    fragment.setReachedEnd(isReachedEnd);
                }else if(objects == null || objects.isEmpty()){
                    fragment.updateState(true);
                    return;
                }
                fragment.updateState(false);
                fragment.showData((List<Object>) objects);
            }
        });
    }
}
