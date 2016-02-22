package org.iflab.wecenterandroid.base;

import android.content.Context;

import org.iflab.wecenterandroid.modal.DataManager;

/**
 * Created by Lyn on 16/1/1.
 */
public abstract class BaseViewModel {
    public DataManager dataManager;

    public BaseViewModel(Context context){
        dataManager = DataManager.getInstance(context);
    }
}
