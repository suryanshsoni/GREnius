package com.tensai.grenius.ui.home.quiz_fragment;

import android.support.annotation.Nullable;
import android.util.Log;

import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Question;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Pavilion on 30-06-2017.
 */

public class QuizPresenterImpl <V extends QuizView> extends BasePresenter<V> implements QuizPresenter<V>,QuestionsList.Callback {
    @Inject
    public QuizPresenterImpl(DataManager dataManager) {
        super(dataManager);
    }
    int pos;
    @Override
    public void onViewInitialized(int position) {
        List<Question> questions= new ArrayList<Question>();
        this.pos=position;
        getDataManager().getAllWords(new QueryTransaction.QueryResultListCallback<Word>() {
            @Override
            public void onListQueryResult(QueryTransaction transaction, @Nullable List<Word> tResult) {
                Log.d("Demo",""+tResult.toString());
                Log.d("Demo",""+tResult.get(10).getWord());
                sendWords(tResult);
                }


        }, new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                Log.d("DEMO", "Error here");
                error.printStackTrace();
            }
        });

    }

    private void sendWords(List<Word> tResult) {
        Log.d("DEMO",""+pos);
        int words_count=getDataManager().getWordCount();
        QuestionsList questionList = new QuestionsList(this.pos,tResult,this,words_count-1);
        questionList.getQuestions();
    }

    public void onCardExhausted(int correct,int incorrect,int unattempted){
        getMvpView().showResult(correct,incorrect,unattempted);
        Log.i("Demo:",correct+" out of "+(correct+incorrect+unattempted));
    }

    @Override
    public void call(List<Question> questions) {
        Log.d("DEMO","Here"+questions.get(0).getQuestion());
        getMvpView().refreshQuestionnaire(questions);
    }
}
