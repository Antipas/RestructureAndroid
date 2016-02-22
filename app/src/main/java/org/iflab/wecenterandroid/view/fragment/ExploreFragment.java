package org.iflab.wecenterandroid.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseFragment;
import org.iflab.wecenterandroid.databinding.FragmentExploreBinding;
import org.iflab.wecenterandroid.viewmodal.ExploreViewModal;

/**
 * Created by Lyn on 15/11/21.
 */
public class ExploreFragment extends BaseFragment  {

    public static ExploreFragment newInstances() {
        ExploreFragment fragment = new ExploreFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentExploreBinding fragmentExploreBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false);

        ViewPager viewPager = fragmentExploreBinding.viewPager;
        viewPager.setAdapter(new SectionPagerAdapter(getFragmentManager()));
        fragmentExploreBinding.tabLayout.setupWithViewPager(viewPager);

        return fragmentExploreBinding.getRoot();
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ExploreChildFragment.newInstance(ExploreChildFragment.RECOMMEND);
                case 1:
                    return ExploreChildFragment.newInstance(ExploreChildFragment.HOT);
                case 2:
                    return ExploreChildFragment.newInstance(ExploreChildFragment.NEW);
                case 3:
                    return ExploreChildFragment.newInstance(ExploreChildFragment.UNRESPONSIVE);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "推荐";
                case 1:
                    return "热门";
                case 2:
                    return "最新";
                case 3:
                    return "待答";
                default:
                    return "";
            }
        }
    }
}
