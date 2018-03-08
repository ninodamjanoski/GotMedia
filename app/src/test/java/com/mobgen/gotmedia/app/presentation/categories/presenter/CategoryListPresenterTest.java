package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.entity.categories.MockCategoriesResult;
import com.mobgen.gotmedia.app.presentation.categories.CategoryListFragment;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 3/8/18.
 */
public class CategoryListPresenterTest {

    @Mock
    private CategoryListFragment categoriesFragment;

    private CategoriesPresenterImpl presenter;

    @Mock
    private CategoriesRepository categoriesRepository;

    private List<Category> categories;

    @Before
    public void setUpPresenter(){
        MockitoAnnotations.initMocks(this);

        categories = MockCategoriesResult.getInstance();

        presenter = new CategoriesPresenterImpl(categoriesRepository);
    }

}