package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import org.iflab.wecenterandroid.base.BaseViewModel;

import rx.Observable;

/**
 * Created by Lyn on 16/1/31.
 */
public class PersonInfoViewModal extends BaseViewModel {
    public ObservableField<String> avatar = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();


    public PersonInfoViewModal(Context context) {
        super(context);
    }

    public void setUserInfo(String avatar,String name){
        this.avatar.set(avatar);
        userName.set(name);
    }

    public Observable loadFollows(String uid,int page){
        return dataManager.loadUserFollow(uid,page);
    }

    public Observable loadFans(String uid,int page){
        return dataManager.loadUserFans(uid, page);
    }

    public Observable loadArticle(String uid,int page){
        return dataManager.loadUserArticle(uid, page);
    }

    public Observable loadQuestion(String uid,int page){
        return dataManager.loadUserQuestion(uid, page);
    }

    public Observable loadAnswer(String uid,int page){
        return dataManager.loadUserAnswer(uid,page);
    }

}
