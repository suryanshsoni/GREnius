package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.view.SlideTextView;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.position.BottomRight;
import ru.dimorinny.showcasecard.position.ViewPosition;
import ru.dimorinny.showcasecard.radius.Radius;
/*import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.position.ViewPosition;
import ru.dimorinny.showcasecard.radius.Radius;*/


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
    Callback callback = null;
    @BindView(R.id.flipView)
    EasyFlipView flipView;

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
        Log.i("Tut: ", " " + presenter.getTutorial());
        return view;
    }

    @Override
    public void setView(Word object) {

        tvFlashcardTitleFront.setText(capitalize(object.getWord()));
        tvFlashcardTitleBack.setText(capitalize(object.getWord()));
        tvFlashcardExample.setText(object.getExample());
        tvFlashcardMeaning.setText(object.getMeaning());
        tvFlashcardSynonym.setText(object.getSynonym());

        switch (object.getPos()) {

            case "A":
                tvFlashcardPos.setText(R.string.adjective);
                break;
            case "N":
                tvFlashcardPos.setText(R.string.noun);
                break;
            case "V":
                tvFlashcardPos.setText(R.string.verb);
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
        } else {
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
                if (markedWords != null) {

                    if (markedWords.contains(wordObj)) {
                        presenter.unmarkWord(wordObj);
                        markedWords.remove(wordObj);
                        ivBookmark.setImageResource(R.drawable.ic_bookmark_unselected);
                    } else {
                        presenter.markWord(wordObj);
                        markedWords.add(wordObj);
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
                share(capitalize(wordObj.getWord()) + ": " + capitalize(wordObj.getMeaning()) + "\n\n" + "Example: " + wordObj.getExample());
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Tut:", "In card fragment attach");
        try {
            callback = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void dialogDismissed() {
        Log.i("Tut:", "in card fragment dialog dismissed method");
      /*  new FancyShowCaseView.Builder(getActivity())
                .focusOn(rlFlashcardDetailsFront)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .title("Tap to flip the card")
                .titleStyle(0, Gravity.BOTTOM | Gravity.CENTER)
                .showOnce("fancy1")
                .build()
                .
                .show(); */
        new ShowCaseView.Builder(getActivity())
                .withTypedPosition( new BottomRight())
                .withTypedRadius(new Radius(300F))
                .withContent(" Swipe to view the cards ")
                .withDismissListener(new ShowCaseView.DismissListener() {
                    @Override
                    public void onDismiss() {
                        new ShowCaseView.Builder(getActivity())
                                .withContent("Tap anywhere on the card to flip it")
                                .withTypedPosition(new ViewPosition(rlFlashcardDetailsFront))
                                .withTypedRadius(new Radius(300F))
                                .withDismissListener(new ShowCaseView.DismissListener() {
                                    @Override
                                    public void onDismiss() {
                                        flipView.flipTheView();
                                    }
                                }).build()
                                .show(getActivity());
                    }
                })
                .build()
                .show(getActivity());


       /* */
        presenter.setTutorial(true);
    }

}
