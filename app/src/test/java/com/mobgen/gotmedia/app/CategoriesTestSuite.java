package com.mobgen.gotmedia.app;

import com.mobgen.gotmedia.app.presentation.categories.presenter.CategoriesPresenterTest;
import com.mobgen.gotmedia.app.presentation.categories.presenter.CategoryListPresenterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 2/26/18.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CategoriesPresenterTest.class,
        CategoryListPresenterTest.class
})
public class CategoriesTestSuite {
}
