package com.mobgen.gotmedia.app.entity.categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/6/18.
 */
public class MockCategoriesResult {

    private static List<Category> INSTANCE;
    public static final int ITEMS_SIZE = 3;

    public static List<Category> getInstance(){
        if(INSTANCE == null){
            INSTANCE = getMockCategories();
        }
        return INSTANCE;
    }


    private static List<Category> getMockCategories() {

        List<Category> items = new ArrayList<>(ITEMS_SIZE);
        Category categoriesResult = new Category("1", "Books", "\"/api1/list1");
        items.add(categoriesResult);
        categoriesResult = new Category("2", "Houses", "\"/api1/list2");
        items.add(categoriesResult);
        categoriesResult = new Category("3", "Characters", "\"/api1/list3");
        items.add(categoriesResult);

        return items;
    }
}