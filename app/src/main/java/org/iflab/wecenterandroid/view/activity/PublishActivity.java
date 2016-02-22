package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.opengl.ETC1;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityPublishBinding;
import org.iflab.wecenterandroid.modal.Attach;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.util.MD5Util;
import org.iflab.wecenterandroid.util.UploadAttachUtil;
import org.iflab.wecenterandroid.util.ViewUtils;
import org.iflab.wecenterandroid.viewmodal.PublishViewModal;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cn.finalteam.toolsfinal.BitmapUtils;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;

public class PublishActivity extends BaseActivity {

    private static final String PUBLISH_KIND = "PUBLISH_KIND";
    private static final String QUESTION_ID = "QUESTION_ID";
    private static final String LOCATION = "LOCATION";
    public static final String QUESTION = "question";
    public static final String ARTICLE = "article";
    public static final String ANSWER = "ANSWER";
    PublishViewModal publishViewModal;
    ActivityPublishBinding activityPublishBinding;
    String publishKind;
    String resultText;
    String questionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityPublishBinding = DataBindingUtil.setContentView(this,R.layout.activity_publish);
        publishViewModal = new PublishViewModal(getApplicationContext());
        activityPublishBinding.setAttach(publishViewModal);


        setSupportActionBar(activityPublishBinding.toolbar);

        publishKind = getIntent().getStringExtra(PUBLISH_KIND);

        if(publishKind.equals(QUESTION)){
            activityPublishBinding.toolbar.setTitle("发布问题");
        }else if(publishKind.equals(ARTICLE)){
            activityPublishBinding.toolbar.setTitle("发布文章");
        }else if(publishKind.equals(ANSWER)){
            activityPublishBinding.toolbar.setTitle("添加回答");
            questionId = getIntent().getStringExtra(QUESTION_ID);
        }else {
            showToast("错误发布类型");
            finish();
        }

        final int[]loca = getIntent().getIntArrayExtra(LOCATION);
        final View scrim = activityPublishBinding.scrim;
        scrim.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                scrim.getViewTreeObserver().removeOnPreDrawListener(this);
                Animator showScrim = ViewAnimationUtils.createCircularReveal(
                        scrim,
                        loca[0],
                        loca[1],
                        0f,
                        (float) Math.hypot(scrim.getWidth(),scrim.getHeight()));
                double fsd = Math.hypot(scrim.getWidth(),scrim.getHeight());
                showScrim.setDuration(400L);
                showScrim.setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(PublishActivity
                        .this));
                showScrim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        scrim.setVisibility(View.GONE);
                    }
                    
                });
                showScrim.start();
                return false;
            }
        });


        RxView.clicks(activityPublishBinding.fab)
                .throttleFirst(200, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        ArrayList imageList = activityPublishBinding.idPoorEdit.exportText();
                        int length = imageList.size();
                        int lastIndex = length - 1;

                        resultText = imageList.get(lastIndex).toString();
                        String key = MD5Util.MD5(System.currentTimeMillis() + "");

                        RequestBody idBody =
                                RequestBody.create(MediaType.parse("multipart/form-data"), publishKind);

                        RequestBody keyBody =
                                RequestBody.create(MediaType.parse("multipart/form-data"), key);

                        for (int i = 0; i < lastIndex; i++) {
                            String imagePath = imageList.get(i).toString();
                            boolean isLast = lastIndex - i == 1;

                            RequestBody fileBody =
                                    RequestBody.create(MediaType.parse("multipart/form-data"),
                                            UploadAttachUtil.compressPicture(imagePath,getApplicationContext()));
                            uploadAttach(idBody,fileBody, keyBody, imagePath, isLast);
                        }

                    }
                });

    }



    public static void startPublish(String kind, Activity startingActivity,int[]loc) {
        Intent intent = new Intent(startingActivity, PublishActivity.class);
        intent.putExtra(PUBLISH_KIND, kind);
        intent.putExtra(LOCATION, loc);
        startingActivity.startActivity(intent);
    }

    public static void startPublishAnswer(String kind, Activity startingActivity,String questionId,int[]loc) {
        Intent intent = new Intent(startingActivity, PublishActivity.class);
        intent.putExtra(PUBLISH_KIND, kind);
        intent.putExtra(QUESTION_ID, questionId);
        intent.putExtra(LOCATION, loc);
        startingActivity.startActivity(intent);
    }

    private void uploadAttach(RequestBody idBody,RequestBody fileBody,RequestBody keyBody, final String strReplaced,final boolean isLast) {
        Subscription subscription = publishViewModal.uploadAttach(idBody,keyBody, fileBody)
                .subscribe(new Subscriber<Attach>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Attach attach) {
                        if (attach.getErr() != null)
                            onError(OnErrorThrowable.from(new Throwable(attach.getErr().toString())));

                        replaceAttachId(strReplaced, attach.getRsm().getAttach_id(), isLast, attach.getRsm().getAttach_access_key());
                    }
                });
        addSubscription(subscription);
    }

    private void replaceAttachId(String strReplaced,String id,boolean isLast,String key){
        synchronized(this){
            resultText = resultText.replace(strReplaced, id);
            if(isLast){
                if(publishKind.equals(QUESTION)){
                    uploadQuestionText(resultText,key);
                }else if(publishKind.equals(ARTICLE)){
                    uploadArticleText(resultText, key);
                }else if(publishKind.equals(ANSWER)){
                    uploadAnswerText(resultText,key);
                }
            }
        }
    }

    private void uploadQuestionText(String resultText, String key) {
        Subscription subscription = publishViewModal.uploadQuestionText("啦啦啦", key, resultText, "")
                .subscribe(new Subscriber<SaveComment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(SaveComment result) {
                        if(result.getErr()!= null) {
                            onError(OnErrorThrowable.from(new Throwable(result.getErr().toString())));
                            return;
                        }
                        showToast("发布成功!");
                        finish();
                    }
                });
        addSubscription(subscription);
    }

    private void uploadArticleText(String resultText,String key) {
        Subscription subscription = publishViewModal.uploadArticleText("啦啦啦", key, resultText, "")
                .subscribe(new Subscriber<SaveComment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(SaveComment result) {
                        if(result.getErr()!= null) {
                            onError(OnErrorThrowable.from(new Throwable(result.getErr().toString())));
                            return;
                        }
                        showToast("发布成功!");
                        finish();
                    }
                });
        addSubscription(subscription);
    }

    private void uploadAnswerText(String resultText,String key) {
        Subscription subscription = publishViewModal.uploadAnswerText(questionId, key, resultText)
                .subscribe(new Subscriber<SaveComment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(SaveComment result) {
                        if(result.getErr()!= null) {
                            onError(OnErrorThrowable.from(new Throwable(result.getErr().toString())));
                            return;
                        }
                        showToast("发布成功!");
                        finish();
                    }
                });
        addSubscription(subscription);
    }

    @Override
    protected void loadData() {

    }
}
