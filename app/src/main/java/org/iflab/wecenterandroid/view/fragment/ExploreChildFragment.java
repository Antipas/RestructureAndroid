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
import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.view.recyclerView.EndlessRecyclerOnScrollListener;
import org.iflab.wecenterandroid.view.recyclerView.ExploreAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class ExploreChildFragment extends BaseFragment {
    public static final String RECOMMEND = "RECOMMEND";
    public static final String NEW = "NEW";
    public static final String HOT = "HOT";
    public static final String UNRESPONSIVE = "UNRESPONSIVE";
    public static final int DAY = 30;
    private static final String EXPLORE_KIND = "EXPLORE_KIND";

    String kind;
    int page;
    List dataList = new ArrayList();
    ExploreAdapter exploreAdapter;
    FragmentExploreChildBinding fragmentExploreChildBinding;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentExploreChildBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore_child, container, false);

        setViews(fragmentExploreChildBinding.recyclerview, fragmentExploreChildBinding.swipyrefreshlayout);

        loadData();

        return fragmentExploreChildBinding.getRoot();
    }

    private void setViews(RecyclerView recyclerView,SwipeRefreshLayout refreshLayout) {
        exploreAdapter = new ExploreAdapter(getActivity(), dataList);
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
        fragmentExploreChildBinding.swipyrefreshlayout.setRefreshing(true);
        Subscription subscription;
        Observable observable;
        switch (kind){
            case RECOMMEND:
                observable = dataManager.loadExplore(page,DAY,1,"new");
                break;
            case HOT:
                observable = dataManager.loadExplore(page,DAY,0,"hot");
                break;
            case NEW:
                observable = dataManager.loadExplore(page,DAY,0,"new");
                break;
            case UNRESPONSIVE:
                observable = dataManager.loadExplore(page,DAY,0,"unresponsive");
                break;
            default:
                return;
        }

        subscription = observable.subscribe(new Subscriber<List<Explore>>() {
            @Override
            public void onCompleted() {
                stopRefresh(fragmentExploreChildBinding.swipyrefreshlayout);
            }

            @Override
            public void onError(Throwable e) {
                showToast(e.getMessage());
            }

            @Override
            public void onNext(List<Explore> list) {

                if(list == null){
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
        loadData();
    }
}
