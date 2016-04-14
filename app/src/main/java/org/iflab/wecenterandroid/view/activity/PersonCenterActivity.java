package org.iflab.wecenterandroid.view.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityPersonCenterBinding;
import org.iflab.wecenterandroid.modal.FocusPeople;
import org.iflab.wecenterandroid.modal.UserAPIModal;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.prefs.UserPrefs;
import org.iflab.wecenterandroid.util.SupportVersion;
import org.iflab.wecenterandroid.view.recyclerView.EndlessRecyclerOnScrollListener;
import org.iflab.wecenterandroid.view.recyclerView.PersonCenterAdapter;
import org.iflab.wecenterandroid.viewmodal.PersonCenterViewModal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;

public class PersonCenterActivity extends BaseActivity {

    private static final String UID = "UID";
    ActivityPersonCenterBinding activityPersonCenterBinding;
    RecyclerView recyclerView;
    PersonCenterAdapter adapter;
    List dataList = new ArrayList();
    long uid;
    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPersonCenterBinding = DataBindingUtil.setContentView(this,R.layout.activity_person_center);
        setSupportActionBar(activityPersonCenterBinding.toolbar);

        recyclerView = activityPersonCenterBinding.recyclerview;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMore();
            }
        });

        adapter = new PersonCenterAdapter(PersonCenterActivity.this,dataList);
        recyclerView.setAdapter(adapter);

        uid = getIntent().getLongExtra(UID, UserPrefs.getInstance(getApplicationContext()).getUserId());

        if(SupportVersion.lollipop()) {
            Fade fade = new Fade();
            fade.setDuration(500);
            getWindow().setReturnTransition(fade);
        }
        loadData();
    }

    private void loadMore() {
        ++page;

        Subscription subscription = dataManager.loadMyZaidu(page,String.valueOf(uid))
                .subscribe(new Subscriber<List<Home>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        activityPersonCenterBinding.avloadingIndicatorView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCompleted() {
                        activityPersonCenterBinding.avloadingIndicatorView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Home> list) {
                        dataList.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
        addSubscription(subscription);
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
        Subscription subscription = dataManager.loadUserInfo(String.valueOf(uid))
                .zipWith(dataManager.loadMyZaidu(page, String.valueOf(uid)), new Func2<UserAPIModal,List<Home>,Boolean>() {
                    @Override
                    public Boolean call(UserAPIModal userAPIModal, List<Home> list) {
                        PersonCenterViewModal personCenterViewModal = new PersonCenterViewModal(getApplicationContext(),userAPIModal.getRsm());
                        activityPersonCenterBinding.setPerson(personCenterViewModal);
                        setBtnHasFocus(personCenterViewModal);

                        dataList.addAll(list);
                        adapter.setUserAPIModal(userAPIModal.getRsm());
                        adapter.notifyDataSetChanged();
                        return list.size() != 0;
                    }
                })
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        activityPersonCenterBinding.avloadingIndicatorView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean hasZaidu) {
                        if(!hasZaidu){
                            showToast("快去在读吧");
                        }
                    }
                });
        addSubscription(subscription);

    }

    private void setBtnHasFocus(final PersonCenterViewModal personCenterViewModal) {
        activityPersonCenterBinding.btnAddfocus.setChecked(personCenterViewModal.getHasFocus());
        RxCompoundButton.checkedChanges(activityPersonCenterBinding.btnAddfocus)
                .debounce(700, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(final Boolean status) {
                        if(personCenterViewModal.getHasFocus() ^ status){
                            Subscription subscription = dataManager.addFocusPeople(String.valueOf(uid))
                                    .subscribe(new Subscriber<FocusPeople>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            showToast(e.getMessage());
                                        }

                                        @Override
                                        public void onNext(FocusPeople result) {
                                            if(result.getErr() != null){
                                                showToast("请求失败");
                                                return;
                                            }
                                            if(result.getRsm().getType().equals("add")){
                                                showToast("关注成功");
                                            }else if(result.getRsm().getType().equals("remove")){
                                                showToast("取消关注");
                                            }
                                            personCenterViewModal.setHasFocus(status);
                                        }
                                    });
                            addSubscription(subscription);
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }

}
