package com.mobgen.gotmedia.app.presentation.categories.listcell;

import java.util.List;

/**
 * Created on 3/8/18.
 */

interface CharacterViewHolder {

    void bindCharacterData(List<String> aliases, List<String> allegiances, List<String> titles, List<String> playedBy,
                           String born, String culture, String died, String name, String gender, String mother);
}
