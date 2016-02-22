package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;

import com.squareup.okhttp.RequestBody;

import org.iflab.wecenterandroid.base.BaseViewModel;

import rx.Observable;

/**
 * Created by Lyn on 16/1/17.
 */
public class PublishViewModal extends BaseViewModel {
    public PublishViewModal(Context context) {
        super(context);
    }

    public Observable uploadAttach(RequestBody id,RequestBody key,RequestBody file){
        return dataManager.uploadAttach(file,key,id);
    }

    public Observable uploadArticleText(String title,String attach_access_key,String message,String topics){
        return dataManager.uploadArticleText(title, attach_access_key, message, topics);
    }

    public Observable uploadQuestionText(String title,String attach_access_key,String content,String topics){
        return dataManager.uploadQuestionext(title, attach_access_key, content, topics);
    }

    public Observable uploadAnswerText(String id,String attach_access_key,String content ){
        return dataManager.uploadAnswerText(id, attach_access_key, content);
    }

}
