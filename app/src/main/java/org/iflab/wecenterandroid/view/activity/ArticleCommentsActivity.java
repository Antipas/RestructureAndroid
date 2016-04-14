package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityCommentsBinding;
import org.iflab.wecenterandroid.modal.Comments;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.util.ImeUtils;
import org.iflab.wecenterandroid.util.SupportVersion;
import org.iflab.wecenterandroid.view.recyclerView.CommentsAdapter;
import org.iflab.wecenterandroid.viewmodal.CommentViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class ArticleCommentsActivity extends BaseActivity implements CommentViewModel.onClickSendListener{

    private static final String ARTICLE_ID = "article_id";
    public static final String COMMENT_COUNT = "COMMENT_COUNT";
    public static final String LIKE_COUNT = "LIKE_COUNT";

    ActivityCommentsBinding activityCommentsBinding;
    RecyclerView recyclerView;
    CommentsAdapter commentsAdapter;
    List dataList = new ArrayList();
    CommentViewModel commentViewModel;

    int id;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCommentsBinding = DataBindingUtil.setContentView(this,R.layout.activity_comments);

        setUpToolBar(activityCommentsBinding.toolbar);

        recyclerView = activityCommentsBinding.recyclerviewComments;
        commentsAdapter = new CommentsAdapter(getApplicationContext(),dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(commentsAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    dismissBottomBar();
                }else{
                    showBottomBar();
                }
            }
        });

        id = getIntent().getIntExtra(ARTICLE_ID,0);
        int commentCount = getIntent().getIntExtra(COMMENT_COUNT,0);
        int likeCount = getIntent().getIntExtra(LIKE_COUNT,0);

        commentViewModel = new CommentViewModel(getApplicationContext(),this);
        commentViewModel.setCommentCount(commentCount);
        commentViewModel.setLikeCount(likeCount);
        activityCommentsBinding.setComment(commentViewModel);

        loadData();
    }

    public static void startArticleComments(Activity startingActivity,int id,int commentCount,int likeCount) {
        Intent intent = new Intent(startingActivity, ArticleCommentsActivity.class);
        intent.putExtra(ARTICLE_ID, id);
        intent.putExtra(COMMENT_COUNT, commentCount);
        intent.putExtra(LIKE_COUNT, likeCount);
        startingActivity.startActivity(intent);
    }
    
    @Override
    public void onClickSend(){
        String comment = activityCommentsBinding.editTxtComment.getText().toString();
        if(comment.length() == 0){
            return;
        }
        showLoading();
        Subscription subscription = dataManager.sendComment(id,comment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaveComment>() {
                    @Override
                    public void onCompleted() {
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(SaveComment saveComment) {
                        if(saveComment.getRsm() != null){
                            activityCommentsBinding.editTxtComment.setText("");
                            refresh();
                            recyclerView.smoothScrollBy(0, recyclerView.getHeight());
                            ImeUtils.hideIme(activityCommentsBinding.getRoot());
                            commentViewModel.setCommentCount(commentViewModel.getCommentCount() + 1);
                        }
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

    private void showLoading(){
        activityCommentsBinding.avloadingIndicatorView.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        activityCommentsBinding.avloadingIndicatorView.setVisibility(View.GONE);
    }

    private void refresh(){
        page = 1;
        dataList.clear();
        loadData();
    }

    @Override
    protected void loadData() {

        Subscription subscription = dataManager.loadComments(id, page)
                .subscribe(new Observer<Comments>() {
                    @Override
                    public void onCompleted() {
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Comments comments) {
                        Comments.RsmEntity rsmEntity = comments.getRsm();
                        if(rsmEntity.getTotal_rows() != 0){
                            dataList.addAll(rsmEntity.getRows());
                            commentsAdapter.notifyDataSetChanged();
                        }
                    }
                });
        addSubscription(subscription);
    }

}
