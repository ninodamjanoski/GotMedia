package com.mobgen.gotmedia.app.entity.categories;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created on 3/2/18.
 */


@Entity(indexes = {
        @Index(value = "id", unique = true)
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category implements Parcelable {

    private String id;
    private String title;
    private String href;


    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    protected Category(Parcel in) {
        href = in.readString();
        id = in.readString();
        title = in.readString();
    }

    @Keep
    public Category(@JsonProperty("id") String id, @JsonProperty("title") String title,
                    @JsonProperty("href") String href) {
        this.id = id;
        this.title = title;
        this.href = href;
    }

    @Generated(hash = 1150634039)
    public Category() {
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
        dest.writeString(id);
        dest.writeString(title);
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
