package org.iflab.wecenterandroid.viewmodal;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.view.activity.PublishActivity;

/**
 * Created by lyn on 16-3-11.
 */
public class QuestionViewModal extends BaseViewModel{
    int questionID;
    Question.RsmEntity entity;

    public QuestionViewModal(Context context) {
        super(context);
    }

    public QuestionViewModal(Context context,int questionID,Question.RsmEntity entity) {
        super(context);
        this.questionID = questionID;
        this.entity = entity;
    }

    @BindingAdapter({"bind:setToolbar"})
    public static void setToolbar(Toolbar toolbar, String content){
        toolbar.setTitle(content);
    }

    public int getAnswerNum(){
        return entity.getQuestion_info().getAnswer_count();
    }

    public View.OnClickListener onClickFab(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] loc = new int[2];
                view.getLocationOnScreen(loc);
                PublishActivity.startPublishAnswer(PublishActivity.ANSWER,(Activity) context,questionID+"",loc);
            }
        };
    }

}
