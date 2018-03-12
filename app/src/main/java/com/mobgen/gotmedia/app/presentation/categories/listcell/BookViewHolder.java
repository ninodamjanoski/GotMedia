package com.mobgen.gotmedia.app.presentation.categories.listcell;

import java.util.List;

/**
 * Created on 3/8/18.
 */

interface BookViewHolder {

    void bindBookData(List<String> authors, String country, String isbn, String mediaType, int numPages,
                      String name, String publisher, String released);
}
