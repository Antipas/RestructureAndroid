package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.jakewharton.rxbinding.view.RxView;

import org.iflab.wecenterandroid.AnswerInfo;
import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityAnswerBinding;
import org.iflab.wecenterandroid.databinding.QuestionUserinfoItemBinding;
import org.iflab.wecenterandroid.modal.QuestionFocus;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.viewmodal.AnswerViewModel;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;

public class AnswerActivity extends BaseActivity {

    private static final String ANSWER_ID = "ANSWER_ID";
    AnswerViewModel answerViewModel;
    ActivityAnswerBinding activityAnswerBinding;
    Dialog likeDialog;
    QuestionUserinfoItemBinding questionUserinfoItemBinding;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionUserinfoItemBinding = DataBindingUtil.setContentView(this,R.layout.question_userinfo_item);
        activityAnswerBinding = DataBindingUtil.setContentView(this,R.layout.activity_answer);
        answerViewModel = new AnswerViewModel(getApplicationContext());
        activityAnswerBinding.setAnswer(answerViewModel);

        activityAnswerBinding.toolbar.setTitle("回答");

        setUpToolBar(activityAnswerBinding.toolbar);
        id = getIntent().getIntExtra(ANSWER_ID,0);
        loadData();
        setListener();

    }

    public static void startAnswer(int id, Context context) {
        Intent intent = new Intent(context, AnswerActivity.class);
        intent.putExtra(ANSWER_ID, id);
        context.startActivity(intent);
    }

    private void setListener() {
        RxView.clicks(activityAnswerBinding.imageLike)
                .debounce(200, TimeUnit.MILLISECONDS)
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
                        //don't need to response
                        answerViewModel.voteOrFuckAnswer(id, rating)
                                .subscribe(new Subscriber<QuestionFocus>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(QuestionFocus questionFocus) {
//                                        Log.v("saveComment",questionFocus.getErrno()+"");

                                    }
                                });
                    }
                });

        RxView.clicks(activityAnswerBinding.imagebtnComment)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        AnswerCommentsActivity.startAnswerComments(id,AnswerActivity.this);
                    }
                });

        activityAnswerBinding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollY < scrollY) {
                    dismissBottomBar();
                } else if (oldScrollY > scrollY) {
                    showBottomBar();
                }
            }
        });
    }

    private void showBottomBar() {
        if(activityAnswerBinding.bottomBarContainer.getTranslationY() != 0){
            activityAnswerBinding.bottomBarContainer.animate()
                    .translationY(0)
                    .setDuration(400L)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            activityAnswerBinding.bottomBarContainer.setTranslationY(0);
                        }
                    })
                    .start();

        }
    }

    private void dismissBottomBar() {
        final float height = (float) activityAnswerBinding.bottomBarContainer.getHeight();
        if(activityAnswerBinding.bottomBarContainer.getTranslationY() == 0){
            activityAnswerBinding.bottomBarContainer.animate()
                    .translationY(height)
                    .setDuration(400L)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            activityAnswerBinding.bottomBarContainer.setTranslationY(height);
                        }
                    })
                    .start();
        }
    }

    @Override
    protected void loadData() {
        Subscription subscription = answerViewModel.loadAnswer(id)
                .subscribe(new Observer<AnswerInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(final AnswerInfo answerInfo) {
                        activityAnswerBinding.webviewContent.setText(answerInfo.getContent());
                        RxView.clicks(activityAnswerBinding.layoutUserinfo.ivAvatar)
                                .subscribe(new Action1<Void>() {
                                    @Override
                                    public void call(Void aVoid) {
                                        PersonCenterActivity.startPersonCenter(answerInfo.getUid(),
                                                AnswerActivity.this,
                                                activityAnswerBinding.layoutUserinfo.ivAvatar);
                                    }
                                });
                    }
                });
        addSubscription(subscription);
    }


    private Observable getLikeOrFuckResult(){
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                likeDialog = new AlertDialog.Builder(AnswerActivity.this)
                        .setItems(new String[]{"赞", "踩"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int rating = 0;
                                int value = 0;
                                switch (which) {
                                    case 0:
                                        rating = 1;
                                        activityAnswerBinding.imageLike.setImageResource(R.drawable.evaluation_like_highlighted);
                                        value = answerViewModel.likeNum.get() + 1;
                                        break;
                                    case 1:
                                        rating = -1;
                                        activityAnswerBinding.imageLike.setImageResource(R.drawable.evaluation_dislike_highlighted);
                                        value = answerViewModel.likeNum.get() - 1;

                                        break;
                                }
                                answerViewModel.likeNum.set(value);
                                subscriber.onNext(rating);
                                dialog.dismiss();
                            }
                        }).create();
            }
        });
    }
}
