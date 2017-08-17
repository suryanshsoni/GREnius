package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpPresenter;

import java.util.List;

/**
 * Created by ishitabhandari on 04/07/17.
 */

public interface CardPresenter <V extends CardView> extends MvpPresenter<V> {

    void speak(String toSpeak);
    boolean markWord(Word obj);
    boolean unmarkWord(Word obj);
    List<Word> getMarkedWord();
    boolean getTutorial();
    void setTutorial(boolean tutorialshown);
}
