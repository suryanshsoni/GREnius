package com.tensai.grenius.ui.home.institutes;

import com.tensai.grenius.data.DataManager;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rishabhpanwar on 26/10/17.
 */

public class InstitutePresenterImpl <V extends InstituteView> extends BasePresenter<V> implements InstitutePresenter<V> {

    @Inject
    public InstitutePresenterImpl(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getInstitutes() {

        getDataManager().getInstitutes(getDataManager().getCurrentUserid(),getDataManager().getSessionId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, List<Institute>>() {
                    @Override
                    public List<Institute> call(Throwable throwable) {
                        return null;
                    }
                })
                .subscribe(new Action1<List<Institute>>() {
                    @Override
                    public void call(List<Institute> institutes) {
                        try{
                            List<Institute> gre_institutes = new ArrayList<Institute>();
                            List<Institute> cat_institutes = new ArrayList<Institute>();
                            for(int i=0; i<institutes.size();i++){
                                if(institutes.get(i).getType().equalsIgnoreCase("GRE")){
                                    gre_institutes.add(institutes.get(i));
                                }else {
                                    cat_institutes.add(institutes.get(i));
                                }
                            }
                            getMvpView().showInstitutes(gre_institutes,cat_institutes);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
    }
}
