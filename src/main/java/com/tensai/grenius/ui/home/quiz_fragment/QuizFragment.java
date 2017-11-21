package com.tensai.grenius.ui.home.quiz_fragment;


import android.app.Activity;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.tensai.grenius.R;
import com.tensai.grenius.model.Question;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.toptas.fancyshowcase.FancyShowCaseView;
import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.position.ViewPosition;
import ru.dimorinny.showcasecard.radius.Radius;

import static android.os.Looper.getMainLooper;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends BaseFragment implements QuizView, QuestionCard.Callback {


    @BindView(R.id.quiz_cards_container)
    SwipePlaceHolderView quizCardsContainer;
    Unbinder unbinder;
    int correct = 0, incorrect = 0, unattempted = 0;

    @Inject
    QuizPresenter<QuizView> presenter;

    PieChart piechart;
    int count;

    private OnFragmentInteractionListener mListener;

    public QuizFragment() {
        // Required empty public constructor
    }

    int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (args != null)
            position = args.getInt("position");
        else
            position = -1;
        Log.d("Demo",position+":position");
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
        if (!presenter.getQuizTutorial()){
            new ShowCaseView.Builder(getActivity())
                    .withTypedPosition(new ViewPosition(quizCardsContainer))
                    .withTypedRadius(new Radius(300))
                    .withContent("Swipe the card to view next question")
                    .withDismissListener(new ShowCaseView.DismissListener() {
                        @Override
                        public void onDismiss() {
                            presenter.setQuizTutorial(true);
                        }
                    })
                    .build()
                    .show(getActivity());
        }

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
                            presenter.onCardExhausted(correct, incorrect, unattempted);
                        }
                    }, 800);
                }
            }
        });
    }

    public void onAttach(Activity activity) {
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
        count=1;
        for (Question question : questionList) {
            if (question != null) {
                try {
                    quizCardsContainer.addView(new QuestionCard(question, this, count));
                    Log.i("Cnt:",""+count);
                    count++;
                }catch (Exception e){
                    e.printStackTrace();
                }
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
        if (ans == 0) {
            incorrect++;
        } else {
            correct++;
        }
    }

    @Override
    public void callUnattempted() {
        unattempted++;
    }

    @Override
    public void showResult(int correct, int incorrect, int unattempted) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        RelativeLayout rl = (RelativeLayout) getActivity().findViewById(R.id.rl_score);
        View v = inflater.inflate(R.layout.quiz_result, null, false);

        piechart= (PieChart) v.findViewById(R.id.piechart);

        List<Integer> arr_colour= new ArrayList<Integer>();
        rl.addView(v);

        Legend legend = piechart.getLegend();

        legend.setEnabled(false);
        piechart.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();

        ArrayList<String> xVals = new ArrayList<String>();
        //{R.color.pie_correct,R.color.pie_incorrect,R.color.pie_unattempted};
        if(correct!=0){
            xVals.add("Correct");
            yvalues.add(new Entry(correct, 0));
            arr_colour.add(R.color.pie_correct);
        }

        if(incorrect!=0){
            yvalues.add(new Entry(incorrect, 1));
            xVals.add("Incorrect");
            arr_colour.add(R.color.pie_incorrect);
        }

        if(unattempted!=0){
            yvalues.add(new Entry(unattempted, 2));
            xVals.add("Unattempted");
            arr_colour.add(R.color.pie_unattempted);
        }

        PieDataSet dataSet = new PieDataSet(yvalues, "");

        PieData data = new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        piechart.setDrawHoleEnabled(true);
        piechart.setDescription("");
        int arr[]=new int[arr_colour.size()];
        int i=0;
        for(Integer n : arr_colour){
            arr[i++]=n;
        }
        dataSet.setColors(arr, getActivity());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);

        piechart.setData(data);
        piechart.animateY(2000);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}
