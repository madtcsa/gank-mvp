package com.madchen.gank_mvp.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chenwei on 2017/4/4.
 */

public class GankFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabTitles;
    private List<GankFragment> gankFragments;

    public GankFragmentAdapter(FragmentManager fm, List<GankFragment> gankFragments, String[] tabTitles) {
        super(fm);
        this.gankFragments = gankFragments;
        this.tabTitles = tabTitles;
    }

    @Override
    public int getCount() {
        return gankFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return gankFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
