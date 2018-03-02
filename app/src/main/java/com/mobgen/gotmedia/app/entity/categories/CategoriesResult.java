package com.mobgen.gotmedia.app.entity.categories;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created on 3/2/18.
 */


@Entity(indexes = {
        @Index(value = "id", unique = true)
})
public class CategoriesResult implements Parcelable {

    private String href;
    private String id;
    private String title;


    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    protected CategoriesResult(Parcel in) {
        href = in.readString();
        id = in.readString();
        title = in.readString();
    }

    @Generated(hash = 995343199)
    public CategoriesResult(String href, String id, String title) {
        this.href = href;
        this.id = id;
        this.title = title;
    }

    @Generated(hash = 13005112)
    public CategoriesResult() {
    }

    public static final Creator<CategoriesResult> CREATOR = new Creator<CategoriesResult>() {
        @Override
        public CategoriesResult createFromParcel(Parcel in) {
            return new CategoriesResult(in);
        }

        @Override
        public CategoriesResult[] newArray(int size) {
            return new CategoriesResult[size];
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
