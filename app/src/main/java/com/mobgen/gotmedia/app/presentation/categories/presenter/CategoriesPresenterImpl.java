package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.CategoriesResult;
import com.mobgen.gotmedia.app.presentation.categories.CategoriesFragment;
import com.mobgen.gotmedia.app.presentation.categories.pojo.CategoryItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.http.PUT;
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
                .map(new Func1<List<CategoriesResult>, List<Object>>() {
                    @Override
                    public List<Object> call(List<CategoriesResult> categoriesResults) {
                        List<Object> items = null;
                        if(categoriesResults != null || categoriesResults.size() > 0){
                            items = prepareItems(categoriesResults);
                        }
                        return items;
                    }
                })
                .subscribe(new Subscriber<List<Object>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Object> categoriesResults) {
                        if(categoriesResults == null || categoriesResults.size() == 0){
                            fragment.updateState(true);
                            return;
                        }
                        fragment.updateState(false);
                        fragment.showData(categoriesResults);
                    }
                });
    }

    private List<Object> prepareItems(List<CategoriesResult> categoriesResults) {
        List<Object> items = new ArrayList<>(categoriesResults.size());
        for(CategoriesResult result : categoriesResults){
            items.add(new CategoryItem(result.getId(), result.getTitle(), result.getHref()));
        }
        return items;
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void dropView() {

    }
}
