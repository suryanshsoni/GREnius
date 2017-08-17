package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ishitabhandari on 04/07/17.
 */

public class CardPresenterImpl <V extends CardView> extends BasePresenter<V> implements CardPresenter<V> {
    @Inject
    public CardPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public boolean markWord(Word obj) {
        Log.i("Mark: ", "In card presenter");
        getDataManager().setMarkedWords(obj);
        return true;}

    @Override
    public boolean unmarkWord(Word obj) {
        Log.i("Mark: ", "In card presenter");
        getDataManager().removeMarkedWords(obj);
        return true;
    }

    @Override
    public List<Word> getMarkedWord() {
        return getDataManager().getMarkedWords();
    }

    @Override
    public boolean getTutorial() { return getDataManager().getTutorial(); }

    @Override
    public void setTutorial(boolean tutorialshown) { getDataManager().setTutorial(tutorialshown);}

}
