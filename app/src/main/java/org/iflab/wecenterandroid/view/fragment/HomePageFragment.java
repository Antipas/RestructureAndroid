package org.iflab.wecenterandroid.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseFragment;
import org.iflab.wecenterandroid.databinding.FragmentHomeBinding;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.util.ViewUtils;
import org.iflab.wecenterandroid.view.activity.PublishActivity;
import org.iflab.wecenterandroid.view.recyclerView.EndlessRecyclerOnScrollListener;
import org.iflab.wecenterandroid.view.recyclerView.HomeAdapter;
import org.iflab.wecenterandroid.viewmodal.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Lyn on 15/11/20.
 */
public class HomePageFragment extends BaseFragment {

    HomeAdapter homeAdapter;
    List<Home> dataList = new ArrayList();
    FragmentHomeBinding fragmentHomeBinding;
    RecyclerView recyclerView;
    View resultsScrim;
    int page = 1;

    public static HomePageFragment newInstances() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        recyclerView = fragmentHomeBinding.recyclerview;
        resultsScrim  = fragmentHomeBinding.resultsScrim;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        homeAdapter = new HomeAdapter(getActivity(),dataList);
        recyclerView.setAdapter(homeAdapter);

        fragmentHomeBinding.swipyrefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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

        resultsScrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        fragmentHomeBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View fab) {
                final FrameLayout container = fragmentHomeBinding.fabContainer;
                fab.setVisibility(View.GONE);
                resultsScrim.setVisibility(View.VISIBLE);
                container.setVisibility(View.VISIBLE);
                container.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {

                        container.getViewTreeObserver().removeOnPreDrawListener(this);
                        Animator reveal = ViewAnimationUtils.createCircularReveal(container,
                                container.getWidth() / 2,
                                container.getHeight() / 2,
                                fab.getWidth() / 2,
                                container.getWidth() / 2);
                        reveal.setDuration(250L);
                        reveal.setInterpolator(AnimUtils.getFastOutSlowInInterpolator(getActivity()));
                        reveal.start();

                        int centerX = (fab.getLeft() + fab.getRight()) / 2;
                        int centerY = (fab.getTop() + fab.getBottom()) / 2;
                        Animator revealScrim = ViewAnimationUtils.createCircularReveal(
                                resultsScrim,
                                centerX,
                                centerY,
                                0,
                                (float) Math.hypot(centerX, centerY));
                        revealScrim.setDuration(400L);
                        revealScrim.setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(getActivity()));
                        revealScrim.start();
                        ObjectAnimator fadeInScrim = ObjectAnimator.ofArgb(resultsScrim,
                                ViewUtils.BACKGROUND_COLOR,
                                Color.TRANSPARENT,
                                ContextCompat.getColor(getActivity(), R.color.scrim));
                        fadeInScrim.setDuration(800L);
                        fadeInScrim.setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(getActivity()));
                        fadeInScrim.start();
                        return false;
                    }
                });
            }
        });

        fragmentHomeBinding.publishQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] loc = new int[2];
                v.getLocationOnScreen(loc);
                PublishActivity.startPublish(PublishActivity.QUESTION,getActivity(),loc);
                dismiss();
            }
        });

        fragmentHomeBinding.publishArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] loc = new int[2];
                v.getLocationOnScreen(loc);
                PublishActivity.startPublish(PublishActivity.ARTICLE,getActivity(),loc);
                dismiss();
            }
        });

        loadData();

        return fragmentHomeBinding.getRoot();
    }

    private void dismiss() {
        final FrameLayout container = fragmentHomeBinding.fabContainer;
        if (container.getVisibility() == View.VISIBLE) {
            // contract the bubble & hide the scrim
            AnimatorSet hideConfirmation = new AnimatorSet();
            hideConfirmation.playTogether(
                    ViewAnimationUtils.createCircularReveal(container,
                            container.getWidth() / 2,
                            container.getHeight() / 2,
                            container.getWidth() / 2,
                            fragmentHomeBinding.fab.getWidth() / 2),
                    ObjectAnimator.ofArgb(resultsScrim,
                            ViewUtils.BACKGROUND_COLOR,
                            Color.TRANSPARENT));
            hideConfirmation.setDuration(150L);
            hideConfirmation.setInterpolator(AnimUtils.getFastOutSlowInInterpolator
                    (getActivity()));
            hideConfirmation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    container.setVisibility(View.GONE);
                    resultsScrim.setVisibility(View.GONE);
                    fragmentHomeBinding.fab.setVisibility(View.VISIBLE);
                }
            });
            hideConfirmation.start();
        }
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
        fragmentHomeBinding.swipyrefreshlayout.setRefreshing(true);
        Subscription subscription = dataManager.loadHome(page)
                .subscribe(new Observer<List<Home>>() {
                    @Override
                    public void onCompleted() {
                        stopRefresh(fragmentHomeBinding.swipyrefreshlayout);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Home> list) {
                        if (list.size() != 0) {
                            dataList.addAll(list);
                            homeAdapter.notifyDataSetChanged();
                        } else {
                            showToast("no more");
                        }

                    }
                });

        addSubscription(subscription);
    }

}
