package com.mobgen.gotmedia.app.presentation.categories.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 3/5/18.
 */

public class CategoryItem implements Parcelable {

    private final String id;
    private final String title;
    private final String url;

    public CategoryItem(String id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    protected CategoryItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<CategoryItem> CREATOR = new Creator<CategoryItem>() {
        @Override
        public CategoryItem createFromParcel(Parcel in) {
            return new CategoryItem(in);
        }

        @Override
        public CategoryItem[] newArray(int size) {
            return new CategoryItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(url);
    }
}
