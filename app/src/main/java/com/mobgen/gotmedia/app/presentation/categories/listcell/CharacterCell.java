package com.mobgen.gotmedia.app.presentation.categories.listcell;

import android.support.v17.leanback.widget.Presenter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.entity.categories.Character;
import com.mobgen.gotmedia.app.presentation.view.TitleDescRowView;
import com.mobgen.gotmedia.core.lists.ChildClickPresenter;

import java.util.List;

/**
 * Created on 3/8/18.
 */

public class CharacterCell extends ChildClickPresenter {

    public class CharacterCellViewHolder extends Presenter.ViewHolder implements CharacterViewHolder {

        private final TitleDescRowView aliases;
        private final TitleDescRowView allegiances;
        private final TitleDescRowView titles;
        private final TitleDescRowView playedBy;
        private final TitleDescRowView born;
        private final TitleDescRowView culture;
        private final TitleDescRowView died;
        private final TitleDescRowView name;
        private final TitleDescRowView gender;
        private final TitleDescRowView mother;
        private final TitleDescRowView father;
        private final TitleDescRowView spouse;

        public CharacterCellViewHolder(View view) {
            super(view);
            aliases = view.findViewById(R.id.aliases);
            allegiances = view.findViewById(R.id.allegiances);
            titles = view.findViewById(R.id.titles);
            playedBy = view.findViewById(R.id.playedBy);
            born = view.findViewById(R.id.born);
            culture = view.findViewById(R.id.culture);
            died = view.findViewById(R.id.died);
            name = view.findViewById(R.id.name);
            gender = view.findViewById(R.id.gender);
            mother = view.findViewById(R.id.mother);
            father = view.findViewById(R.id.father);
            spouse = view.findViewById(R.id.spouse);
        }

        @Override
        public void bindCharacterData(List<String> aliases, List<String> allegiances, List<String> titles,
                                      List<String> playedBy, String born, String culture, String died, String name,
                                      String gender, String mother, String father, String spouse) {
            handleListOfStringsToView(this.aliases, aliases);
            handleListOfStringsToView(this.allegiances, allegiances);
            handleListOfStringsToView(this.titles, titles);
            handleListOfStringsToView(this.playedBy, playedBy);
            handleSimpleView(this.born, born);
            handleSimpleView(this.culture, culture);
            handleSimpleView(this.died, died);
            handleSimpleView(this.name, name);
            handleSimpleView(this.gender, gender);
            handleSimpleView(this.mother, mother);
            handleSimpleView(this.father, father);
            handleSimpleView(this.spouse, spouse);
        }

        private void handleSimpleView(TitleDescRowView descRowView, String s) {
            if(TextUtils.isEmpty(s)){
                if(descRowView.getVisibility() != View.GONE){
                    descRowView.setVisibility(View.GONE);
                }
            }else {
                if(descRowView.getVisibility() != View.VISIBLE){
                    descRowView.setVisibility(View.VISIBLE);
                }
                descRowView.setDesc(s);
            }
        }

        private void handleListOfStringsToView(TitleDescRowView descRowView, List<String> list) {
            if(list == null || list.isEmpty()){
                if(descRowView.getVisibility() != View.GONE){
                    descRowView.setVisibility(View.GONE);
                }
            }else {
                if(descRowView.getVisibility() != View.VISIBLE){
                    descRowView.setVisibility(View.VISIBLE);
                }
                String desc = "";
                for(String str : list){
                    if(!TextUtils.isEmpty(desc)){
                        desc += ", \n";
                    }
                    desc += str;
                }
                descRowView.setDesc(desc);
            }
        }
    }
        @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new CharacterCellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_character,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Character character = (Character) item;
        CharacterViewHolder holder = (CharacterViewHolder) viewHolder;
        holder.bindCharacterData(character.getAliases(), character.getAllegiances(), character.getTitles(), character.getPlayedBy(),
                character.getBorn(), character.getCulture(), character.getDied(), character.getName(), character.getGender(),
                character.getMother(), character.getFather(), character.getSpouse());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
