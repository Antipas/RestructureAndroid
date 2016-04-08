package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.view.View;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.Article;
import org.iflab.wecenterandroid.modal.ArticleWrapper;
import org.iflab.wecenterandroid.modal.DataManager;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.WecenterImageGetter;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lyn on 15/12/26.
 */
public class ArticleViewModel extends BaseViewModel{

    Context context;

    public ArticleViewModel(Context context) {
        super(context);
        this.context = context;
    }




    public Observable<SaveComment> favorite(int itemId,String type){
        return dataManager.favorite(itemId, type)
                .doOnNext(new Action1<SaveComment>() {
                    @Override
                    public void call(SaveComment result) {
                        if (result.getErr() != null) {
                            OnErrorThrowable.from(new Throwable(result.getErr().toString()));
                        }
                    }
                });
    }

    public Observable<SaveComment> voteOrFuckArticle(int itemId,String type,int rating){
        return dataManager.voteOrFuckArticle(itemId,type,rating)
                .doOnNext(new Action1<SaveComment>() {
                    @Override
                    public void call(SaveComment result) {
                        if (result.getErr() != null) {
                            OnErrorThrowable.from(new Throwable(result.getErr().toString()));
                        }
                    }
                });
    }

}
