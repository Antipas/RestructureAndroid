package org.iflab.wecenterandroid.view.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityPersonCenterBinding;
import org.iflab.wecenterandroid.modal.PersonCenterItem;
import org.iflab.wecenterandroid.modal.UserAPIModal;
import org.iflab.wecenterandroid.modal.person.PersonInfo;
import org.iflab.wecenterandroid.modal.prefs.UserPrefs;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.util.SupportVersion;
import org.iflab.wecenterandroid.view.recyclerView.PersonCenterAdapter;
import org.iflab.wecenterandroid.viewmodal.PersonCenterViewModal;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class PersonCenterActivity extends BaseActivity {

    private static final String UID = "UID";
    ActivityPersonCenterBinding activityPersonCenterBinding;
    PersonCenterViewModal personCenterViewModal;
    RecyclerView recyclerView;
    PersonCenterAdapter personCenterAdapter;
    List dataList = new ArrayList(10);
    long uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPersonCenterBinding = DataBindingUtil.setContentView(this,R.layout.activity_person_center);
        personCenterViewModal = new PersonCenterViewModal(getApplicationContext());
        activityPersonCenterBinding.setPerson(personCenterViewModal);

        activityPersonCenterBinding.collapsingToolbarLayout.setTitle("");
        setSupportActionBar(activityPersonCenterBinding.toolbar);

        recyclerView = activityPersonCenterBinding.recyclerview;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        uid = getIntent().getLongExtra(UID, UserPrefs.getInstance(getApplicationContext()).getUserId());

        if(SupportVersion.lollipop()) {
            Fade fade = new Fade();
            fade.setDuration(500);
            getWindow().setReturnTransition(fade);
        }
        loadData();
    }

    public static void startPersonCenter(long id, Activity startingActivity, View transitionView) {
        Intent intent = new Intent(startingActivity, PersonCenterActivity.class);
        intent.putExtra(UID, id);
        if(SupportVersion.lollipop()) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(startingActivity,
                            Pair.create(transitionView, startingActivity.getString(R.string.transition_person_center_avatar_view)));
            startingActivity.startActivity(intent, options.toBundle());
        }else{
            startingActivity.startActivity(intent);
        }
    }

    @Override
    protected void loadData() {
        Subscription subscription = personCenterViewModal.loadUserInfo(uid+"")
                .subscribe(new Observer<UserAPIModal>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(UserAPIModal user) {
                        Picasso.with(getApplicationContext()).load(user.getRsm().getAvatar_file()).
                                transform(new RoundedTransformation()).
                                into(activityPersonCenterBinding.personAvatar);
                        activityPersonCenterBinding.collapsingToolbarLayout.setTitle(user.getRsm().getUser_name());
                        dataList.clear();

                        dataList.add(new PersonCenterItem(R.drawable.error,"我的粉丝",user.getRsm().getFans_count(), PersonInfo.FANS));
                        dataList.add(new PersonCenterItem(R.drawable.error,"我的关注",user.getRsm().getFriend_count(),PersonInfo.FOLLOW));
                        dataList.add(new PersonCenterItem(R.drawable.error,"文章数",user.getRsm().getArticle_count(),PersonInfo.ARTICLE));
                        dataList.add(new PersonCenterItem(R.drawable.error,"问题数",user.getRsm().getQuestion_count(),PersonInfo.QUESTION));
                        dataList.add(new PersonCenterItem(R.drawable.error,"回答数",user.getRsm().getAnswer_count(),PersonInfo.ANSWER));
                        dataList.add(new PersonCenterItem(R.drawable.error, "关注的话题", user.getRsm().getTopic_focus_count(),PersonInfo.TOPIC));
                        dataList.add(new PersonCenterItem(R.drawable.error, "赞同数", user.getRsm().getAgree_count(),PersonInfo.AGREE));

                        PersonCenterItem empty = new PersonCenterItem();
                        empty.setType(PersonCenterAdapter.SETTINGS_EMPTY_ITEM);
                        dataList.add(empty);

                        dataList.add(new PersonCenterItem(R.drawable.error, "关于我们", -1,PersonInfo.SETTING));
                        dataList.add(new PersonCenterItem(R.drawable.error, "意见反馈", -1,PersonInfo.SETTING));

                        personCenterAdapter = new PersonCenterAdapter(PersonCenterActivity.this,dataList,
                                user.getRsm().getUid(),
                                user.getRsm().getAvatar_file(),
                                user.getRsm().getUser_name()
                                );
                        recyclerView.setAdapter(personCenterAdapter);
                    }
                });
        addSubscription(subscription);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}
