package org.iflab.wecenterandroid.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityMainBinding;
import org.iflab.wecenterandroid.databinding.NavHeaderMainBinding;
import org.iflab.wecenterandroid.modal.prefs.UserPrefs;
import org.iflab.wecenterandroid.util.SupportVersion;
import org.iflab.wecenterandroid.view.fragment.ExploreFragment;
import org.iflab.wecenterandroid.view.fragment.HomePageFragment;
import org.iflab.wecenterandroid.viewmodal.UserViewModel;

import rx.functions.Action1;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SEARCH = 0;
    NavHeaderMainBinding navHeaderMainBinding;
    ActivityMainBinding activityMainBinding;

    UserViewModel userViewModal;

    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        userViewModal = new UserViewModel(getApplicationContext());

        navHeaderMainBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.nav_header_main, null, false);
        navHeaderMainBinding.setUser(userViewModal);

        toolbar = activityMainBinding.toolbar;
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = activityMainBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        toggle.syncState();
        drawer.setDrawerListener(toggle);

        NavigationView navigationView = activityMainBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.addHeaderView(navHeaderMainBinding.getRoot());

        RxView.clicks(navHeaderMainBinding.imageAvatar)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        PersonCenterActivity.startPersonCenter(UserPrefs.getInstance(getApplicationContext()).getUserId(),
                                MainActivity.this,
                                navHeaderMainBinding.imageAvatar);
                    }
                });

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_content, HomePageFragment.newInstances()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(SupportVersion.lollipop()) {
            getMenuInflater().inflate(R.menu.main_lollipop, menu);
        }else{
            getMenuInflater().inflate(R.menu.main_kitkat, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            View searchMenuView = toolbar.findViewById(id);
            if(SupportVersion.lollipop()) {
                int[] loc = new int[2];
                searchMenuView.getLocationOnScreen(loc);
                startActivityForResult(SearchActivity.createStartIntent(this, loc[0], loc[0] +
                        (searchMenuView.getWidth() / 2)), RC_SEARCH, ActivityOptions
                        .makeSceneTransitionAnimation(this).toBundle());
            }else{
                startActivity(SearchActivity.createStartIntent(this,0,0));
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_home:
                toolbar.setTitle("主页");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_content, HomePageFragment.newInstances()).commit();
                return true;
//            case R.id.nav_gallery:
//                toolbar.setTitle("话题");
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_content, HotTopicsFragment.newInstances()).commit();
//                return true;
            case R.id.nav_explore:
                toolbar.setTitle("发现");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_content, ExploreFragment.newInstances()).commit();
                return true;
            case R.id.nav_sign_out:
                UserPrefs.getInstance(getApplicationContext()).logout();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void loadData() {

    }
}
