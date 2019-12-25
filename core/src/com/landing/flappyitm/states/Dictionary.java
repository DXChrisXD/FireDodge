package com.landing.flappyitm.states;

import com.badlogic.gdx.utils.ObjectMap;

public class Dictionary {

    public enum Keys{
        KeyScore,
        KeyHighscore,
        KeyTryagain,
        KeyMainmenu,
        KeyInstructions,
        KeyHowto1_1,
        KeyHowto1_2,
        KeyHowto1_3,
        KeyHowto2_1,
        KeyHowto2_2,
        KeyHowto3_1,
        KeyHowto3_2,
        KeyHowto4_1,
        KeyHowto4_2,
        KeyStory,
        KeyStory1_1,
        KeyStory1_2,
        KeyStory2_1,
        KeyStory2_2,
        KeyStory3_1,
        KeyStory3_2,
        KeyProfile,
        KeyScores,
        KeyHowto,
        KeySound,
        KeyLanguage,
        KeyCredits,
        KeyLanguage1_1,
        KeyLanguage1_2,
        KeyCredits1_1,
        KeyCredits1_2,
        KeyCredits1_3,
        KeyCredits1_4,
        KeyCredits1_5,
        KeySound1_1,
        KeySound1_2,
        KeySound1_3,
        KeyProfile1_1,
        KeyProfile1_2,
        KeyInputname,
        KeySave,
        KeyPlayername,
        KeyPlayer,
        KeyPlayerhint
    }
    public ObjectMap<String, String> map;

    public Dictionary(){

    }

    public String getValue(Keys key){
        return map.get(key.name());
    }

}
