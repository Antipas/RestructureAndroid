package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;

import org.iflab.wecenterandroid.modal.AnswerComment;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.Comments;
import org.iflab.wecenterandroid.modal.SaveComment;

import java.util.List;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lyn on 16/1/1.
 */
public class CommentViewModel extends BaseViewModel{

    public CommentViewModel(Context context) {
        super(context);
    }

    public Observable<Comments.RsmEntity> loadComment(int id,int page){
        return dataManager.loadComments(id,page)
                .map(new Func1<Comments, Comments.RsmEntity>() {
                    @Override
                    public Comments.RsmEntity call(Comments comments) {
                        if (comments.getRsm() == null) {
                            OnErrorThrowable.from(new Throwable(comments.getErr().toString()));
                        }

                        return comments.getRsm();
                    }
                });
    }

    public Observable sendComment(int id,String comment){
        return dataManager.sendComment(id, comment)
                .doOnNext(new Action1<SaveComment>() {
                    @Override
                    public void call(SaveComment saveComment) {
                        if (saveComment.getRsm() == null) {
                            OnErrorThrowable.from(new Throwable(saveComment.getErr().toString()));
                        }
                    }
                });
    }

    public Observable loadAnswerComment(int id){
        return dataManager.loadAnswerComments(id)
                .doOnNext(new Action1<AnswerComment>() {
                    @Override
                    public void call(AnswerComment answerComment) {
                        if (answerComment.getRsm() == null) {
                            OnErrorThrowable.from(new Throwable(answerComment.getErr().toString()));
                        }
                    }
                }).map(new Func1<AnswerComment,List<AnswerComment.RsmEntity>>() {
                    @Override
                    public List<AnswerComment.RsmEntity> call(AnswerComment answerComment) {
                        return answerComment.getRsm();
                    }
                });
    }

    public Observable sendAnswerComment(int id,String message){
        return dataManager.sendAnswerComment(id,message)
                .doOnNext(new Action1<SaveComment>() {
                    @Override
                    public void call(SaveComment answerComment) {
                        if (answerComment.getRsm() == null) {
                            OnErrorThrowable.from(new Throwable(answerComment.getErr().toString()));
                        }
                    }
                });
    }

}
