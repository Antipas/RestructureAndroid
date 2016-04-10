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
import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.modal.explore.ExploreArticle;
import org.iflab.wecenterandroid.modal.explore.Famous;
import org.iflab.wecenterandroid.util.DisplayUtil;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;

/**
 * Created by Lyn on 15/11/21.
 */
public class ExploreViewModal extends BaseViewModel{


    ExploreArticle exploreArticle;
    Famous.RsmBean.RowsBean famous;
    Context context;
    public ExploreViewModal(Context context) {
        super(context);
    }

    public ExploreViewModal(Context context,Explore explore) {
        super(context);
        this.context = context;
        if(explore instanceof Famous.RsmBean.RowsBean){
            this.famous = (Famous.RsmBean.RowsBean)explore;
        }else if(explore instanceof ExploreArticle){
            this.exploreArticle = (ExploreArticle)explore;
        }
    }

    /**
     *  发现推荐
     */

    @BindingAdapter({"bind:explore_avatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    @BindingAdapter({"bind:explorethumb"})
    public static void loadThumb(ImageView view, String url) {
        if(TextUtils.isEmpty(url)){
            Picasso.with(view.getContext()).load(R.drawable.error).into(view);
        }else
            Picasso.with(view.getContext()).load(url).into(view);
    }

    public String getAvatarExploreFile(){
        return exploreArticle.getUser_info().getAvatar_file();
    }

    public Spannable getExploreViewsCount(){
        Spannable spannable = new SpannableString(exploreArticle.getViews()+" 已阅读");
        int endIndex = spannable.toString().length() - 3;
        spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.nav_item_checked)), 0, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public String getExploreThumb(){
        return exploreArticle.getImgUrl();
    }
    public String getExploreUserName(){
        return exploreArticle.getUser_info().getUser_name();
    }

    public String getExploreAddTime(){
        return DisplayUtil.lossOfTime(exploreArticle.getAdd_time());
    }

    public String getExploreOutLine(){
        return exploreArticle.getOutline();
    }

    public String getExploreTitle(){
        return exploreArticle.getTitle();
    }

    public View.OnClickListener onClickExploreAvatar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonCenterActivity.startPersonCenter(exploreArticle.getUser_info().getUid(),(Activity)context,v);

            }
        };
    }

    public View.OnClickListener onClickContentView() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exploreArticle != null){
                    ArticleActivity.startArticle(context,exploreArticle.getId(),ArticleActivity.INNER_ARTICLE,exploreArticle.getUrl());
                }
            }
        };
    }

    /**
     * 名人、媒体
     */
    @BindingAdapter({"bind:fm_avatar"})
    public static void loadFMAvatar(ImageView view, String url) {
        if(TextUtils.isEmpty(url)){
            Picasso.with(view.getContext()).load(R.drawable.error).into(view);
        }else
            Picasso.with(view.getContext()).load(Constant.AVATAR + url).into(view);
    }

    public String getFMAvatar(){
        return famous.getAvatar_file();
    }

    public String getFMName(){
        return famous.getUser_name();
    }

    public String getFMDesc(){
        return "介绍";
    }

    public int getFMFocusState(){
        return famous.getHas_focus();
    }

    public Spannable getFMArticleCount(){
        Spannable spannable = new SpannableString(famous.getArticle_count()+" 篇文章");
        int endIndex = spannable.toString().length() - 3;
        spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.nav_item_checked)), 0, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public Spannable getFMFansCount(){
        Spannable spannable = new SpannableString(famous.getFans_count()+" 人关注");
        int endIndex = spannable.toString().length() - 3;
        spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.nav_item_checked)), 0, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public boolean getFMHasFocus(){
        return famous.getHas_focus() == 1;
    }

    public void setFMHasFocus(boolean status){
        famous.setHas_focus(status ? 1 : 0);
        // becase will emit checkedChanges again
//        notifyChange();
    }

    public void setFMFansCount(boolean status){
        int originCount = famous.getFans_count();
        famous.setFans_count(status ? originCount + 1 : originCount - 1);
        notifyChange();
    }
}
