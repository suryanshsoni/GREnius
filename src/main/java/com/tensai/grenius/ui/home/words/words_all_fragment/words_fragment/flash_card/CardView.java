package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;

import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.MvpView;

/**
 * Created by ishitabhandari on 04/07/17.
 */

public interface CardView extends MvpView {
    void setView(Word object);
}
