package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.entity.categories.MockCategoriesResult;
import com.mobgen.gotmedia.app.presentation.categories.CategoriesFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created on 3/6/18.
 */
public class CategoriesPresenterTest {


    @Mock
    private CategoriesFragment categoriesFragment;

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

    @Test
    public void visualizeDataTest() throws Exception {

        when(categoriesRepository.getCategories())
                .thenReturn(Observable.just(categories));

        presenter.takeView(categoriesFragment);

        ArgumentCaptor<List> itemsResultArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(categoriesFragment).showData(itemsResultArgumentCaptor.capture());

        assertTrue(itemsResultArgumentCaptor.getValue().size() == MockCategoriesResult.ITEMS_SIZE);
    }

    @Test
    public void noCacheNoNetworkVisualizeDataTest() throws Exception {

        when(categoriesRepository.getCategories())
                .thenReturn(Observable.<List<Category>>just(null));

        presenter.takeView(categoriesFragment);

        ArgumentCaptor<List> itemsResultArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(categoriesFragment).showData(itemsResultArgumentCaptor.capture());

        assertTrue(itemsResultArgumentCaptor.getValue().size() == 0);
    }
}