package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityArticleBinding;
import org.iflab.wecenterandroid.modal.Article;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.modal.article.QRCodeArticle;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.view.ObservableWebView;
import org.iflab.wecenterandroid.viewmodal.ArticleViewModel;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class ArticleActivity extends BaseActivity {
    public static final String ARTICLE_ID = "ARTICLE_ID";
    public static final String INNER_ARTICLE = "INNER_ARTICLE";
    public static final String QRCODE_ARTICLE = "QRCODE_ARTICLE";

    ActivityArticleBinding activityArticleBinding;
    int id;
    int page = 1;
    String type;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityArticleBinding = DataBindingUtil.setContentView(this,R.layout.activity_article);

        setUpToolBar(activityArticleBinding.toolbar);

        id = getIntent().getIntExtra(ARTICLE_ID,-1);
        type = getIntent().getStringExtra("type");
        url = getIntent().getStringExtra("url");

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
    }

    public static void startArticle(Context context,int id,String type,String url) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(ARTICLE_ID, id);
        intent.putExtra("type", type);
        intent.putExtra("url", url);
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

    private void initBottomBar(final ArticleViewModel articleViewModel){
        // listener is here
        RxView.clicks(activityArticleBinding.imagebtnComment)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        checkAddArticle();
                        // TODO: goto commentactivity
                    }
                });

        RxView.clicks(activityArticleBinding.imagebtnShare)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        checkAddArticle();
                        // TODO: share
                    }
                });

        activityArticleBinding.imagebtnFavorite.setChecked(articleViewModel.getIsFavorite());
        RxCompoundButton.checkedChanges(activityArticleBinding.imagebtnFavorite)
                .debounce(700, TimeUnit.MICROSECONDS)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean status) {
                        if(articleViewModel.getIsFavorite() ^ status) {
                            showLoading();
                            // 防止重复添加
                            if (isQRCode() && !articleViewModel.getHasAdded()) {
                                Subscription subscription = dataManager.addQRCodeArticle(url)
                                        .subscribe(new Subscriber() {
                                            @Override
                                            public void onCompleted() {
                                                hideLoading();
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                showToast("操作失败");
                                            }

                                            @Override
                                            public void onNext(Object o) {
                                                showToast("已加入");
                                            }
                                        });
                                addSubscription(subscription);
                            } else {
                                addSubscription(getFavorite());
                            }
                        }
                    }
                });

        activityArticleBinding.imagebtnFavorite.setChecked(articleViewModel.getIsLike());
        RxCompoundButton.checkedChanges(activityArticleBinding.imagebtnLike)
                .debounce(700, TimeUnit.MICROSECONDS)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(final Boolean status) {
                        if(articleViewModel.getIsLike() ^ status) {
                            showLoading();
                            // 防止重复添加
                            if (isQRCode() && !articleViewModel.getHasAdded()) {
                                Subscription subscription = dataManager.addQRCodeArticle(url)
                                        .flatMap(new Func1<QRCodeArticle, Observable<Object>>() {
                                            @Override
                                            public Observable<Object> call(QRCodeArticle result) {
                                                if(TextUtils.isEmpty(result.getRsm().getArticle_id())){
                                                    return Observable.create(new Observable.OnSubscribe<Object>(){

                                                        @Override
                                                        public void call(Subscriber<? super Object> subscriber) {
                                                            subscriber.onError(new Throwable());
                                                        }
                                                    });
                                                }else {
                                                    articleViewModel.setHasAdded(true);
                                                    return dataManager.voteOrFuckArticle(id, "article", 1);
                                                }
                                            }
                                        }).subscribe(new Subscriber() {
                                            @Override
                                            public void onCompleted() {
                                                hideLoading();
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                showToast("操作失败");
                                            }

                                            @Override
                                            public void onNext(Object o) {
                                                articleViewModel.setIsLike(status);
                                                showToast("已点赞");
                                            }
                                        });
                                addSubscription(subscription);
                            } else {
                                addSubscription(getVoteSubscription());
                            }
                        }
                    }
                });

    }

    private Subscription getVoteSubscription(){
        return dataManager.voteOrFuckArticle(id, "article", 1)
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    private Subscription getFavorite(){
        return dataManager.favorite(id, "article")
                .subscribe(new Subscriber<SaveComment>() {
                    @Override
                    public void onCompleted() {
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("操作失败");
                    }

                    @Override
                    public void onNext(SaveComment result) {
                        showToast("已加入");
                    }
                });
    }

    @Override
    protected void loadData() {
        // load webview
        activityArticleBinding.webviewContent.loadUrl(url);
        activityArticleBinding.webviewContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                activityArticleBinding.avloadingIndicatorView.setVisibility(View.GONE);

            }
        });

        //  load article infomation
        Subscription subscription = dataManager.loadArticle(id,page)
                .subscribe(new Subscriber<Article>() {
                    @Override
                    public void onCompleted() {
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Article article) {
                        ArticleViewModel articleViewModel = new ArticleViewModel(getApplicationContext(),article);
                        activityArticleBinding.setArticle(articleViewModel);
                        initBottomBar(articleViewModel);

                        if(isQRCode()){
                            showAddZaiduDialog();
                        }
                    }
                });
        addSubscription(subscription);
    }

    private void showAddZaiduDialog() {
        Dialog dialog = new AlertDialog.Builder(ArticleActivity.this)
                .setMessage("是否加入我的在读列表？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addArticle(true);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
                dialog.show();
    }

    private boolean isQRCode(){
        return type.equals(QRCODE_ARTICLE);
    }

    public void checkAddArticle() {
        if(isQRCode()){
            addArticle(false);
        }
    }

    private void showLoading(){
        activityArticleBinding.avloadingIndicatorView.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        activityArticleBinding.avloadingIndicatorView.setVisibility(View.GONE);
    }

    private void addArticle(final boolean isShowToast){
        Subscription subscription = dataManager.addQRCodeArticle(url)
                .subscribe(new Subscriber<QRCodeArticle>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast("添加失败");
                    }

                    @Override
                    public void onNext(QRCodeArticle qrCodeArticle) {
                        if(isShowToast){
                            showToast("添加成功");
                        }
                    }
                });
        addSubscription(subscription);
    }

}
