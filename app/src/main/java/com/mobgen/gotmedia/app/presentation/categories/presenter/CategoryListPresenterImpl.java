package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.presentation.categories.CategoryListFragment;
import com.mobgen.gotmedia.app.presentation.categories.pojo.CategoryItem;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created on 3/7/18.
 */

public class CategoryListPresenterImpl implements CategoriesContract.CategoryListPresenter {

    public static final int PAGE_SIZE = 2;
    private final CategoriesRepository categoriesRepository;
    private CategoryListFragment fragment;

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
    public void visualizeData(CategoryItem item, int size) {
        fragment.updateState(false);
        int page = (size / PAGE_SIZE) + 1;
        String url = item.getUrl().substring(1, item.getUrl().length());
        categoriesRepository.getCategory(url, page, PAGE_SIZE)
        .subscribe(new Subscriber<List<? extends Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                fragment.updateState(true);
            }

            @Override
            public void onNext(List<? extends Object> objects) {
                if(objects == null || objects.isEmpty()){
                    fragment.updateState(true);
                    return;
                }
                fragment.updateState(false);
                fragment.showData((List<Object>) objects);
            }
        });
    }
}
