package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.PersonCenterItem;
import org.iflab.wecenterandroid.modal.User;
import org.iflab.wecenterandroid.modal.UserAPIModal;
import org.iflab.wecenterandroid.modal.prefs.UserPrefs;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.recyclerView.PersonCenterAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Lyn on 16/1/28.
 */
public class PersonCenterViewModal extends BaseViewModel {

    UserAPIModal.RsmEntity user;

    public PersonCenterViewModal(Context context,UserAPIModal.RsmEntity user) {
        super(context);
        this.user = user;
    }

    /**
     *  header
     */

    @BindingAdapter({"bind:headerAvatar"})
    public static void loadImage(ImageView view, String url) {
        if(TextUtils.isEmpty(url)){
            return ;
        }
        Picasso.with(view.getContext()).load( url).transform(new RoundedTransformation()).into(view);
    }

    public String getAvatar(){
        if(user == null){
            return "";
        }
        return user.getAvatar_file();
    }

    public String getPublishCount(){
        if(user == null){
            return "";
        }
        return String.valueOf(user.getArticle_count());
    }

    public String getFollowingCount(){
        if(user == null){
            return "";
        }
        return String.valueOf(user.getFans_count());
    }

    public String getFansCount(){
        if(user == null){
            return "";
        }
        return String.valueOf(user.getFans_count());
    }

    /**
     *  userinfo
     */
    public String getSignature(){
        if(user == null){
            return "暂无介绍";
        }
        return user.getSignature();
    }

    public String getUserName(){
        if(user == null){
            return "";
        }
        return user.getUser_name();
    }

    public boolean getIsMySelf(){
        return user.getUid() == UserPrefs.getInstance(context).getUserId();
    }

    public boolean getHasFocus(){
        return user.getHas_focus() == 1;
    }

    public void setHasFocus(boolean status){
        user.setHas_focus(status ? 1 : 0);
    }
}
