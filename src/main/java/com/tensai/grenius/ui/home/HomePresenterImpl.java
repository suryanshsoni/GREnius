package com.tensai.grenius.ui.home;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.tensai.grenius.R;
import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Word;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rishabhpanwar on 24/06/17.
 */

public class HomePresenterImpl <V extends HomeView> extends BasePresenter<V> implements HomePresenter<V> {

    @Inject
    public HomePresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    public void getUserDetails() {
        String userId = getDataManager().getCurrentUserid();
        String userName = getDataManager().getCurrentUserName();
        getMvpView().showUserDetails(userId, userName);
    }

    @Override
    public List<Word> getMarkedWords() {
        List<Word> list = getDataManager().getMarkedWords();
        return list;
    }

    @Override
    public void uploadBookmarkedWords(ArrayList<Word> words) {
        if(!getMvpView().isNetworkConnected()){
            getMvpView().showToast(String.valueOf(R.string.network_error));
        }
        else{
            getMvpView().showLoading("Uploading Progress!");
            getDataManager().sendBookmarkWords(words,getDataManager().getCurrentUserid(),getDataManager().getSessionId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BookmarkWordsResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("ZXC:","in error");
                        }

                        @Override
                        public void onNext(BookmarkWordsResponse bookmarkWordsResponse) {
                            getMvpView().hideLoading();
                            Log.i("ZXC:","in onNext");
                        }
                    });
        }
    }

    public int getResourceId() {
        return getDataManager().getResourceId();
    }

    public void setResourceId(int resourceId) {
        getDataManager().setResourceId(resourceId);
    }

    public int getWordCount() {
        return getDataManager().getWordCount();
    }

    @Override
    public void update() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                getMvpView().showToast("Started Downloading");
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //Thread.sleep(5000);

                    downloadData();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void response) {
                getMvpView().showToast("Downloaded everything");

            }
        }.execute();
    }


    public void downloadData() {
        //final int index=getDataManager().getWordCount();
        getDataManager().downloadWords(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Word>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Demo HomePresenter", e.getMessage());
                    }

                    @Override
                    public void onNext(List<Word> words) {
                        //do work here
                        int total=0;
                        if(words!=null){
                            total=words.size();
                            int count=1;
                            for(Word word :words){

                                Log.i("DEMO",""+count++);
                                word.save();
                            }
                            getDataManager().setWordCount(total);
                            getMvpView().showToast("Downloaded words");
                        }

                    }
                });
        //final int categoryIndex=getDataManager().getCategoryCount();
        getDataManager().getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Category>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LoginPresenter", e.getMessage());
                    }

                    @Override
                    public void onNext(List<Category> categories) {
                        //do work here
                        int total=0;
                        if(categories!=null){
                            total=categories.size();
                            int count=1;
                            for(Category category :categories){

                                Log.i("DEMO",""+count++);
                                category.save();
                            }
                        }

                        getDataManager().setCategoryCount(total);
                        getMvpView().showToast("Downloaded categories");

                    }
                });
    }

}