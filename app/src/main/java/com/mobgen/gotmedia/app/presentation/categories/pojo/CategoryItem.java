package com.mobgen.gotmedia.app.presentation.categories.pojo;

/**
 * Created on 3/5/18.
 */

public class CategoryItem {

    private final String id;
    private final String title;
    private final String url;

    public CategoryItem(String id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
