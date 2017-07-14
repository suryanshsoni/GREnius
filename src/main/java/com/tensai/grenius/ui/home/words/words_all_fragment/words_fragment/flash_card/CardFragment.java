package com.tensai.grenius.ui.home.words.words_all_fragment.words_fragment.flash_card;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends BaseFragment implements CardView {
    Word wordObj;
    @BindView(R.id.iv_bookmark)
    ImageView ivBookmark;
    @BindView(R.id.tv_flip)
    TextView tvFlip;
    @BindView(R.id.tv_reveal_translation)
    TextView tvRevealTranslation;
    @BindView(R.id.tv_flashcard_title)
    TextView tvFlashcardTitle;
    @BindView(R.id.tv_flashcard_pos)
    TextView tvFlashcardPos;
    @BindView(R.id.rl_flashcard_details)
    RelativeLayout rlFlashcardDetails;
    Unbinder unbinder;
    @BindView(R.id.btn_audio)
    ImageView btnTts;

    @Inject
    CardPresenter<CardView> presenter;

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
            Log.i("Demo", "" + wordObj.getExample());


        }
        getActivityComponent().inject(this);
        presenter.onAttach(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        unbinder = ButterKnife.bind(this, view);

        setView(wordObj);
        return view;
    }

    @Override
    public void setView(Word object) {
        tvFlashcardTitle.setText(object.getWord());
        tvFlashcardPos.setText(object.getPos());

        tvFlip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //function for flip
            }
        });
        btnTts.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String toSpeak = tvFlashcardTitle.getText().toString();
                                          //Toast.makeText(getActivity().getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                                          //getTts().speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                                            presenter.speak(toSpeak);
                                      }
                                  });

                tvRevealTranslation.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        tvRevealTranslation.setText("~Hindi Translation~");
                    }
                });

        rlFlashcardDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //flip the card
            }
        });

        ivBookmark.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ivBookmark.setImageResource(R.drawable.ic_bookmark_selected);
                //add to do marked words list
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
