package com.tensai.grenius.ui.home.words_all_fragment.words_fragment.flash_card;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.ui.base.BasePresenter;

/**
 * Created by ishitabhandari on 04/07/17.
 */

public class CardPresenterImpl <V extends CardView> extends BasePresenter<V> implements CardPresenter<V> {

    public CardPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }
}
