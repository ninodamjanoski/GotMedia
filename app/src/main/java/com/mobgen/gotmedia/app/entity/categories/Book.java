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
public class Book implements Parcelable {
    private final List<String> authors;
    private final String country;
    private final String isbn;
    private final String mediaType;
    private final int numPages;
    private final String name;
    private final String publisher;
    private final Date released;

    public Book(@JsonProperty("authors")List<String> authors, @JsonProperty("country")String country, @JsonProperty("isbn")String isbn,
                @JsonProperty("mediaType")String mediaType, @JsonProperty("numberOfPages")int numPages,
                @JsonProperty("name")String name, @JsonProperty("publisher")String publisher, @JsonProperty("released")Date released) {
        this.authors = authors;
        this.country = country;
        this.isbn = isbn;
        this.mediaType = mediaType;
        this.numPages = numPages;
        this.name = name;
        this.publisher = publisher;
        this.released = released;
    }


    protected Book(Parcel in) {
        authors = in.createStringArrayList();
        country = in.readString();
        isbn = in.readString();
        mediaType = in.readString();
        numPages = in.readInt();
        name = in.readString();
        publisher = in.readString();
        released = new Date(in.readLong());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public List<String> getAuthors() {
        return authors;
    }

    public String getCountry() {
        return country;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getMediaType() {
        return mediaType;
    }

    public int getNumPages() {
        return numPages;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getReleased() {
        return released;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(authors);
        parcel.writeString(country);
        parcel.writeString(isbn);
        parcel.writeString(mediaType);
        parcel.writeInt(numPages);
        parcel.writeString(name);
        parcel.writeString(publisher);
        parcel.writeLong(released.getTime());
    }
}
