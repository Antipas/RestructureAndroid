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

    Question.RsmEntity.QuestionInfoEntity.UserInfoEntity userInfoEntity = new Question.RsmEntity.QuestionInfoEntity.UserInfoEntity();

    public final ObservableInt likeNum = new ObservableInt();
    public final ObservableInt isRating = new ObservableInt();
    public final ObservableField<String> title = new ObservableField<>();

    Context context;

    public ArticleViewModel(Context context) {
        super(context);
        this.context = context;
    }


    public Observable<ArticleWrapper> loadArticle(int id,int page){
        return dataManager.loadArticle(id, page)
                .map(new Func1<Article, ArticleWrapper>() {
                    @Override
                    public ArticleWrapper call(Article article) {
                        if (article.getRsm() == null) {
                            OnErrorThrowable.from(new Throwable(article.getErr().toString()));
                        }
                        Article.RsmEntity.ArticleInfoEntity entity = article.getRsm().getArticle_info();
                        title.set(entity.getTitle());
                        likeNum.set(entity.getVotes());
                        if (entity.getVote_info() != null) {
                            isRating.set(entity.getVote_info().getRating());
                        }

                        userInfoEntity.setAvatar_file(entity.getUser_info().getAvatar_file());
                        userInfoEntity.setSignature(entity.getUser_info().getSignature());
                        userInfoEntity.setUser_name(entity.getUser_info().getUser_name());

                        WecenterImageGetter wecenterImageGetter = new WecenterImageGetter.Builder(context).build();
                        Spanned spanned = Html.fromHtml(entity.getMessage(), wecenterImageGetter, null);

                        ArticleWrapper articleWrapper = new ArticleWrapper.Builder(article)
                                .setSpan(spanned)
                                .setUserInfo(userInfoEntity)
                                .builder();
                        return articleWrapper;
                    }
                })
                .compose(dataManager.applySchedulers());
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
