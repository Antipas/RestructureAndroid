package org.iflab.wecenterandroid.viewmodal;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.AnswerActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;

/**
 * Created by lyn on 16-3-10.
 */
public class QuestionAnswerViewModal extends BaseViewModel{
    Question.RsmEntity.AnswersEntity answersEntity;
    public QuestionAnswerViewModal(Context context) {
        super(context);
    }

    public QuestionAnswerViewModal(Context context,Question.RsmEntity.AnswersEntity answersEntity) {
        super(context);
        this.answersEntity = answersEntity;
    }

    @BindingAdapter({"bind:answerAvatar"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public String getAvatarFile(){
        return answersEntity.getUser_info().getAvatar_file();
    }

    public String getUserName(){
        return answersEntity.getUser_info().getUser_name();
    }

    public String getTitle(){
        return answersEntity.getAnswer_content();
    }

    public String getAgreeCount(){
        return answersEntity.getAgree_count()+"";
    }

    public View.OnClickListener onClickAvatar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonCenterActivity.startPersonCenter(answersEntity.getUser_info().getUid(), ((Activity) context), v);
            }
        };
    }

    public View.OnClickListener onClickTitle() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerActivity.startAnswer(answersEntity.getAnswer_id(),((Activity) context));
            }
        };
    }
}
