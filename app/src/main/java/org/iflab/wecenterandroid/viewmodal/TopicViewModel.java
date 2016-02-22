package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.DataManager;
import org.iflab.wecenterandroid.modal.Topic;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

/**
 * Created by Lyn on 15/12/12.
 */
public class TopicViewModel extends BaseViewModel{

    public TopicViewModel(Context context) {
        super(context);
    }

    public Observable loadTopic(int page, String type){
        return dataManager.loadTopics(page,type)
                .map(new Func1<Topic,Topic.RsmEntity>() {
                    @Override
                    public Topic.RsmEntity call(Topic topic) {
                        if(topic.getRsm() == null){
                            OnErrorThrowable.from(new Throwable(topic.getErr().toString()));
                        }
                        return topic.getRsm();
                    }
                });
  }
}
