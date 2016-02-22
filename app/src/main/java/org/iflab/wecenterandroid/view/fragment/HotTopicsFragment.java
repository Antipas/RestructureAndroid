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
import org.iflab.wecenterandroid.databinding.FragmentHotTopicsBinding;
import org.iflab.wecenterandroid.modal.Topic;
import org.iflab.wecenterandroid.view.recyclerView.EndlessRecyclerOnScrollListener;
import org.iflab.wecenterandroid.view.recyclerView.TopicsAdapter;
import org.iflab.wecenterandroid.viewmodal.TopicViewModel;
import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.Subscription;

/**
 *      HotTopics
 */
public class HotTopicsFragment extends BaseFragment {

    TopicViewModel topicViewModel;
    FragmentHotTopicsBinding fragmentHotTopicsBinding;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TopicsAdapter adapter;
    List<Topic.RsmEntity.RowsEntity> dataList = new ArrayList();
    private Subscription hotTopicsSubscription;
    int page = 1;

    public HotTopicsFragment() {
    }

    public static HotTopicsFragment newInstances() {
        HotTopicsFragment fragment = new HotTopicsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        topicViewModel  = new TopicViewModel(getActivity());

        fragmentHotTopicsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_hot_topics,container,false);
        recyclerView = fragmentHotTopicsBinding.recyclerview;

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TopicsAdapter(getActivity(),dataList);
        recyclerView.setAdapter(adapter);

        fragmentHotTopicsBinding.swipyrefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMore();
            }
        });

        loadData();

        return fragmentHotTopicsBinding.getRoot();
    }

    private void loadMore() {
        ++page;
        loadData();
    }

    private void refreshData() {
        dataList.clear();
        page = 1;
        loadData();
    }

    private void loadData() {
        hotTopicsSubscription = topicViewModel.loadTopic(1,"week")
                .subscribe(new Observer<Topic.RsmEntity>() {
                    @Override
                    public void onCompleted() {
                        stopRefresh(fragmentHotTopicsBinding.swipyrefreshlayout);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(Topic.RsmEntity list) {
                        if(list.getTotal_rows() != 0){
                            dataList.addAll(list.getRows());
                            adapter.notifyDataSetChanged();
                        }else {
                            showToast("noe more");
                        }
                    }
                });
        addSubscription(hotTopicsSubscription);
    }


}
