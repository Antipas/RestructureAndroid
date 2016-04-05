package org.iflab.wecenterandroid.viewmodal;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.modal.explore.ExploreArticle;
import org.iflab.wecenterandroid.modal.explore.ExploreQuestion;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.view.activity.QuestionActivity;

import rx.Observable;

/**
 * Created by Lyn on 15/11/21.
 */
public class ExploreViewModal extends BaseViewModel{

    ExploreQuestion exploreQuestion;
    ExploreArticle exploreArticle;
    Context context;
    public ExploreViewModal(Context context) {
        super(context);
    }

    public ExploreViewModal(Context context,Explore explore) {
        super(context);
        this.context = context;
        if(explore instanceof ExploreQuestion){
            this.exploreQuestion = (ExploreQuestion)explore;
        }else if(explore instanceof ExploreArticle){
            this.exploreArticle = (ExploreArticle)explore;
        }
    }

    @BindingAdapter({"bind:explore_avatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public String getAvatarFile(){
        if(exploreQuestion !=null) {
            return exploreQuestion.getUser_info().getAvatar_file();
        }else if(exploreArticle != null){
            return exploreArticle.getUser_info().getAvatar_file();
        }else
            return "";
    }

    public String getUserName(){
        if(exploreQuestion !=null) {
            return exploreQuestion.getUser_info().getUser_name();
        }else if(exploreArticle != null){
            return exploreArticle.getUser_info().getUser_name();
        }else
            return "";
    }

    public String getVotes(){
        if(exploreQuestion !=null) {
            return exploreQuestion.getView_count()+"";
        }else if(exploreArticle != null){
            return exploreArticle.getVotes()+"";
        }else
            return "";
    }

    public String getMessage(){
        return exploreArticle.getMessage();
    }

    public String getQuestionContent(){
        return exploreQuestion.getQuestion_content();
    }

    public View.OnClickListener onClickAvatar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exploreQuestion !=null) {
                    PersonCenterActivity.startPersonCenter(exploreQuestion.getUser_info().getUid(),(Activity)context,v);
                }else if(exploreArticle != null){
                    PersonCenterActivity.startPersonCenter(exploreArticle.getUser_info().getUid(),(Activity)context,v);
                }
            }
        };
    }

    public View.OnClickListener onClickTitle() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exploreQuestion != null){
                    QuestionActivity.startQuestion(exploreQuestion.getQuestion_id(),(Activity)context,v);
                }else if(exploreArticle != null){
                    ArticleActivity.startArticle(exploreArticle.getId(),context);
                }
            }
        };
    }

    public Observable loadExplore(int page,int day,int recommend,String type){
        return dataManager.loadExplore(page,day,recommend,type);
    }

}
