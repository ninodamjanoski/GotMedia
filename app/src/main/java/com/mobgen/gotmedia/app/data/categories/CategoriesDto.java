package com.mobgen.gotmedia.app.data.categories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created on 3/2/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriesDto {

    private final String href;
    private final String id;
    private final String title;

    public CategoriesDto(@JsonProperty("href") String href, @JsonProperty("id") String id,
                         @JsonProperty("title") String title) {
        this.href = href;
        this.id = id;
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
