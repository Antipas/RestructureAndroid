package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityArticleBinding;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.view.ObservableWebView;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

public class ArticleActivity extends BaseActivity {
    private static final String ARTICLE_ID = "article_id";

    ActivityArticleBinding activityArticleBinding;
    Dialog likeDialog;
    int id;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityArticleBinding = DataBindingUtil.setContentView(this,R.layout.activity_article);

        setUpToolBar(activityArticleBinding.toolbar);

        id = getIntent().getIntExtra(ARTICLE_ID,-1);
        activityArticleBinding.webviewContent.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int x, int y, int oldx, int oldy) {
                if (oldy < y) {
                    dismissBottomBar();
                } else if (oldy > y) {
                    showBottomBar();
                }
            }
        });

        loadData();
        initBottomBar();
    }

    public static void startArticle(int id, Context context) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(ARTICLE_ID, id);
        context.startActivity(intent);
    }

    private void showBottomBar() {
        if(activityArticleBinding.bottomBarContainer.getTranslationY() != 0){
            activityArticleBinding.bottomBarContainer.animate()
                    .translationY(0)
                    .setDuration(400L)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            activityArticleBinding.bottomBarContainer.setTranslationY(0);
                        }
                    })
                    .start();

        }
    }

    private void dismissBottomBar() {
        final float height = (float) activityArticleBinding.bottomBarContainer.getHeight();
        if(activityArticleBinding.bottomBarContainer.getTranslationY() == 0){
            activityArticleBinding.bottomBarContainer.animate()
                    .translationY(height)
                    .setDuration(400L)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            activityArticleBinding.bottomBarContainer.setTranslationY(height);
                        }
                    })
                    .start();
        }
    }

    private void initBottomBar(){
        RxView.clicks(activityArticleBinding.imagebtnShare)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });

        RxView.clicks(activityArticleBinding.imagebtnFavorite)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Subscription subscription = dataManager.favorite(id, "article")
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
                                        showToast("收藏成功!");
                                    }
                                });
                        addSubscription(subscription);
                    }
                });

    }

    @Override
    protected void loadData() {

    }


}
