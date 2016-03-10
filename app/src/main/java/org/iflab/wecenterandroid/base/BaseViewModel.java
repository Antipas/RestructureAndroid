package org.iflab.wecenterandroid.base;

import android.content.Context;
import android.databinding.BaseObservable;

import org.iflab.wecenterandroid.modal.DataManager;

/**
 * Created by Lyn on 16/1/1.
 */
public abstract class BaseViewModel extends BaseObservable {
    public DataManager dataManager;
    public Context context;

    public BaseViewModel(Context context){
        this.context = context;
        dataManager = DataManager.getInstance(context);
    }
}
