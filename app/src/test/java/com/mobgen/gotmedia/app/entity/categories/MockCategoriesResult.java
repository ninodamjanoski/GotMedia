package com.mobgen.gotmedia.app.entity.categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/6/18.
 */
public class MockCategoriesResult {

    private static List<CategoriesResult> INSTANCE;
    public static final int ITEMS_SIZE = 3;

    public static List<CategoriesResult> getInstance(){
        if(INSTANCE == null){
            INSTANCE = getMockCategories();
        }
        return INSTANCE;
    }


    private static List<CategoriesResult> getMockCategories() {

        List<CategoriesResult> items = new ArrayList<>(ITEMS_SIZE);
        CategoriesResult categoriesResult = new CategoriesResult("1", "Books", "\"/api1/list1");
        items.add(categoriesResult);
        categoriesResult = new CategoriesResult("2", "Houses", "\"/api1/list2");
        items.add(categoriesResult);
        categoriesResult = new CategoriesResult("3", "Characters", "\"/api1/list3");
        items.add(categoriesResult);

        return items;
    }
}