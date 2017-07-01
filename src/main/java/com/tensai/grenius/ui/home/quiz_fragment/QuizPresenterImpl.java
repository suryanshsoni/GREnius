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
                Log.d("Demo",""+tResult.get(0).getWord());
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
        QuestionsList questionList = new QuestionsList(this.pos,tResult,this);
    }

    @Override
    public void call(List<Question> questions) {
        getMvpView().refreshQuestionnaire(questions);
    }
}
