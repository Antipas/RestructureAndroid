package org.iflab.wecenterandroid.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseFragment;
import org.iflab.wecenterandroid.databinding.FragmentExploreChildBinding;
import org.iflab.wecenterandroid.modal.FocusPeople;
import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.modal.explore.Famous;
import org.iflab.wecenterandroid.view.recyclerView.EndlessRecyclerOnScrollListener;
import org.iflab.wecenterandroid.view.recyclerView.ExploreAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

public class ExploreChildFragment extends BaseFragment implements ExploreAdapter.ExploreListener{
    public static final String RECOMMEND = "RECOMMEND";
    public static final String FAMOUS_PEOPLE = "FAMOUS_PEOPLE";
    public static final String MEDIA = "MEDIA";
    private static final String EXPLORE_KIND = "EXPLORE_KIND";

    List dataList = new ArrayList();
    ExploreAdapter exploreAdapter;
    FragmentExploreChildBinding fragmentExploreChildBinding;
    String kind;
    int page = 1;
    boolean isVisible;

    public static ExploreChildFragment newInstance(String kind) {
        ExploreChildFragment fragment = new ExploreChildFragment();
        Bundle args = new Bundle();
        args.putString(EXPLORE_KIND, kind);
        fragment.setArguments(args);
        return fragment;
    }

    public ExploreChildFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            kind = getArguments().getString(EXPLORE_KIND);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        }else{
            isVisible = false;
        }
    }

    private void onVisible() {
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentExploreChildBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore_child, container, false);

        setViews(fragmentExploreChildBinding.recyclerview, fragmentExploreChildBinding.swipyrefreshlayout);

        return fragmentExploreChildBinding.getRoot();
    }

    private void setViews(RecyclerView recyclerView,SwipeRefreshLayout refreshLayout) {
        exploreAdapter = new ExploreAdapter(getActivity(), dataList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(exploreAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void loadData(){
        if(!isVisible){
            return;
        }
        if(dataList.size() > 0){
            fragmentExploreChildBinding.avloadingIndicatorView.setVisibility(View.GONE);
            return;
        }
        Subscription subscription;
        Observable observable;
        switch (kind){
            case RECOMMEND:
                observable = dataManager.loadExplore(page);
                break;
            case MEDIA:
                observable = dataManager.loadMedia(page)
                        .map(new Func1<Famous,List<Explore>>() {
                            @Override
                            public List<Explore> call(Famous famous) {
                                if(famous.getRsm().getRows() == null){
                                    return null;
                                }
                                int length = famous.getRsm().getRows().size();
                                List<Explore> list = new ArrayList();
                                for(int i = 0; i < length;i++){
                                    Explore explore = famous.getRsm().getRows().get(i);
                                    list.add(explore);
                                }
                                return list;
                            }
                        });
                break;
            case FAMOUS_PEOPLE:
                observable = dataManager.loadFamous(page)
                        .map(new Func1<Famous,List<Explore>>() {
                            @Override
                            public List<Explore> call(Famous famous) {
                                if(famous.getRsm().getRows() == null){
                                    return null;
                                }
                                int length = famous.getRsm().getRows().size();
                                List<Explore> list = new ArrayList();
                                for(int i = 0; i < length;i++){
                                    Explore explore = famous.getRsm().getRows().get(i);
                                    list.add(explore);
                                }
                                return list;
                            }
                        });
                break;
            default:
                return;
        }

        subscription = observable.subscribe(new Subscriber<List<Explore>>() {

            @Override
            public void onCompleted() {
                fragmentExploreChildBinding.avloadingIndicatorView.setVisibility(View.GONE);
                stopRefresh(fragmentExploreChildBinding.swipyrefreshlayout);
            }

            @Override
            public void onError(Throwable e) {
                showToast(e.getMessage());
            }

            @Override
            public void onNext(List<Explore> list) {

                if(list == null || list.size() == 0){
                    showToast("没有更多");
                    return;
                }
                dataList.addAll(list);
                exploreAdapter.notifyDataSetChanged();

            }
        });
        addSubscription(subscription);
    }

    private void loadMoreData() {
        page++;
        loadData();
    }

    private void refreshData() {
        page = 1;
        dataList.clear();
        loadData();
    }

    @Override
    public Subscription onAddFocusPeople(String uid) {
        return dataManager.addFocusPeople(uid)
                .subscribe(new Subscriber<FocusPeople>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FocusPeople result) {
                        if (result.getErr() != null){
                            showToast(result.toString());
                        }
                    }
                });
    }

}
