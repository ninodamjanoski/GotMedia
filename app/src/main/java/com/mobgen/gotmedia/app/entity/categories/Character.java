package com.mobgen.gotmedia.app.entity.categories;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created on 3/7/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character implements Parcelable {
    private final List<String> aliases;
    private final List<String> allegiances;
    private final List<String> titles;
    private final List<String> playedBy;
    private final String born;
    private final String culture;
    private final String died;
    private final int id;
    private final String name;
    private final String gender;
    private final String mother;

    public Character(@JsonProperty("aliases")List<String> aliases, @JsonProperty("allegiances")List<String> allegiances,
                     @JsonProperty("titles")List<String> titles, @JsonProperty("playedBy")List<String> playedBy,
                     @JsonProperty("born")String born, @JsonProperty("culture")String culture,
                     @JsonProperty("died")String died, @JsonProperty("id")int id,
                     @JsonProperty("name")String name, @JsonProperty("gender")String gender, @JsonProperty("mother")String mother) {
        this.aliases = aliases;
        this.allegiances = allegiances;
        this.titles = titles;
        this.playedBy = playedBy;
        this.born = born;
        this.culture = culture;
        this.died = died;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.mother = mother;
    }

    protected Character(Parcel in) {
        aliases = in.createStringArrayList();
        allegiances = in.createStringArrayList();
        titles = in.createStringArrayList();
        playedBy = in.createStringArrayList();
        born = in.readString();
        culture = in.readString();
        died = in.readString();
        id = in.readInt();
        name = in.readString();
        gender = in.readString();
        mother = in.readString();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public List<String> getAliases() {
        return aliases;
    }

    public List<String> getAllegiances() {
        return allegiances;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getPlayedBy() {
        return playedBy;
    }

    public String getBorn() {
        return born;
    }

    public String getCulture() {
        return culture;
    }

    public String getDied() {
        return died;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMother() {
        return mother;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(aliases);
        parcel.writeStringList(allegiances);
        parcel.writeStringList(titles);
        parcel.writeStringList(playedBy);
        parcel.writeString(born);
        parcel.writeString(culture);
        parcel.writeString(died);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeString(mother);
    }
}
