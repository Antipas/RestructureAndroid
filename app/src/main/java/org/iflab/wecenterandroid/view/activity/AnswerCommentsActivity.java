package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.jakewharton.rxbinding.view.RxView;

import org.iflab.wecenterandroid.modal.AnswerComment;
import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityAnswerCommentBinding;
import org.iflab.wecenterandroid.databinding.PopupCommentsBottomBarBinding;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.view.recyclerView.AnswerCommentsAdapter;
import org.iflab.wecenterandroid.view.recyclerView.CommentsAdapter;
import org.iflab.wecenterandroid.viewmodal.CommentViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

public class AnswerCommentsActivity extends BaseActivity {
    private static final String ANSWER_ID = "ANSWER_ID";

    ActivityAnswerCommentBinding activityCommentsBinding;
    CommentViewModel commentViewModel;
    RecyclerView recyclerView;
    InputMethodManager inputManager;
    AnswerCommentsAdapter commentsAdapter;
    int id;
    List dataList = new ArrayList();
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCommentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_answer_comment);
        commentViewModel = new CommentViewModel(getApplicationContext());
        activityCommentsBinding.setComment(commentViewModel);

        activityCommentsBinding.toolbar.setTitle("评论");
        setSupportActionBar(activityCommentsBinding.toolbar);

        recyclerView = activityCommentsBinding.recyclerviewComments;
        commentsAdapter = new AnswerCommentsAdapter(getApplicationContext(),dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(commentsAdapter);

        inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        id = getIntent().getIntExtra(ANSWER_ID,0);
        loadData();
        createBottomBar();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && !status) {
                    status = true;
                    showBottomBar();
                } else if(dy < 0 && status){
                    status = false;
                    dismissBottomBar();
                }
            }

        });
    }

    public static void startAnswerComments(int id, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, AnswerCommentsActivity.class);
        intent.putExtra(ANSWER_ID, id);
        startingActivity.startActivity(intent);
    }
    private void createBottomBar() {

        RxView.clicks(activityCommentsBinding.btnSend)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        String comment = activityCommentsBinding.editTxtComment.getText().toString();
                        if (comment.length() == 0) {
                            return;
                        }
                        sendComment(comment);
                    }
                });

    }

    private void sendComment(String comment){
        Subscription subscription = commentViewModel.sendAnswerComment(id, comment)
                .subscribe(new Observer<SaveComment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(SaveComment saveComment) {
                        activityCommentsBinding.editTxtComment.setText("");
                        dataList.clear();
                        loadData();
                        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                });
        addSubscription(subscription);
    }


    private void showBottomBar() {
        if(activityCommentsBinding.bottomBarContainer.getTranslationY() != 0){
            activityCommentsBinding.bottomBarContainer.animate()
                    .translationY(0)
                    .setDuration(400L)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            activityCommentsBinding.bottomBarContainer.setTranslationY(0);
                        }
                    })
                    .start();

        }
    }

    private void dismissBottomBar() {
        final float height = (float) activityCommentsBinding.bottomBarContainer.getHeight();
        if(activityCommentsBinding.bottomBarContainer.getTranslationY() == 0){
            activityCommentsBinding.bottomBarContainer.animate()
                    .translationY(height)
                    .setDuration(400L)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            activityCommentsBinding.bottomBarContainer.setTranslationY(height);
                        }
                    })
                    .start();
        }
    }

    @Override
    protected void loadData() {
        Subscription subscription = commentViewModel.loadAnswerComment(id)
                .subscribe(new Observer<List<AnswerComment.RsmEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(List<AnswerComment.RsmEntity> commentList) {
                        if(commentList.size() != 0){
                            dataList.addAll(commentList);
                            commentsAdapter.notifyDataSetChanged();
                        }
                    }
                });
        addSubscription(subscription);
    }


}
