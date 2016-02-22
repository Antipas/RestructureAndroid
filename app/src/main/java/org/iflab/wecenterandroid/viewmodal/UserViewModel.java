package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.LoginInfo;
import org.iflab.wecenterandroid.modal.User;
import org.iflab.wecenterandroid.modal.prefs.UserPrefs;

import retrofit.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Lyn on 15/11/17.
 */
public class UserViewModel extends BaseViewModel{
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();

    public UserViewModel(Context context) {
        super(context);
    }


    public Observable loadLogin(String name, String pwd) {
        return dataManager.login(name, pwd)
                .doOnNext(new Action1<Response<LoginInfo>>() {
                    @Override
                    public void call(Response<LoginInfo> response) {
                        LoginInfo info = response.body();

                    }
                });
    }

    public void setLoggedInUser(User user){
        dataManager.getUserPrefs().setLoggedInUser(user);
    }

    public void setCookie(String cookie){
        dataManager.getUserPrefs().setCookie(cookie);
    }


    @BindingAdapter({"bind:navHeaderAvatarUrl"})
    public static void loadImage(ImageView view, String url) {
       Picasso.with(view.getContext()).load(UserPrefs.getInstance(view.getContext()).getUserAvatar()).into(view);
    }

    @BindingAdapter({"bind:displayName"})
    public static void displayName(TextView view, String text) {
        view.setText(UserPrefs.getInstance(view.getContext()).getUserName());
    }

    @BindingAdapter({"bind:displayDesc"})
    public static void displayDesc(TextView view, String text) {
        view.setText(UserPrefs.getInstance(view.getContext()).getDesc());
    }

}
