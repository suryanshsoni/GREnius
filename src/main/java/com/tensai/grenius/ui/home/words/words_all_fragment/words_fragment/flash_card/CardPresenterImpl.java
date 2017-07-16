package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import android.speech.tts.TextToSpeech;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ishitabhandari on 04/07/17.
 */

public class CardPresenterImpl <V extends CardView> extends BasePresenter<V> implements CardPresenter<V> {
    @Inject
    public CardPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

}
