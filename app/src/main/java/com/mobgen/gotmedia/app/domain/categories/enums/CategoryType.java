package com.mobgen.gotmedia.app.domain.categories.enums;

import java.util.Locale;

/**
 * Created on 3/7/18.
 */

public enum CategoryType {

    UNKNOWN("unknown"),
    BOOKS("books"),
    HOUSES("houses"),
    CHARACTERS("characters");

    private final String name;

    CategoryType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static CategoryType fromString(String from) {
        if (from != null) {
            String fromLowerCase = from.toLowerCase(Locale.ENGLISH);
            for (CategoryType categoryType : values()) {
                if (categoryType.name.equals(fromLowerCase)) {
                    return categoryType;
                }
            }
        }
        return UNKNOWN;
    }

}
