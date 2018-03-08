package com.mobgen.gotmedia.app.entity.categories;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Created on 3/7/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class House implements Parcelable {
    private final String id;
    private final String name;
    private final String region;
    private final String title;;

    public House(@JsonProperty("id") String id, @JsonProperty("name") String name,
                 @JsonProperty("region") String region, @JsonProperty("title") String title){
        this.id = id;
        this.name = name;
        this.region = region;
        this.title = title;
    }

    protected House(Parcel in) {
        id = in.readString();
        name = in.readString();
        region = in.readString();
        title = in.readString();
    }

    public static final Creator<House> CREATOR = new Creator<House>() {
        @Override
        public House createFromParcel(Parcel in) {
            return new House(in);
        }

        @Override
        public House[] newArray(int size) {
            return new House[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(region);
        parcel.writeString(title);
    }
}
