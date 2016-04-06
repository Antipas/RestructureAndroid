package org.iflab.wecenterandroid.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.ArcMotion;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityPopupBinding;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.util.ImeUtils;
import org.iflab.wecenterandroid.util.MorphDialogToButton;
import org.iflab.wecenterandroid.view.MorphButtonToDialog;
import org.iflab.wecenterandroid.view.MorphDialogToFab;
import org.iflab.wecenterandroid.view.MorphFabToDialog;

public class PopupActivity extends BaseActivity {

    boolean isDismissing = false;

    ActivityPopupBinding activityPopupBinding;
    public static final String EXTRA_MORPH_TYPE = "morph_type";
    public static final String MORPH_TYPE_BUTTON = "morph_type_button";
    public static final String MORPH_TYPE_FAB = "morph_type_fab";
    public static final String MORPH_TYPE_DELETE_TOPIC= "morph_type_delte_topic";
    public static final String TOPIC_CONTENT = "topic_content";


    public static Intent getStartIntent(Context context, String type) {
        Intent intent = new Intent(context, PopupActivity.class);
        intent.putExtra(EXTRA_MORPH_TYPE, type);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityPopupBinding = DataBindingUtil.setContentView(this,R.layout.activity_popup);

        String type = getIntent().getStringExtra(EXTRA_MORPH_TYPE);
        if (type.equals(MORPH_TYPE_BUTTON)) {
            setupSharedElementTransitionsButton(this, activityPopupBinding.container,true);
            activityPopupBinding.container.setVisibility(View.VISIBLE);

        } else if (type.equals(MORPH_TYPE_FAB)){
            setupSharedElementTransitionsFab(this, activityPopupBinding.container,
                    getResources().getDimensionPixelSize(R.dimen.dialog_corners));
            activityPopupBinding.container.setVisibility(View.VISIBLE);
        }else if(type.equals(MORPH_TYPE_DELETE_TOPIC)){
            setupSharedElementTransitionsButton(this, activityPopupBinding.deleTopicContainer,false);
            activityPopupBinding.deleTopicContainer.setVisibility(View.VISIBLE);
        }



    }

    public void setupSharedElementTransitionsFab(@NonNull Activity activity,
                                                 @Nullable View target,
                                                 int dialogCornerRadius) {
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMinimumHorizontalAngle(50f);
        arcMotion.setMinimumVerticalAngle(50f);
        int color = ContextCompat.getColor(activity, R.color.accent);
        Interpolator easeInOut =
                AnimationUtils.loadInterpolator(activity, android.R.interpolator.fast_out_slow_in);
        MorphFabToDialog sharedEnter = new MorphFabToDialog(color, dialogCornerRadius);
        sharedEnter.setPathMotion(arcMotion);
        sharedEnter.setInterpolator(easeInOut);
        MorphDialogToFab sharedReturn = new MorphDialogToFab(color);
        sharedReturn.setPathMotion(arcMotion);
        sharedReturn.setInterpolator(easeInOut);
        if (target != null) {
            sharedEnter.addTarget(target);
            sharedReturn.addTarget(target);
        }
        activity.getWindow().setSharedElementEnterTransition(sharedEnter);
        activity.getWindow().setSharedElementReturnTransition(sharedReturn);
    }

    public void setupSharedElementTransitionsButton(@NonNull Activity activity,
                                                    @Nullable View target,
                                                    boolean isShowIme) {
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMinimumHorizontalAngle(50f);
        arcMotion.setMinimumVerticalAngle(50f);
        int color = ContextCompat.getColor(activity, R.color.accent);
        Interpolator easeInOut =
                AnimationUtils.loadInterpolator(activity, android.R.interpolator.fast_out_slow_in);
        MorphButtonToDialog sharedEnter = new MorphButtonToDialog(color);
        sharedEnter.setPathMotion(arcMotion);
        sharedEnter.setInterpolator(easeInOut);
        MorphDialogToButton sharedReturn = new MorphDialogToButton(color);
        sharedReturn.setPathMotion(arcMotion);
        sharedReturn.setInterpolator(easeInOut);
        if(isShowIme) {
            sharedEnter.addListener(new AnimUtils.TransitionListenerAdapter() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    activityPopupBinding.editTxtTopic.requestFocus();
                    ImeUtils.showIme(activityPopupBinding.editTxtTopic);

                }
            });
        }
        if (target != null) {
            sharedEnter.addTarget(target);
            sharedReturn.addTarget(target);
        }
        activity.getWindow().setSharedElementEnterTransition(sharedEnter);
        activity.getWindow().setSharedElementReturnTransition(sharedReturn);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void dismiss(View view) {
        isDismissing = true;
        setResult(Activity.RESULT_CANCELED);
        finishAfterTransition();
    }

    public void delelteTopic(View view){
        isDismissing = true;
        setResult(Activity.RESULT_OK);
        finishAfterTransition();
    }

    @Override
    public void onBackPressed() {
        dismiss(null);
    }

    public void getTopicContent(View view){
        String contnet = activityPopupBinding.editTxtTopic.getText().toString();
        if(TextUtils.isEmpty(contnet)){
            dismiss(null);
        }

        isDismissing = true;
//        Intent intent = new Intent(PopupActivity.this,PublishActivity.class);
//        intent.putExtra(TOPIC_CONTENT,contnet);
//        setResult(Activity.RESULT_OK,intent);
        finishAfterTransition();
    }


    @Override
    protected void loadData() {

    }
}
