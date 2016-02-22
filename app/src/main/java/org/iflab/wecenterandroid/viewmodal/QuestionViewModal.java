package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.DataManager;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.modal.QuestionFocus;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lyn on 15/12/21.
 */
public class QuestionViewModal extends BaseViewModel{


    public QuestionViewModal(Context context) {
        super(context);
    }

    public Observable loadQuestion(int id,int page){
        return dataManager.loadQuestion(id,page)
                .map(new Func1<Question,Question.RsmEntity>() {
                    @Override
                    public Question.RsmEntity call(Question question) {
                        Question.RsmEntity rsmEntity = question.getRsm();
                        if(rsmEntity == null){
                            OnErrorThrowable.from(new Throwable(question.getErr().toString()));
                        }

                        return rsmEntity;
                    }
                });
    }

    public Observable addFocus(int id){
        return dataManager.addFocus(id)
                .doOnNext(new Action1<QuestionFocus>() {
                    @Override
                    public void call(QuestionFocus questionFocus) {
                        if(questionFocus.getRsm() == null){
                            OnErrorThrowable.from(new Throwable(questionFocus.getErr().toString()));
                        }
                    }
                });
    }


}
