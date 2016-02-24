package org.iflab.wecenterandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityPersonInfoBinding;
import org.iflab.wecenterandroid.modal.person.PersonFollow;
import org.iflab.wecenterandroid.modal.person.PersonInfo;
import org.iflab.wecenterandroid.modal.person.PersonalAnswer;
import org.iflab.wecenterandroid.modal.person.PersonalArticle;
import org.iflab.wecenterandroid.modal.person.PersonalQuestion;
import org.iflab.wecenterandroid.view.recyclerView.EndlessRecyclerOnScrollListener;
import org.iflab.wecenterandroid.view.recyclerView.PersonInfoAdapter;
import org.iflab.wecenterandroid.viewmodal.PersonInfoViewModal;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;

public class PersonInfoActivity extends BaseActivity {

    private static final String TYPE = "TYPE";
    private static final String UID = "UID";
    private static final String USER_AVATAR = "USER_AVATAR";
    private static final String USER_NAME = "USER_NAME";
    ActivityPersonInfoBinding activityPersonInfoBinding;
    PersonInfoViewModal personInfoViewModal;
    RecyclerView recyclerView;
    List dataList = new ArrayList();
    PersonInfoAdapter personInfoAdapter;
    int type;
    int page = 1;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        personInfoViewModal = new PersonInfoViewModal(getApplicationContext());
        activityPersonInfoBinding = DataBindingUtil.setContentView(this,R.layout.activity_person_info);
        activityPersonInfoBinding.setPersonInfo(personInfoViewModal);


        setUpToolBar(activityPersonInfoBinding.toolbar);

        type = getIntent().getIntExtra(TYPE, -1);
        uid = getIntent().getStringExtra(UID);

        personInfoViewModal.setUserInfo(getIntent().getStringExtra(USER_AVATAR),getIntent().getStringExtra(USER_NAME));

        recyclerView = activityPersonInfoBinding.recyclerview;
        personInfoAdapter = new PersonInfoAdapter(getApplicationContext(),personInfoViewModal,dataList,type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(personInfoAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        });

        activityPersonInfoBinding.swipyrefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        activityPersonInfoBinding.toolbar.setTitle("");
        loadData();
    }

    public static void startPersonInfo(String id,int type ,String avatar,String name,Context startingActivity) {
        Intent intent = new Intent(startingActivity, PersonInfoActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(UID, id);
        intent.putExtra(USER_AVATAR, avatar);
        intent.putExtra(USER_NAME, name);
        startingActivity.startActivity(intent);
    }

    private void loadMoreData() {
        ++page;
        loadData();
    }

    private void refreshData() {
        dataList.clear();
        page = 1;
        loadData();
    }

    private void loadArticleData() {
        Subscription subscription = personInfoViewModal.loadArticle(uid, page)
                .subscribe(new Subscriber<PersonalArticle>() {
                    @Override
                    public void onCompleted() {
                        stopRefresh(activityPersonInfoBinding.swipyrefreshlayout);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(PersonalArticle article) {
                        if(article.getErr() != null){
                            showToast(article.getErr().toString());
                            return;
                        }
                        if(article.getRsm().getTotal_rows() == 0){
                            showToast("no more");
                            return;
                        }
                        dataList.addAll(article.getRsm().getRows());
                        personInfoAdapter.notifyDataSetChanged();
                    }
                });
        addSubscription(subscription);
    }

    private void loadQuestionData() {
        Subscription subscription = personInfoViewModal.loadQuestion(uid, page)
                .subscribe(new Subscriber<PersonalQuestion>() {
                    @Override
                    public void onCompleted() {
                        stopRefresh(activityPersonInfoBinding.swipyrefreshlayout);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(PersonalQuestion question) {
                        if(question.getErr() != null){
                            showToast(question.getErr().toString());
                            return;
                        }
                        if(question.getRsm().getTotal_rows() == 0){
                            showToast("no more");
                            return;
                        }
                        dataList.addAll(question.getRsm().getRows());
                        personInfoAdapter.notifyDataSetChanged();
                    }
                });
        addSubscription(subscription);
    }

    private void loadAnswerData() {
        Subscription subscription = personInfoViewModal.loadAnswer(uid, page)
                .subscribe(new Subscriber<PersonalAnswer>() {
                    @Override
                    public void onCompleted() {
                        stopRefresh(activityPersonInfoBinding.swipyrefreshlayout);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(PersonalAnswer answer) {
                        if(answer.getErr() != null){
                            showToast(answer.getErr().toString());
                            return;
                        }
                        if(answer.getRsm().getTotal_rows() == 0){
                            showToast("no more");
                            return;
                        }
                        dataList.addAll(answer.getRsm().getRows());
                        personInfoAdapter.notifyDataSetChanged();
                    }
                });
        addSubscription(subscription);
    }

    private void loadFanData() {
        Subscription subscription = personInfoViewModal.loadFans(uid,page)
                .subscribe(new Subscriber<PersonFollow>() {
                    @Override
                    public void onCompleted() {
                        stopRefresh(activityPersonInfoBinding.swipyrefreshlayout);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(PersonFollow follow) {
                        if(follow.getErr() != null){
                            showToast(follow.getErr().toString());
                            return;
                        }
                        if(follow.getRsm().getTotal_rows() == 0){
                            showToast("no more");
                            return;
                        }
                        dataList.addAll(follow.getRsm().getRows());
                        personInfoAdapter.notifyDataSetChanged();
                    }
                });
        addSubscription(subscription);
    }

    private void loadFollowData() {
        Subscription subscription = personInfoViewModal.loadFollows(uid,page)
                .subscribe(new Subscriber<PersonFollow>() {
                    @Override
                    public void onCompleted() {
                        stopRefresh(activityPersonInfoBinding.swipyrefreshlayout);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(PersonFollow follow) {
                        if(follow.getRsm().getTotal_rows() == 0){
                            showToast("no more");
                            return;
                        }
                        if(follow.getErr() != null){
                            showToast(follow.getErr().toString());
                            return;
                        }

                        dataList.addAll(follow.getRsm().getRows());
                        personInfoAdapter.notifyDataSetChanged();
                    }
                });
        addSubscription(subscription);
    }

    @Override
    protected void loadData() {
        switch (type){
            case PersonInfo.FOLLOW:
                activityPersonInfoBinding.toolbar.setTitle("关注的人");
                loadFollowData();
                break;
            case PersonInfo.FANS:
                activityPersonInfoBinding.toolbar.setTitle("粉丝");
                loadFanData();
                break;
            case PersonInfo.ARTICLE:
                activityPersonInfoBinding.toolbar.setTitle("文章");
                loadArticleData();
                break;
            case PersonInfo.QUESTION:
                activityPersonInfoBinding.toolbar.setTitle("提问");
                loadQuestionData();
                break;
            case PersonInfo.ANSWER:
                activityPersonInfoBinding.toolbar.setTitle("回答");
                loadAnswerData();
                break;
            default:
                break;
        }
    }
}
