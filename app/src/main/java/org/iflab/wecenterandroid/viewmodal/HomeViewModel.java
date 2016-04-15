package org.iflab.wecenterandroid.viewmodal;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.Constant;
import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.home.Home503;
import org.iflab.wecenterandroid.util.DisplayUtil;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.view.activity.QRCodeActivity;

import java.util.List;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;

/**
 * Created by Lyn on 15/11/21.
 */
public class HomeViewModel extends BaseViewModel{

    Home home;
    Context context;
    public HomeViewModel(Context context) {
        super(context);
    }

    public HomeViewModel(Context context,Home home) {
        super(context);
        this.context = context;
        this.home = home;
    }

    @BindingAdapter({"bind:homeAvatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load( url).transform(new RoundedTransformation()).into(view);
    }

    @BindingAdapter({"bind:thumb"})
    public static void loadThumb(ImageView view, String url) {
        if(TextUtils.isEmpty(url)){
            Picasso.with(view.getContext()).load(R.drawable.error).into(view);
        }else
            Picasso.with(view.getContext()).load(url).into(view);
    }

    public String getAvatarFile(){
        return home.getUser_info().getAvatar_file();
    }

    public String getAddTime(){
        return DisplayUtil.lossOfTime(home.getAdd_time());
    }

    public Spannable getViewsCount(){
        Spannable spannable = new SpannableString(DisplayUtil.formatCount(home.getArticle_info().getViews())+" 已阅读");
        int endIndex = spannable.toString().length() - 3;
        spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.tablayout_black_bg)), 0, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public String getThumb(){
        return home.getImgUrl();
    }

    public String getOutLine(){
        return home.getOutline();
    }


    public String getComments(){
        if(home instanceof Home503)
            return ((Home503)home).getComment_info().getMessage();
        else
            return "";
    }

    public String getTitle(){
        return home.getArticle_info().getTitle();
    }

    public int getAssociateAction(){
        return home.getAssociate_action();
    }
    public String getUserName(){
        return home.getUser_info().getUser_name();
    }


    public View.OnClickListener onClickAvatar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonCenterActivity.startPersonCenter(home.getUser_info().getUid(), ((Activity) context), v);
            }
        };
    }

    public View.OnClickListener onClickTitle() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleActivity.startArticle(context,home.getArticle_info().getId(),ArticleActivity.INNER_ARTICLE,home.getUrl());
            }
        };
    }

    public View.OnClickListener onClickSao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QRCodeActivity.startQRCodeActivity(context);
            }
        };
    }

}
