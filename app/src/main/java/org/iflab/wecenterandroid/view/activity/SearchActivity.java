package org.iflab.wecenterandroid.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivitySearchBinding;
import org.iflab.wecenterandroid.modal.search.Search;
import org.iflab.wecenterandroid.util.AnimUtils;
import org.iflab.wecenterandroid.util.ImeUtils;
import org.iflab.wecenterandroid.util.SupportVersion;
import org.iflab.wecenterandroid.util.ViewUtils;
import org.iflab.wecenterandroid.view.recyclerView.EndlessRecyclerOnScrollListener;
import org.iflab.wecenterandroid.view.recyclerView.SearchAdapter;
import org.iflab.wecenterandroid.viewmodal.SearchViewModal;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class SearchActivity extends BaseActivity {
    public static final String EXTRA_MENU_LEFT = "EXTRA_MENU_LEFT";
    public static final String EXTRA_MENU_CENTER_X = "EXTRA_MENU_CENTER_X";

    private int searchBackDistanceX;
    private int searchIconCenterX;
    int page;
    List<Search> dataList = new ArrayList<>();
    FrameLayout searchBackContainer;
    FrameLayout resultsContainer;
    ActivitySearchBinding activitySearchBinding;
    SearchView searchView;
    SearchViewModal searchViewModal;
    ImageButton searchBack;
    View searchBackground;
    View scrim;
    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchViewModal = new SearchViewModal(getApplicationContext());
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        activitySearchBinding.setSearch(searchViewModal);

        searchView = activitySearchBinding.searchView;
        searchBackContainer = activitySearchBinding.searchbackContainer;
        searchBack = activitySearchBinding.searchback;
        searchBackground = activitySearchBinding.searchBackground;
        scrim = activitySearchBinding.scrim;
        resultsContainer = activitySearchBinding.resultsContainer;
        setupSearchView();

        if(SupportVersion.lollipop()) {
            searchBackDistanceX = getIntent().getIntExtra(EXTRA_MENU_LEFT, 0) - (int) TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
            searchIconCenterX = getIntent().getIntExtra(EXTRA_MENU_CENTER_X, 0);

            // translate icon to match the launching screen then animate back into position
            searchBackContainer.setTranslationX(searchBackDistanceX);
            searchBackContainer.animate()
                    .translationX(0f)
                    .setDuration(650L)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this));
            // transform from search icon to back icon
            AnimatedVectorDrawable searchToBack = (AnimatedVectorDrawable) ContextCompat
                    .getDrawable(this, R.drawable.avd_search_to_back);
            searchBack.setImageDrawable(searchToBack);
            searchToBack.start();
            searchBack.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchBack.setImageDrawable(ContextCompat.getDrawable(SearchActivity.this,
                            R.drawable.ic_arrow_back_padded));
                }
            }, 600L);

            searchBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss(v);
                }
            });
        }
        // fade in the other search chrome
        searchBackground.animate()
                .alpha(1f)
                .setDuration(300L)
                .setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(this));
        searchView.animate()
                .alpha(1f)
                .setStartDelay(400L)
                .setDuration(400L)
                .setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(this))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        searchView.requestFocus();
                        ImeUtils.showIme(searchView);
                    }
                });
        if(SupportVersion.lollipop()) {
            scrim.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    scrim.getViewTreeObserver().removeOnPreDrawListener(this);
                    AnimatorSet showScrim = new AnimatorSet();
                    showScrim.playTogether(
                            ViewAnimationUtils.createCircularReveal(
                                    scrim,
                                    searchIconCenterX,
                                    searchBackground.getBottom(),
                                    0,
                                    (float) Math.hypot(searchBackDistanceX, scrim.getHeight()
                                            - searchBackground.getBottom())),
                            ObjectAnimator.ofArgb(
                                    scrim,
                                    ViewUtils.BACKGROUND_COLOR,
                                    Color.TRANSPARENT,
                                    ContextCompat.getColor(SearchActivity.this, R.color.white_scrim)));
                    showScrim.setDuration(400L);
                    showScrim.setInterpolator(AnimUtils.getLinearOutSlowInInterpolator(SearchActivity
                            .this));
                    showScrim.start();
                    return false;
                }
            });
        }

        progressBar = activitySearchBinding.progressbar;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchAdapter = new SearchAdapter(getApplicationContext(),dataList);
        recyclerView = activitySearchBinding.recyclerviewSearchResult;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                ++page;
                searchFor();
            }
        });

    }

    public static Intent createStartIntent(Context context, int menuIconLeft, int menuIconCenterX) {
        Intent starter = new Intent(context, SearchActivity.class);
        if(SupportVersion.lollipop()) {
            starter.putExtra(EXTRA_MENU_LEFT, menuIconLeft);
            starter.putExtra(EXTRA_MENU_CENTER_X, menuIconCenterX);
        }
        return starter;
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint("搜索文章、话题、用户");
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                result = query;
                searchFor();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (TextUtils.isEmpty(query)) {
//                    clearResults();
                }
                return true;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus && confirmSaveContainer.getVisibility() == View.VISIBLE) {
//                    hideSaveConfimation();
//                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        dismiss(searchBackContainer);
    }

    @Override
    protected void onPause() {
        // needed to suppress the default window animation when closing the activity
        overridePendingTransition(0, 0);
        super.onPause();
    }

    public void dismiss(View view) {
        // translate the icon to match position in the launching activity
        searchBackContainer.animate()
                .translationX(searchBackDistanceX)
                .setDuration(600L)
                .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        finishAfterTransition();
                    }
                })
                .start();
        if(SupportVersion.lollipop()) {
            // transform from back icon to search icon
            AnimatedVectorDrawable backToSearch = (AnimatedVectorDrawable) ContextCompat
                    .getDrawable(this, R.drawable.avd_back_to_search);
            searchBack.setImageDrawable(backToSearch);
            // clear the background else the touch ripple moves with the translation which looks bad
            searchBack.setBackground(null);
            backToSearch.start();
        }
        // fade out the other search chrome
        searchView.animate()
                .alpha(0f)
                .setStartDelay(0L)
                .setDuration(120L)
                .setInterpolator(AnimUtils.getFastOutLinearInInterpolator(this))
                .setListener(null)
                .start();
        searchBackground.animate()
                .alpha(0f)
                .setStartDelay(300L)
                .setDuration(160L)
                .setInterpolator(AnimUtils.getFastOutLinearInInterpolator(this))
                .setListener(null)
                .start();

        if(SupportVersion.lollipop()) {
            // if we're showing search results, circular hide them
            if (resultsContainer.getHeight() > 0) {
                Animator closeResults = ViewAnimationUtils.createCircularReveal(
                        resultsContainer,
                        searchIconCenterX,
                        0,
                        (float) Math.hypot(searchIconCenterX, resultsContainer.getHeight()),
                        0f);
                closeResults.setDuration(500L);
                closeResults.setInterpolator(AnimUtils.getFastOutSlowInInterpolator(SearchActivity
                        .this));
                closeResults.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        resultsContainer.setVisibility(View.INVISIBLE);
                    }
                });
                closeResults.start();
            }
        }
        // fade out the scrim
        scrim.animate()
                .alpha(0f)
                .setDuration(400L)
                .setInterpolator(AnimUtils.getFastOutLinearInInterpolator(this))
                .setListener(null)
                .start();
    }

    private void searchFor(){
        dataList.clear();
        progressBar.setVisibility(View.VISIBLE);
        resultsContainer.setVisibility(View.VISIBLE);
        ImeUtils.hideIme(searchView);
        searchView.clearFocus();
        scrim.setVisibility(View.GONE);

        Subscription subscription =  searchViewModal.doQuery(result,page)
                .subscribe(new Observer<List<Search>>() {
                    @Override
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Search> searches) {
                        if(searches == null){
                            return;
                        }
                        if(searches.size() == 0){
                            showToast("没有更多");
                        }else{
                            dataList.addAll(searches);
                            searchAdapter.notifyDataSetChanged();
                        }

                    }
                });
        addSubscription(subscription);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        resultsContainer.setVisibility(View.GONE);
    }
}
