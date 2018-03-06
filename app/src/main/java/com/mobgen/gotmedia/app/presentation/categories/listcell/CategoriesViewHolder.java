package com.mobgen.gotmedia.app.presentation.categories.listcell;

import android.view.View;

/**
 * Created on 3/5/18.
 */

public interface CategoriesViewHolder {
    void bindCellViewData(String id, String title, String url);

    void setOnClickListener(View.OnClickListener listener);
}
