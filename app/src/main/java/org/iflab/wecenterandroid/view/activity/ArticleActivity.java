package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityArticleBinding;
import org.iflab.wecenterandroid.databinding.QuestionUserinfoItemBinding;
import org.iflab.wecenterandroid.modal.Article;
import org.iflab.wecenterandroid.modal.ArticleWrapper;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.viewmodal.ArticleViewModel;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;

public class ArticleActivity extends BaseActivity {
    private static final String ARTICLE_ID = "article_id";

    ArticleViewModel articleViewModel;
    ActivityArticleBinding activityArticleBinding;
    QuestionUserinfoItemBinding questionUserinfoItemBinding;
    Subscription subscription;
    Toolbar toolbar;
    TextView articleContent;
    Dialog likeDialog;
    int id;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // include layout first
        questionUserinfoItemBinding = DataBindingUtil.setContentView(this,R.layout.question_userinfo_item);
        activityArticleBinding = DataBindingUtil.setContentView(this,R.layout.activity_article);

        articleViewModel = new ArticleViewModel(getApplicationContext());
        activityArticleBinding.setArticle(articleViewModel);
        toolbar = activityArticleBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        articleContent = activityArticleBinding.webviewContent;

        id = getIntent().getIntExtra(ARTICLE_ID,-1);
        activityArticleBinding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollY < scrollY) {
                    dismissBottomBar();
                } else if (oldScrollY > scrollY) {
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
                        Subscription subscription = articleViewModel.favorite(id, "article")
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

        RxView.clicks(activityArticleBinding.imagebtnComment)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        AnswerCommentsActivity.startAnswerComments(id, ArticleActivity.this);
//                        ArticleCommentsActivity.startArticleComments(id, ArticleActivity.this);
                    }
                });

        RxView.clicks(activityArticleBinding.imageLike)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        likeDialog.show();
                    }
                })
                .zipWith(getLikeOrFuckResult(), new Func2<Void, Integer, Integer>() {
                    @Override
                    public Integer call(Void o2, Integer result) {
                        return result;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer rating) {
                        articleViewModel.voteOrFuckArticle(id,"article",rating);
                    }
                });
    }
    
    private Observable getLikeOrFuckResult(){
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                likeDialog = new AlertDialog.Builder(ArticleActivity.this)
                        .setItems(new String[]{"赞", "踩"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int rating = 0;
                                int value = 0;
                                switch (which) {
                                    case 0:
                                        rating = 1;
                                        activityArticleBinding.imageLike.setImageResource(R.drawable.evaluation_like_highlighted);
                                        value = articleViewModel.likeNum.get() + 1;
                                        break;
                                    case 1:
                                        rating = -1;
                                        activityArticleBinding.imageLike.setImageResource(R.drawable.evaluation_dislike_highlighted);
                                        value = articleViewModel.likeNum.get() - 1;

                                        break;
                                }
                                articleViewModel.likeNum.set(value);
                                subscriber.onNext(rating);
                                dialog.dismiss();
                            }
                        }).create();
            }
        });
    }

    @Override
    protected void loadData() {
        subscription = articleViewModel.loadArticle(id,page)
                .subscribe(new Observer<ArticleWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(ArticleWrapper articleWrapper) {
                        if(articleWrapper != null){
                            Article article = articleWrapper.getArticle();
                            articleContent.setText(articleWrapper.getSpanned());
                            activityArticleBinding.setUser(articleWrapper.getUserInfoEntity());

                            toolbar.setTitle(article.getRsm().getArticle_info().getTitle());

//                            Picasso.with(getApplicationContext()).load(articleWrapper.getUserInfoEntity().getAvatar_file())
//                                    .transform(new RoundedTransformation())
//                                    .into(activityArticleBinding.layoutUserinfo.ivAvatar);
                            final long id = article.getRsm().getArticle_info().getUid();
                            RxView.clicks(activityArticleBinding.layoutUserinfo.ivAvatar)
                                    .subscribe(new Action1<Void>() {
                                        @Override
                                        public void call(Void aVoid) {
                                            PersonCenterActivity.startPersonCenter(id,ArticleActivity.this,activityArticleBinding.layoutUserinfo.ivAvatar);
                                        }
                                    });
                        }else {
                            Toast.makeText(getApplicationContext(),"出错啦",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        addSubscription(subscription);
    }


}
