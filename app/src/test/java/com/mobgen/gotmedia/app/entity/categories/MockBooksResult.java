package com.mobgen.gotmedia.app.entity.categories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 3/6/18.
 */
public class MockBooksResult {

    public static final String GEORGE_R_R_MARTIN = "George R. R. Martin";
    private static final Date DATE = new Date(System.currentTimeMillis());
    private static List<? extends Object> INSTANCE;
    public static final int ITEMS_SIZE = 3;

    public static List<? extends Object> getInstance(){
        if(INSTANCE == null){
            INSTANCE = getMockBooks();
        }
        return INSTANCE;
    }


    private static List<? extends Object> getMockBooks() {

        List<String> authors = new ArrayList<>();
        authors.add(GEORGE_R_R_MARTIN);
        List<Book> items = new ArrayList<>(ITEMS_SIZE);
        Book book = new Book(authors, "United States", "978-2345235", "Hardcover",
                384, "A storm of swords", "Bantam books", DATE);
        items.add(book);

        DATE.setTime(System.currentTimeMillis() + 1_000_000);
        book = new Book(authors, "United States", "978-2345235", "Hardcover",
                384, "A storm of swords 2", "Bantam books", DATE);
        items.add(book);

        DATE.setTime(System.currentTimeMillis() + 2_000_000);
        book = new Book(authors, "United States", "978-2345235", "Hardcover",
                384, "A storm of swords 22", "Bantam books", DATE);
        items.add(book);

        return items;
    }
}