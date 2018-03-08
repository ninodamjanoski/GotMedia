package com.mobgen.gotmedia.app.presentation.categories.listcell;

import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;

import com.mobgen.gotmedia.core.lists.ChildClickPresenter;

import java.util.List;

/**
 * Created on 3/8/18.
 */

public class CharacterCell extends ChildClickPresenter {

    public class CharacterCellViewHolder extends Presenter.ViewHolder implements CharacterViewHolder {

        public CharacterCellViewHolder(View view) {
            super(view);
        }

        @Override
        public void bindCharacterData(List<String> aliases, List<String> allegiances, List<String> titles,
                                      List<String> playedBy, String born, String culture, String died, String name,
                                      String gender, String mother) {

        }
    }
        @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
