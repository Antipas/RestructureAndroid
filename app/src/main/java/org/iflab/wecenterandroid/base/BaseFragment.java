package org.iflab.wecenterandroid.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import org.iflab.wecenterandroid.modal.DataManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lyn on 15/11/20.
 */
public abstract class BaseFragment extends Fragment {

    public DataManager dataManager;

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataManager = DataManager.getInstance(getActivity());
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }


    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    public void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void stopRefresh(SwipeRefreshLayout refreshLayout){
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
