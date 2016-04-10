package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.Article;
import org.iflab.wecenterandroid.modal.ArticleWrapper;
import org.iflab.wecenterandroid.modal.DataManager;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.util.WecenterImageGetter;

import java.util.concurrent.TimeUnit;

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

    Article article;
    ObservableBoolean hasAdded = new ObservableBoolean();

    public ArticleViewModel(Context context) {
        super(context);
    }

    public ArticleViewModel(Context context,Article article ){
        super(context);
        this.article = article;
    }

    @BindingAdapter({"bind:articleAvatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public String getAvatar(){
        return article.getRsm().getArticle_info().getUser_info().getAvatar_file();
    }

    public String getName(){
        return article.getRsm().getArticle_info().getUser_info().getUser_name();
    }

    public String getCommentCount(){
        return String.valueOf(article.getRsm().getArticle_info().getComments());
    }

    public boolean getIsFavorite(){
        return article.getRsm().getArticle_info().getIs_favorite() == 1;
    }

    public boolean getIsLike(){
        return article.getRsm().getArticle_info().getUser_follow_check() == 1;
    }


    public void setIsLike(Boolean status) {
        article.getRsm().getArticle_info().setUser_follow_check(status ? 1: 0);
    }

    public void setIsFavorite(Boolean status) {
        article.getRsm().getArticle_info().setIs_favorite(status ? 1: 0);
    }

    public boolean getHasAdded() {
        return hasAdded.get();
    }

    public void setHasAdded(boolean hasAdded) {
        this.hasAdded.set(hasAdded);
    }
}
