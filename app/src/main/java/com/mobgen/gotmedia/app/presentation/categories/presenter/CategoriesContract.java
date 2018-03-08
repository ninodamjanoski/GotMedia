package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.presentation.categories.CategoriesFragment;
import com.mobgen.gotmedia.app.presentation.categories.CategoryListFragment;
import com.mobgen.gotmedia.core.base.BasePresenter;

/**
 * Created on 3/5/18.
 */

public class CategoriesContract {


    public interface CategoriesPresenter extends BasePresenter<CategoriesFragment> {

        void visualizeData();

        void onItemSelected(Category data);
    }

    public interface CategoryListPresenter extends BasePresenter<CategoryListFragment> {

        void visualizeData(Category url, int size);
    }


}
