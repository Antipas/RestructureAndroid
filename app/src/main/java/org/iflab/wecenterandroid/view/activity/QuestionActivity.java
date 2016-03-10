package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityQuestionBinding;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.modal.QuestionFocus;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.view.recyclerView.DividerItemDecoration;
import org.iflab.wecenterandroid.view.recyclerView.QuestionAnswerAdapter;
import org.iflab.wecenterandroid.viewmodal.QuestionViewModal;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

public class QuestionActivity extends BaseActivity {

    private static final String QUESTION_ID = "QUESTION_ID";
    ActivityQuestionBinding activityQuestionBinding;
    Subscription subscription;
    QuestionAnswerAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    List dataList = new ArrayList();
    int id;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityQuestionBinding = DataBindingUtil.setContentView(this, R.layout.activity_question);

        adapter = new QuestionAnswerAdapter(QuestionActivity.this,dataList);
        recyclerView = activityQuestionBinding.recyclerviewAnswer;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        toolbar = activityQuestionBinding.toolbar;
        setUpToolBar(toolbar);

        id = getIntent().getIntExtra(QUESTION_ID,0);

        RxView.clicks(activityQuestionBinding.fabComment)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        int[] loc = new int[2];
                        activityQuestionBinding.fabComment.getLocationOnScreen(loc);
                        PublishActivity.startPublishAnswer(PublishActivity.ANSWER,QuestionActivity.this,id+"",loc);
                    }
                });

        Slide slide = new Slide(Gravity.BOTTOM);
        getWindow().setEnterTransition(slide);


        if(id != 0){
            loadData();
        }

    }

    public static void startQuestion(int id, Activity host,View transitionView) {
        Intent intent = new Intent(host, QuestionActivity.class);
        intent.putExtra(QUESTION_ID, id);
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(host,
                        Pair.create(transitionView,host.getString(R.string.transition_question_backgroung)));
        host.startActivity(intent,options.toBundle());
    }

    @Override
    protected void loadData() {
        subscription = dataManager.loadQuestion(id,page)
                .subscribe(new Observer<Question>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Question question) {
                        Question.RsmEntity entity = question.getRsm();
                        if(entity != null){
                            activityQuestionBinding.setQuestion(new QuestionViewModal(QuestionActivity.this,entity));
                            dataList.addAll(entity.getAnswers());
                            adapter.notifyDataSetChanged();
                        }

                    }
                });
        addSubscription(subscription);
    }


}
