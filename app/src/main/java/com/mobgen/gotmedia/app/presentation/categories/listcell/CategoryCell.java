package com.mobgen.gotmedia.app.presentation.categories.listcell;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.presentation.categories.pojo.CategoryItem;
import com.mobgen.gotmedia.core.lists.ChildClickPresenter;

/**
 * Created on 3/5/18.
 */

public class CategoryCell extends ChildClickPresenter {

    public class CategoryCellViewHolder extends Presenter.ViewHolder implements CategoriesViewHolder {

        private final View view;
        private final TextView textCategory;

        public CategoryCellViewHolder(View view) {
            super(view);
            this.view = view.findViewById(R.id.cell);
            textCategory = view.findViewById(R.id.description);
        }

        @Override
        public void bindCellViewData(String id, String title, String url) {
            textCategory.setText(title);
        }

        @Override
        public void setOnClickListener(View.OnClickListener listener) {
            view.setOnClickListener(listener);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CategoryCellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_category, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        final CategoryItem categoryItem = (CategoryItem) item;
        CategoriesViewHolder categoriesViewHolder = (CategoriesViewHolder) viewHolder;
        categoriesViewHolder.bindCellViewData(categoryItem.getId(), categoryItem.getTitle(), categoryItem.getUrl());
        categoriesViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getOnCellChildClickListener() != null){
                    getOnCellChildClickListener().onViewClicked(v, categoryItem);
                }
            }
        });
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
