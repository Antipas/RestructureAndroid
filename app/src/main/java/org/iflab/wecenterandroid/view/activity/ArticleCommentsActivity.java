package org.iflab.wecenterandroid.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.jakewharton.rxbinding.view.RxView;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityCommentsBinding;
import org.iflab.wecenterandroid.databinding.PopupCommentsBottomBarBinding;
import org.iflab.wecenterandroid.modal.Comments;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.view.recyclerView.CommentsAdapter;
import org.iflab.wecenterandroid.viewmodal.CommentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ArticleCommentsActivity extends BaseActivity {

    private static final String ARTICLE_ID = "article_id";

    ActivityCommentsBinding activityCommentsBinding;
    PopupCommentsBottomBarBinding popupCommentsBottomBarBinding;
    CommentViewModel commentViewModel;
    RecyclerView recyclerView;
    CommentsAdapter commentsAdapter;
    PopupWindow popupWindow;
    InputMethodManager inputManager;
    int id;
    int page = 1;
    List dataList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityCommentsBinding = DataBindingUtil.setContentView(this,R.layout.activity_comments);
        commentViewModel = new CommentViewModel(getApplicationContext());
        activityCommentsBinding.setComment(commentViewModel);

        activityCommentsBinding.toolbar.setTitle("评论");
        setSupportActionBar(activityCommentsBinding.toolbar);

        recyclerView = activityCommentsBinding.recyclerviewComments;
        commentsAdapter = new CommentsAdapter(getApplicationContext(),dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(commentsAdapter);
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollY < scrollY) {
                    popupWindow.dismiss();
                } else if (oldScrollY > scrollY) {
                    popupWindow.showAtLocation(activityCommentsBinding.getRoot(), Gravity.BOTTOM, 0, 0);
                }
            }
        });

        inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        id = getIntent().getIntExtra(ARTICLE_ID,0);

        loadData();
        initBottomBar();
    }

    public static void startArticleComments(int id, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, ArticleCommentsActivity.class);
        intent.putExtra(ARTICLE_ID, id);
        startingActivity.startActivity(intent);
    }

    //TODO maybe webview and bottomView in framelayout is a better way rather than popupwindow
    private void initBottomBar(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupCommentsBottomBarBinding = DataBindingUtil.inflate(inflater,R.layout.popup_comments_bottom_bar,null,false);
        popupCommentsBottomBarBinding.setCommentViewModel(commentViewModel);

        View contentview = popupCommentsBottomBarBinding.getRoot();
        popupWindow = new PopupWindow(contentview, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);

        activityCommentsBinding.getRoot().post(new Runnable() {
            public void run() {
                popupWindow.showAtLocation(activityCommentsBinding.getRoot(), Gravity.BOTTOM, 0, 0);
            }
        });

        Subscription subscription = RxView.clicks(popupCommentsBottomBarBinding.btnSend)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        sendOnClick();
                    }
                });
        addSubscription(subscription);
    }

    public void sendOnClick(){
        String comment = popupCommentsBottomBarBinding.editTxtComment.getText().toString();
        if(comment.length() == 0){
            return;
        }
        Subscription subscription = commentViewModel.sendComment(id,comment)
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
                        if(saveComment.getRsm() != null){
                            popupCommentsBottomBarBinding.editTxtComment.setText("");
                            refresh();
                            recyclerView.smoothScrollBy(0, recyclerView.getHeight());
                            inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }
                });
        addSubscription(subscription);
    }

    private void refresh(){
        page = 1;
        dataList.clear();
        loadData();
    }

    @Override
    protected void loadData() {
        Subscription subscription = commentViewModel.loadComment(id, page)
                .subscribe(new Observer<Comments.RsmEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Comments.RsmEntity rsmEntity) {
                        if(rsmEntity.getTotal_rows() != 0){
                            dataList.addAll(rsmEntity.getRows());
                            commentsAdapter.notifyDataSetChanged();
                        }

                    }
                });
        addSubscription(subscription);
    }

}
