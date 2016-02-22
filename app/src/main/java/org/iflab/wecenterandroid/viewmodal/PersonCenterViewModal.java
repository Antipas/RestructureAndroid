package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.PersonCenterItem;
import org.iflab.wecenterandroid.modal.User;
import org.iflab.wecenterandroid.modal.UserAPIModal;
import org.iflab.wecenterandroid.view.recyclerView.PersonCenterAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Lyn on 16/1/28.
 */
public class PersonCenterViewModal extends BaseViewModel {

    public List<PersonCenterItem> itemList;

    public PersonCenterViewModal(Context context) {
        super(context);
    }

    public Observable loadUserInfo(String uid){
        return dataManager.loadUserinfo(uid);
    }
}
