package com.tensai.grenius.ui.home.quiz_fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Question;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.util.ScreenUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.os.Looper.getMainLooper;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends BaseFragment implements QuizView,QuestionCard.Callback {


    @BindView(R.id.quiz_cards_container)
    SwipePlaceHolderView quizCardsContainer;
    Unbinder unbinder;
    int correct=0,incorrect=0;

    @Inject
    QuizPresenter<QuizView> presenter;

    private OnFragmentInteractionListener mListener;
    public QuizFragment() {
        // Required empty public constructor
    }
    int position;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args=getArguments();

        if(args!=null)
            position=args.getInt("position");
        else
            position=-1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        if (mListener != null) {
            mListener.onFragmentInteraction("Quiz");
        }
        unbinder = ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        setupCardContainerView();

        presenter.onViewInitialized(position);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupCardContainerView() {

        int screenWidth = ScreenUtils.getScreenWidth(getBaseAcitivity());
        int screenHeight = ScreenUtils.getScreenHeight(getBaseAcitivity());

        quizCardsContainer.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth((int) (0.90 * screenWidth))
                        .setViewHeight((int) (0.75 * screenHeight))
                        .setPaddingTop(20)
                        .setSwipeRotationAngle(10)
                        .setRelativeScale(0.01f));

        quizCardsContainer.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if (count == 0) {
                    // reload the contents again after 1 sec delay
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            presenter.onCardExhausted(correct,incorrect);
                        }
                    }, 800);
                }
            }
        });
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void refreshQuestionnaire(List<Question> questionList) {
        for (Question question : questionList) {
            if (question != null) {
                quizCardsContainer.addView(new QuestionCard(question,this));
            }
        }
    }

    @Override
    public void reloadQuestionnaire(List<Question> questionList) {
        refreshQuestionnaire(questionList);
        ScaleAnimation animation =
                new ScaleAnimation(
                        1.15f, 1, 1.15f, 1,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);

        quizCardsContainer.setAnimation(animation);
        animation.setDuration(100);
        animation.start();
    }

    @Override
    public void call(int ans) {
        if(ans==0){
            incorrect++;
        }
        else{
            correct++;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}
