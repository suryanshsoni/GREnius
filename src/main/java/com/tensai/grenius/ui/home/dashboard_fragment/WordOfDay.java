package com.tensai.grenius.ui.home.dashboard_fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.tensai.grenius.R;

import butterknife.BindView;

/**
 * Created by Pavilion on 13-07-2017.
 */

@NonReusable
@Layout(R.layout.dashboard_wordofday_layout)

public class WordOfDay {
    @View(R.id.wordofday_speak)
    ImageView wordofday_speak;
    @View(R.id.wordofday_share)
    ImageView wordofday_share;
    @View(R.id.txtSynonym_cardback)
    TextView txtSynonymCardback;
    @View(R.id.txtSentence_cardback)
    TextView txtSentenceCardback;
    @View(R.id.txtCategory_cardfront)
    TextView txtCategoryCardfront;
    @View(R.id.txtWord_cardfront)
    TextView txtWordCardfront;
    @View(R.id.txtMeaning_cardback)
    TextView txtMeaningCardback;

    private com.tensai.grenius.model.WordOfDay word;
    private Callback callback;

    public WordOfDay(Callback callback, com.tensai.grenius.model.WordOfDay word) {
        this.callback = callback;
        this.word = word;
    }

    @Resolve
    private void onResolved() {
        txtWordCardfront.setText(word.getWord());
        txtMeaningCardback.setText(word.getMeaning());
        txtSentenceCardback.setText(word.getExample());
        txtSynonymCardback.setText(word.getSynonym());
    }

    @Click(R.id.wordofday_speak)
    public void onOption1Click() {
        callback.speak(word.getWord());
    }
    @Click(R.id.wordofday_share)
    public void onShareClick() {
        callback.callShare(word.getWord()+":"+word.getMeaning()+"\n"+"Sentence: "+word.getExample());
    }
    public interface Callback {
        void speak(String toSpeak);
        void callShare(String text);
    }
}