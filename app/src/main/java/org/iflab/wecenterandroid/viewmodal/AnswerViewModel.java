package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Html;
import android.text.Spanned;

import org.iflab.wecenterandroid.AnswerInfo;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.Answer;
import org.iflab.wecenterandroid.modal.QuestionFocus;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.WecenterImageGetter;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lyn on 16/1/7.
 */
public class AnswerViewModel extends BaseViewModel {
    public ObservableField<String> question = new ObservableField<>();
    public ObservableInt likeNum = new ObservableInt();
    public ObservableInt isRating = new ObservableInt();
    public ObservableField<Answer.RsmEntity.AnswerEntity.UserInfoEntity> userInfo = new ObservableField<>();
    Context context;

    public AnswerViewModel(Context context) {
        super(context);
        this.context = context;
    }

    public Observable loadAnswer(int id){
        return dataManager.loadAnswer(id)
                .map(new Func1<Answer,AnswerInfo>() {
                    @Override
                    public AnswerInfo call(Answer answer) {
                        if(answer.getRsm() == null){
                            OnErrorThrowable.from(new Throwable(answer.getErr().toString()));
                        }
                        Answer.RsmEntity.AnswerEntity answerEntity = answer.getRsm().getAnswer();
                        question.set(answerEntity.getQuestion_content());
                        likeNum.set(answerEntity.getAgree_count());
                        isRating.set(answerEntity.getUser_vote_status());
                        userInfo.set(answerEntity.getUser_info());

                        WecenterImageGetter wecenterImageGetter = new WecenterImageGetter.Builder(context).build();
                        Spanned spanned = Html.fromHtml(answerEntity.getAnswer_content(), wecenterImageGetter, null);

                        AnswerInfo answerInfo = new AnswerInfo();
                        answerInfo.setContent(spanned);
                        answerInfo.setUid(answerEntity.getUser_info().getUid());
                        return answerInfo;
                    }
                })
                .compose(dataManager.applySchedulers());
    }

    public Observable<QuestionFocus> voteOrFuckAnswer(int itemId,int rating) {
        return dataManager.voteOrFuckAnswer(itemId, rating)
                .doOnNext(new Action1<QuestionFocus>() {
                    @Override
                    public void call(QuestionFocus result) {
                        if (result.getErr() != null) {
                            OnErrorThrowable.from(new Throwable(result.getErr().toString()));
                        }
                    }
                });
    }
}
