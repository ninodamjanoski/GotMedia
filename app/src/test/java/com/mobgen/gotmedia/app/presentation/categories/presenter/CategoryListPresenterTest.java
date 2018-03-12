package com.mobgen.gotmedia.app.presentation.categories.presenter;

import com.mobgen.gotmedia.app.domain.categories.enums.CategoryType;
import com.mobgen.gotmedia.app.domain.categories.repository.CategoriesRepository;
import com.mobgen.gotmedia.app.entity.categories.Book;
import com.mobgen.gotmedia.app.entity.categories.Category;
import com.mobgen.gotmedia.app.entity.categories.MockBooksResult;
import com.mobgen.gotmedia.app.entity.categories.MockCategoriesResult;
import com.mobgen.gotmedia.app.presentation.categories.CategoryListFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created on 3/8/18.
 */
public class CategoryListPresenterTest {

    public static final int PAGE_SIZE = 5;
    @Mock
    private CategoryListFragment categoriesListFragment;

    private CategoryListPresenterImpl presenter;

    @Mock
    private CategoriesRepository categoriesRepository;

    private List<? extends Object> books;
    private List<Category> categories;

    @Before
    public void setUpPresenter(){
        MockitoAnnotations.initMocks(this);

        categories = MockCategoriesResult.getInstance();

        books = MockBooksResult.getInstance();

        presenter = new CategoryListPresenterImpl(categoriesRepository);

        presenter.takeView(categoriesListFragment);
    }

    @Test
    public void visualizeBooksDataTest() throws Exception {
        Category category = categories.get(0);
        CategoryType type = CategoryType.fromString(category.getTitle());
        int currItemSize = 0;
        int page = presenter.getPage(currItemSize);
        String url = category.getHref().substring(1, category.getHref().length());
        when(categoriesRepository.getCategory(url, type, page, PAGE_SIZE))
                .thenReturn(Observable.<List<? extends Object>>just(books));

        presenter.visualizeData(category, currItemSize);

        ArgumentCaptor<List> itemsResultArgumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(categoriesListFragment).showData(itemsResultArgumentCaptor.capture());

        assertTrue(itemsResultArgumentCaptor.getValue().size() == MockBooksResult.ITEMS_SIZE);
    }

    @Test
    public void visualizeBooksDataNoNetworkTest() throws Exception {
        Category category = categories.get(0);
        CategoryType type = CategoryType.fromString(category.getTitle());
        int currItemSize = 0;
        int page = presenter.getPage(currItemSize);
        String url = category.getHref().substring(1, category.getHref().length());
        when(categoriesRepository.getCategory(url, type, page, PAGE_SIZE))
                .thenReturn(Observable.<List<? extends Object>>just(new ArrayList()));

        presenter.visualizeData(category, currItemSize);

        ArgumentCaptor<Boolean> isErrorArgumentCaptor = ArgumentCaptor.forClass(Boolean.class);

        verify(categoriesListFragment, atLeast(1)).updateState(isErrorArgumentCaptor.capture());

        assertTrue(isErrorArgumentCaptor.getValue());
    }
}