package org.iflab.wecenterandroid.viewmodal;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.DataManager;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.Topic;
import org.iflab.wecenterandroid.modal.home.Home101;
import org.iflab.wecenterandroid.modal.home.Home105;
import org.iflab.wecenterandroid.modal.home.Home201;
import org.iflab.wecenterandroid.modal.home.Home501;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.AnswerActivity;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.view.activity.QuestionActivity;
import org.iflab.wecenterandroid.view.recyclerView.HomeAdapter;

import java.util.List;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lyn on 15/11/21.
 */
public class HomeViewModel extends BaseViewModel{

    Home501 home501;
    Home101 home101;
    Home201 home201;
    Context context;
    public HomeViewModel(Context context) {
        super(context);
    }

    public HomeViewModel(Context context,Home home) {
        super(context);
        this.context = context;
        if(home instanceof Home501){
            this.home501 = (Home501)home;
        }else if(home instanceof Home101){
            this.home101 = (Home101)home;
        }else if(home instanceof Home201){
            this.home201 = (Home201)home;
        }
    }

    @BindingAdapter({"bind:homeAvatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public String getAvatarFile(){
        if(home501 !=null) {
            return home501.getUser_info().getAvatar_file();
        }else if(home101 != null){
            return home101.getUser_info().getAvatar_file();
        }else if(home201 != null){
            return home201.getUser_info().getAvatar_file();
        }else
            return "";
    }

    public String getComments(){
        return home501.getArticle_info().getComments()+"";
    }

    public String getTitle(){
        if(home501 != null){
            return home501.getArticle_info().getTitle();
        }else if(home101 != null){
            return home101.getQuestion_info().getQuestion_content();
        }else if(home201 != null){
            return home201.getAnswer_info().getAnswer_content();
        }else
            return "";
    }

    public int getAssociateAction(){
        if(home501 != null){
            return home501.getAssociate_action();
        }else if(home101 != null){
            return home101.getAssociate_action();
        }else if(home201 != null){
            return home201.getAssociate_action();
        }
        else
            return 0;
    }
    public String getUserName(){
        if(home501 != null) {
            return home501.getUser_info().getUser_name();
        }else if(home101 != null){
            return home101.getUser_info().getUser_name();
        }else if(home201 != null){
            return home201.getUser_info().getUser_name();
        }else
            return "";
    }

    public String getAgree_count(){
        if(home101 != null){
            return home101.getQuestion_info().getAgree_count()+"";
        }else if(home201 != null){
            return home201.getQuestion_info().getAgree_count()+"";
        }else
            return "";

    }

    public View.OnClickListener onClickAvatar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(home501 != null) {
                    PersonCenterActivity.startPersonCenter(home501.getUser_info().getUid(), ((Activity) context), v);
                }else if(home101 != null){
                    PersonCenterActivity.startPersonCenter(home101.getUser_info().getUid(), ((Activity) context), v);
                }else if(home201 != null){
                    PersonCenterActivity.startPersonCenter(home201.getUser_info().getUid(), ((Activity) context), v);
                }
            }
        };
    }

    public View.OnClickListener onClickTitle() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(home501 != null) {
                    ArticleActivity.startArticle(home501.getArticle_info().getId(), context);
                }else if(home101 != null){
                    QuestionActivity.startQuestion(home101.getQuestion_info().getQuestion_id(),((Activity) context),v);
                }else if(home201 != null){
                    AnswerActivity.startAnswer(home201.getAnswer_info().getAnswer_id(),((Activity) context));
                }
            }
        };
    }

    public Observable loadHome(int page){
        return dataManager.loadHome(page)
                .doOnNext(new Action1<List<Home>>() {
                    @Override
                    public void call(List<Home> list) {
                        if(list == null){
                            OnErrorThrowable.from(new Throwable("home list null"));
                        }
                    }
                });
    }

}
