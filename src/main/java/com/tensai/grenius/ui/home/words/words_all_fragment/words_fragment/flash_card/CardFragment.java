package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.util.ScreenUtils;
import com.tensai.grenius.view.SlideTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends BaseFragment implements CardView {
    Word wordObj;
    List<Word> markedWords;
    @BindView(R.id.iv_bookmark)
    ImageView ivBookmark;
    @BindView(R.id.tv_reveal_translation)
    SlideTextView tvRevealTranslation;
    @BindView(R.id.tv_flashcard_title_front)
    SlideTextView tvFlashcardTitleFront;
    @BindView(R.id.tv_flashcard_title_back)
    SlideTextView tvFlashcardTitleBack;
    @BindView(R.id.tv_flashcard_pos)
    SlideTextView tvFlashcardPos;
    @BindView(R.id.btn_audio)
    ImageView btnTts;
    @BindView(R.id.tv_flashcard_example)
    SlideTextView tvFlashcardExample;
    @BindView(R.id.tv_flashcard_meaning)
    SlideTextView tvFlashcardMeaning;
    @BindView(R.id.tv_flashcard_synonym)
    SlideTextView tvFlashcardSynonym;
    @BindView(R.id.ll_word_back)
    LinearLayout llWordBack;
    @BindView(R.id.ll_word_front)
    LinearLayout llWordFront;
    @BindView(R.id.tv_share_word)
    SlideTextView tvShareWord;
    @BindView(R.id.cv_back)
    android.support.v7.widget.CardView cvBack;
    @BindView(R.id.cv_front)
    android.support.v7.widget.CardView cvFront;
    @BindView(R.id.rl_flashcard_details_front)
    RelativeLayout rlFlashcardDetailsFront;
    @BindView(R.id.rl_flashcard_details_back)
    RelativeLayout rlFlashcardDetailsBack;
    Unbinder unbinder;

    @Inject
    CardPresenter<CardView> presenter;

    String connotation;


    public CardFragment() {
        // Required empty public constructor
    }

    static CardFragment show(Word object) {
        CardFragment subFragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable("wordObject", object);
        subFragment.setArguments(args);
        return subFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            wordObj = getArguments().getParcelable("wordObject");

        }
        getActivityComponent().inject(this);
        presenter.onAttach(this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        unbinder = ButterKnife.bind(this, view);
        markedWords = presenter.getMarkedWord();
        setView(wordObj);

        return view;
    }

    @Override
    public void setView(final Word object) {

        tvFlashcardTitleFront.setText(capitalize(object.getWord()));
        tvFlashcardTitleBack.setText(capitalize(object.getWord()));
        tvFlashcardExample.setText(object.getExample());
        tvFlashcardMeaning.setText(object.getMeaning());
        tvFlashcardSynonym.setText(object.getSynonym());

        switch (object.getPos()) {

            case "A":
                tvFlashcardPos.setText("Adjective");
                break;
            case "N":
                tvFlashcardPos.setText("Noun");
                break;
            case "V":
                tvFlashcardPos.setText("Verb");
                break;
            default:
                tvFlashcardPos.setText(object.getPos());
        }

        if (markedWords != null) {
            try {
                if (markedWords.contains(object)) {
                    ivBookmark.setImageResource(R.drawable.ic_bookmark_selected);
                } else {
                    ivBookmark.setImageResource(R.drawable.ic_bookmark_unselected);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
                ivBookmark.setImageResource(R.drawable.ic_bookmark_unselected);
            }

        connotation = object.getPzn();
        try {
            if (connotation.equals("p")) {
                llWordFront.setBackgroundColor(getResources().getColor(R.color.positive_bg));
                llWordBack.setBackgroundColor(getResources().getColor(R.color.positive_bg));
                tvRevealTranslation.setBackgroundColor(getResources().getColor(R.color.positive_bg));
                tvShareWord.setBackgroundColor(getResources().getColor(R.color.positive_bg));
                rlFlashcardDetailsFront.setBackgroundColor(getResources().getColor(R.color.positive_bg));
                rlFlashcardDetailsBack.setBackgroundColor(getResources().getColor(R.color.positive_bg));
            } else if (connotation.equals("n")) {
                llWordFront.setBackgroundColor(getResources().getColor(R.color.negative_bg));
                llWordBack.setBackgroundColor(getResources().getColor(R.color.negative_bg));
                tvRevealTranslation.setBackgroundColor(getResources().getColor(R.color.negative_bg));
                tvShareWord.setBackgroundColor(getResources().getColor(R.color.negative_bg));
                rlFlashcardDetailsFront.setBackgroundColor(getResources().getColor(R.color.negative_bg));
                rlFlashcardDetailsBack.setBackgroundColor(getResources().getColor(R.color.negative_bg));
            } else {
                llWordFront.setBackgroundColor(getResources().getColor(R.color.wl_yellow));
                llWordBack.setBackgroundColor(getResources().getColor(R.color.wl_yellow));
                tvRevealTranslation.setBackgroundColor(getResources().getColor(R.color.wl_yellow));
                tvShareWord.setBackgroundColor(getResources().getColor(R.color.wl_yellow));
                rlFlashcardDetailsFront.setBackgroundColor(getResources().getColor(R.color.wl_yellow));
                rlFlashcardDetailsBack.setBackgroundColor(getResources().getColor(R.color.wl_yellow));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnTts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = tvFlashcardTitleFront.getText().toString();
                presenter.speak(toSpeak);
            }
        });

        tvRevealTranslation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tvRevealTranslation.setText(wordObj.getTranslate());
            }
        });

        ivBookmark.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (markedWords!=null) {
                    if (markedWords.contains(wordObj)) {
                        presenter.unmarkWord(wordObj);
                        ivBookmark.setImageResource(R.drawable.ic_bookmark_unselected);
                    } else {
                        presenter.markWord(wordObj);
                        ivBookmark.setImageResource(R.drawable.ic_bookmark_selected);
                    }
                } else {
                    presenter.markWord(wordObj);
                    ivBookmark.setImageResource(R.drawable.ic_bookmark_selected);
                }


            }
        });

        tvShareWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(capitalize(object.getWord()) + ": " + capitalize(object.getMeaning()) + "\n\n" + "Example: " + object.getExample());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
