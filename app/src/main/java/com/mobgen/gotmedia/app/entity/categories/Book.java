package com.mobgen.gotmedia.app.entity.categories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Created on 3/7/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
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
}
