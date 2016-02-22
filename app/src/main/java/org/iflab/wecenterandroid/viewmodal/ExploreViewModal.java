package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.explore.Explore;

import java.util.List;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;

/**
 * Created by Lyn on 15/11/21.
 */
public class ExploreViewModal extends BaseViewModel{


    public ExploreViewModal(Context context) {
        super(context);
    }

    public Observable loadExplore(int page,int day,int recommend,String type){
        return dataManager.loadExplore(page,day,recommend,type);
    }

}
