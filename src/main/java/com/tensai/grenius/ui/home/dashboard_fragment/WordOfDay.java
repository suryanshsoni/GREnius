package com.tensai.grenius.ui.home.dashboard_fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.tensai.grenius.R;
import com.tensai.grenius.view.SlideTextView;

import butterknife.BindView;

/**
 * Created by Pavilion on 13-07-2017.
 */

@NonReusable
@Layout(R.layout.dashboard_wordofday_layout)

public class WordOfDay {
    @View(R.id.wordofday_bookmark)
    ImageView wordofday_bookmark;
    @View(R.id.wordofday_speak)
    ImageView wordofday_speak;
    @View(R.id.wordofday_share)
    ImageView wordofday_share;
    @View(R.id.txtSynonym_cardback)
    SlideTextView txtSynonymCardback;
    @View(R.id.txtSentence_cardback)
    SlideTextView txtSentenceCardback;
    @View(R.id.txtCategory_cardfront)
    SlideTextView txtCategoryCardfront;
    @View(R.id.txtWord_cardfront)
    SlideTextView txtWordCardfront;
    @View(R.id.txtMeaning_cardback)
    SlideTextView txtMeaningCardback;

    boolean isWordMarked=false;

    private com.tensai.grenius.model.WordOfDay word;
    private Callback callback;

    public WordOfDay(Callback callback, com.tensai.grenius.model.WordOfDay word) {
        this.callback = callback;
        this.word = word;
    }

    @Resolve
    private void onResolved() {
        txtWordCardfront.setText(callback.capitalize(word.getWord()));
        txtMeaningCardback.setText(word.getMeaning());
        txtSentenceCardback.setText(word.getExample());
        txtSynonymCardback.setText(word.getSynonym());

        /*switch (word.getPos()) {

            case "A":
                txtCategoryCardfront.setText(R.string.adjective);
                break;
            case "N":
                txtCategoryCardfront.setText(R.string.noun);
                break;
            case "V":
                txtCategoryCardfront.setText(R.string.verb);
                break;
            default:
                txtCategoryCardfront.setText(word.getPos());
        }*/
        isWordMarked = callback.isWordOfDayMarked();
        if(isWordMarked){
            wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_selected);
        }else {
            wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_unselected);
        }
    }

    @Click(R.id.wordofday_speak)
    public void onOption1Click() {
        callback.speak(word.getWord());
    }
    @Click(R.id.wordofday_share)
    public void onShareClick() {
        callback.callShare("*"+callback.capitalize(word.getWord())+":* "+callback.capitalize(word.getMeaning())+"\n\n"+"*Example:* "+word.getExample());
    }
    @Click(R.id.wordofday_bookmark)
    public void onBookmarkClick(){
        callback.markWordOfDay(isWordMarked);
        if (isWordMarked){
            isWordMarked = false;
            wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_unselected);
        } else {
            isWordMarked = true;
            wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_selected);
        }
    }

    public interface Callback {
        void speak(String toSpeak);
        void callShare(String text);
        String capitalize(String text);
        boolean isWordOfDayMarked();
        void markWordOfDay(boolean isMarked);
    }
}