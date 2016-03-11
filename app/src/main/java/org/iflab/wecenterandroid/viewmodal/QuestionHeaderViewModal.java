package org.iflab.wecenterandroid.viewmodal;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.DataManager;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.modal.QuestionFocus;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.AnswerActivity;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.view.activity.QuestionActivity;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lyn on 15/12/21.
 */
public class QuestionHeaderViewModal extends BaseViewModel{

    Question.RsmEntity question;
    public QuestionHeaderViewModal(Context context) {
        super(context);
    }

    public QuestionHeaderViewModal(Context context, Question.RsmEntity question) {
        super(context);
        this.question = question;
    }

    @BindingAdapter({"bind:userInfoItemAvatar"})
    public static void loadUserInfoItemAvatar(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    @BindingAdapter({"bind:addTags"})
    public static void addTags(TagFlowLayout tagContainer, List<Question.RsmEntity.QuestionTopicsEntity> tagList) {
        if(tagList == null){
            return;
        }
        String []topics = null;
        int len = tagList.size();
        if( len > 0){
            topics = new String[len];
            for (int i = 0;i < tagList.size();i++){
                topics[i] = tagList.get(i).getTopic_title();
            }
            tagContainer.setAdapter(new TagAdapter<String>(topics) {
                @Override
                public View getView(FlowLayout mFlowLayout, int position, String topic) {
                    TextView tag = (TextView) LayoutInflater.from(mFlowLayout.getContext()).inflate(R.layout.topic_single_item,
                            mFlowLayout, false);
                    tag.setText(topic);
                    return tag;
                }
            });
        }else {
            tagContainer.setVisibility(View.GONE);
        }
    }

    public String getAvatarFile(){
        return question.getQuestion_info().getUser_info().getAvatar_file();
    }

    public String getUserName(){
        return question.getQuestion_info().getUser_info().getUser_name();
    }

    public String getSign(){
        return question.getQuestion_info().getUser_info().getSignature();
    }

    public String getQuestionContent(){
        return question.getQuestion_info().getQuestion_content();
    }

    public String getFocusCount(){
        return question.getQuestion_info().getFocus_count()+"";
    }

    public String getAgreeCount(){
        return question.getQuestion_info().getAgree_count()+"";
    }

    public int getUserQuestionFocus(){
        return question.getQuestion_info().getUser_question_focus();
    }

    public void setgetUserQuestionFocus(int focus){
        question.getQuestion_info().setUser_question_focus(focus);
        notifyChange();
    }


    public List<Question.RsmEntity.QuestionTopicsEntity> getTags(){
        return question.getQuestion_topics();
    }

    public View.OnClickListener onClickUser() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonCenterActivity.startPersonCenter(question.getQuestion_info().getUser_info().getUid(),(Activity)context,v);
            }
        };
    }

    public View.OnClickListener onClickFocus() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.addFocus(question.getQuestion_info().getQuestion_id())
                        .subscribe(new Observer<QuestionFocus>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(QuestionFocus questionFocus) {
                                int status = questionFocus.getRsm().getType().equals("add")? 1:0;
                                setgetUserQuestionFocus(status);
                            }
                });
            }
        };
    }

}
