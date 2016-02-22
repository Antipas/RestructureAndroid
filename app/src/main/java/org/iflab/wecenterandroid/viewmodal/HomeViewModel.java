package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.util.ArrayMap;

import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.DataManager;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.Topic;
import org.iflab.wecenterandroid.modal.home.Home105;
import org.iflab.wecenterandroid.view.recyclerView.HomeAdapter;

import java.util.List;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lyn on 15/11/21.
 */
public class HomeViewModel extends BaseViewModel{

    Home home;

    public HomeViewModel(Context context) {
        super(context);
    }


    public Observable loadHome(int page){
        return dataManager.loadHome(page)
                .doOnNext(new Action1<List<Home>>() {
                    @Override
                    public void call(List<Home> list) {
                        if(list == null){
                            OnErrorThrowable.from(new Throwable("home list null"));
                        }
                    }
                });
    }

}
