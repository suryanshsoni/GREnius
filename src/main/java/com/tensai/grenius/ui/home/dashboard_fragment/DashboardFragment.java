package com.tensai.grenius.ui.home.dashboard_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.model.Titleinstitute;
import com.tensai.grenius.model.WordOfDay;
import com.tensai.grenius.ui.base.BaseFragment;
import com.tensai.grenius.ui.home.dashboard_fragment.word_of_day.LastWODActivity;
import com.tensai.grenius.ui.home.dashboard_fragment.word_of_day.SponsorActivity;
import com.tensai.grenius.view.SlideTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.position.TopLeft;
import ru.dimorinny.showcasecard.position.ViewPosition;
import ru.dimorinny.showcasecard.radius.Radius;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends BaseFragment implements DashboardView, DashboardArticleAdapter.Callback {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @Inject
    DashboardPresenter<DashboardView> presenter;
    WordOfDay wordOfDay;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.wordofday_bookmark)
    ImageView wordofday_bookmark;
    @BindView(R.id.wordofday_speak)
    ImageView wordofday_speak;
    @BindView(R.id.wordofday_share)
    ImageView wordofday_share;
    @BindView(R.id.txtSynonym_cardback)
    SlideTextView txtSynonymCardback;
    @BindView(R.id.txtSentence_cardback)
    SlideTextView txtSentenceCardback;
    @BindView(R.id.txtCategory_cardfront)
    SlideTextView txtCategoryCardfront;
    @BindView(R.id.txtWord_cardfront)
    SlideTextView txtWordCardfront;
    @BindView(R.id.txtMeaning_cardback)
    SlideTextView txtMeaningCardback;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.dashboard_rl)
    RelativeLayout dashboardRl;
    @BindView(R.id.articlesView)
    RecyclerView rvarticles;
    Unbinder unbinder;

    boolean isWordMarked = false;
    @BindView(R.id.tv_lastWords)
    SlideTextView lastWords;
    @BindView(R.id.tv_sponsor_banner)
    SlideTextView tvSponsorBanner;

    String titleURL=null;
    List<Titleinstitute> titleinstitute;


    private OnFragmentInteractionListener mListener;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mListener != null) {
            mListener.onFragmentInteraction("Home");
        }
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvarticles.setLayoutManager(layoutManager);
        presenter.getWordOfDay();
        presenter.getTitleSponsor();
        rvarticles.setNestedScrollingEnabled(false);


        if (!presenter.getTutorial()) {
            mListener.showWelcomeText();
            new ShowCaseView.Builder(getActivity())
                    .withTypedPosition(new ViewPosition(cardView))
                    .withTypedRadius(new Radius(200))
                    .withContent("Tap to flip the card")
                    .withDismissListener(new ShowCaseView.DismissListener() {
                        @Override
                        public void onDismiss() {
                            new ShowCaseView.Builder(getActivity())
                                    .withTypedPosition(new TopLeft())
                                    .withTypedRadius(new Radius(200))
                                    .withContent("Update your content with the option of 'Update Words' in the side menu")
                                    .build()
                                    .show(getActivity());
                        }
                    })
                    .build()
                    .show(getActivity());

            presenter.setTutorial(true);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void showDashboardArticles(List<Articles> articlesList) {
        DashboardArticleAdapter dashboardArticleAdapter = new DashboardArticleAdapter(getActivity(), articlesList, this);
        try {
            rvarticles.setAdapter(dashboardArticleAdapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showWordOfDay(WordOfDay word) {
        try {
            this.wordOfDay = word;
            if (word.getWord().length() > 10) {
                txtWordCardfront.setTextSize(30);
            }
            /*Picasso.with(getContext())
                    .load(R.color.bg_screen1)
                    .resize(300,200)
                    .into((Target) cardView);*/
            txtWordCardfront.setText(capitalize(word.getWord()));
            txtMeaningCardback.setText(capitalize(word.getMeaning()));
            txtSentenceCardback.setText(word.getExample());
            txtCategoryCardfront.setText(word.getPos());
            txtSynonymCardback.setText(word.getSynonym());

            isWordMarked = isWordOfDayMarked();
            if (isWordMarked) {
                wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_selected);
            } else {
                wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_unselected);
            }

            presenter.getDashboardArticles();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showTitleSponsor(List<Titleinstitute> institute) {
        try{
            tvSponsorBanner.setText("Powered by: "+institute.get(0).getName());
            titleinstitute=institute;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void speak(String toSpeak) {
        presenter.speak(toSpeak);
    }

    public void callShare(String text) {
        share(text);
    }

    public boolean isWordOfDayMarked() {
        return presenter.isWordOfDayMarked(wordOfDay);
    }

    public void markWordOfDay(boolean isMarked) {
        //updates bookmark of word of the day

        if (isMarked) {
            presenter.removeMarkedWord(wordOfDay);
        } else {
            presenter.markWord(wordOfDay);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.wordofday_bookmark)
    public void onWordofdayBookmarkClicked() {
        wordofday_bookmark.setEnabled(false);
        markWordOfDay(isWordMarked);
        if (isWordMarked) {
            isWordMarked = false;
            wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_unselected);
        } else {
            isWordMarked = true;
            wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_selected);
        }
        wordofday_bookmark.setEnabled(true);
    }

    @OnClick(R.id.wordofday_share)
    public void onWordofdayShareClicked() {
        callShare("WORD OF THE DAY" + "\n\n" + capitalize(wordOfDay.getWord()) + ": " + capitalize(wordOfDay.getMeaning()) + "\n\n" + "Example: " + wordOfDay.getExample() + "\n\nTo learn more such words, head straight to our app on:- " + getResources().getString(R.string.app_url));
    }

    @OnClick(R.id.wordofday_speak)
    public void onWordofdaySpeakClicked() {
        try {
            speak(wordOfDay.getWord());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.tv_lastWords)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), LastWODActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_sponsor_banner)
    public void onSponsorBannerClicked() {
        try{
            Intent intent = new Intent(getContext(), SponsorActivity.class);
            intent.putExtra("link",titleinstitute.get(0).getUrl());
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
        void showWelcomeText();
    }
}