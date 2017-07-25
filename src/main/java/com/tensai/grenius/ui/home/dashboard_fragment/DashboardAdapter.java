package com.tensai.grenius.ui.home.dashboard_fragment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tensai.grenius.R;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.view.SlideTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tensai.grenius.R.id.wordofday_bookmark;

/**
 * Created by rishabhpanwar on 24/07/17.
 */

public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_HEADER = 1;
    private Callback callback;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_NORMAL) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_wordofday_layout, parent, false);
            return new WordOfDayViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_article_layout, parent, false);
            return new ArticleViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final int itemType = getItemViewType(position);

        if (itemType == 1) {
            //((WordOfDayViewHolder) holder).setHeaderText(Word word, );
        } else {
            //((ArticleViewHolder) holder).setHeaderText((String) myData[position]);
            //(ArticleViewHolder) holder.
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 1) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @OnClick(R.id.card_view)
    public void onViewClicked() {
    }

    public class WordOfDayViewHolder extends RecyclerView.ViewHolder {

        @BindView(wordofday_bookmark)
        ImageView wordofdayBookmark;
        @BindView(R.id.wordofday_share)
        ImageView wordofdayShare;
        @BindView(R.id.txtLabelSynonym_cardback)
        SlideTextView txtLabelSynonymCardback;
        @BindView(R.id.txtSynonym_cardback)
        SlideTextView txtSynonymCardback;
        @BindView(R.id.txtLabelSentence_cardback)
        SlideTextView txtLabelSentenceCardback;
        @BindView(R.id.txtSentence_cardback)
        SlideTextView txtSentenceCardback;
        @BindView(R.id.tv_wordlist_title)
        SlideTextView tvWordlistTitle;
        @BindView(R.id.wotd_layout)
        RelativeLayout wotdLayout;
        @BindView(R.id.txtWord_cardfront)
        SlideTextView txtWordCardfront;
        @BindView(R.id.wordofday_speak)
        ImageView wordofdaySpeak;
        @BindView(R.id.txtCategory_cardfront)
        SlideTextView txtCategoryCardfront;
        @BindView(R.id.txtMeaning_cardback)
        SlideTextView txtMeaningCardback;

        public WordOfDayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setHeaderText(Word word) {
            txtWordCardfront.setText(callback.capitalize(word.getWord()));
            txtMeaningCardback.setText(word.getMeaning());
            txtSentenceCardback.setText(word.getExample());
            txtSynonymCardback.setText(word.getSynonym());

            switch (word.getPos()) {

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
            }
            /*isWordMarked = callback.isWordOfDayMarked();
            if(isWordMarked){
                wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_selected);
            }else {
                wordofday_bookmark.setImageResource(R.drawable.ic_bookmark_unselected);
            }*/

        }
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_dashboard_article_bckgrnd)
        ImageView ivDashboardArticleBckgrnd;
        @BindView(R.id.tv_article_titles)
        SlideTextView tvArticleTitles;
        @BindView(R.id.card_view)
        CardView cardView;


        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        //public void bindData(MyModel model) {
            //tvArticleTitles.setText();
            //descriptionLabel.setText(model.getDescription());
        //}
    }

    public interface Callback {
        void speak(String toSpeak);
        void callShare(String text);
        String capitalize(String text);
        boolean isWordOfDayMarked();
        void markWordOfDay(boolean isMarked);
    }
}
