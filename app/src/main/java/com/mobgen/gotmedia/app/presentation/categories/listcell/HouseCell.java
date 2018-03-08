package com.mobgen.gotmedia.app.presentation.categories.listcell;


import android.support.v17.leanback.widget.Presenter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.entity.categories.House;
import com.mobgen.gotmedia.app.presentation.view.TitleDescRowView;
import com.mobgen.gotmedia.core.lists.ChildClickPresenter;

/**
 * Created on 3/8/18.
 */

public class HouseCell extends ChildClickPresenter {

    public class HouseCellViewHolder extends Presenter.ViewHolder implements HouseViewHolder {

        private final TextView textName;
        private final TitleDescRowView textTitle;
        private final TitleDescRowView textRegion;
        private final View titleDivider;
        View view;

        public HouseCellViewHolder(View view) {
            super(view);
            this.view = view;
            textName = view.findViewById(R.id.name);
            textTitle = view.findViewById(R.id.title);
            textRegion = view.findViewById(R.id.region);
            titleDivider = view.findViewById(R.id.title_divider);
        }

        @Override
        public void bindHouseData(String name, String region, String title) {
            textName.setText(name);
            if(TextUtils.isEmpty(title)){
                if(textTitle.getVisibility() != View.GONE){
                    textTitle.setVisibility(View.GONE);
                }
                if(titleDivider.getVisibility() != View.GONE){
                    titleDivider.setVisibility(View.GONE);
                }
            }else {
                textTitle.setDesc(title);
                if(textTitle.getVisibility() != View.VISIBLE){
                    textTitle.setVisibility(View.VISIBLE);
                }
                if(titleDivider.getVisibility() != View.VISIBLE){
                    titleDivider.setVisibility(View.VISIBLE);
                }
            }
            textRegion.setDesc(region);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new HouseCellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_house, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        House house = (House) item;
        HouseViewHolder holder = (HouseViewHolder) viewHolder;
        holder.bindHouseData(house.getName(), house.getRegion(), house.getTitle());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
