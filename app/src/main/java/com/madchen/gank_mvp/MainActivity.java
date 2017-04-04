package com.madchen.gank_mvp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.madchen.gank_mvp.fragments.GankFragment;
import com.madchen.gank_mvp.fragments.GankFragmentAdapter;
import com.madchen.gank_mvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Constants.GankCategory {

    private String mTitles[] = {all, _android, ios, restVideo, welfare, expandRes, frontEnd, recommend, app};
    private int mTitleIcons[] = {R.drawable.ic_menu_share,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_share,
            R.drawable.ic_menu_share};
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ViewPager mViewPager;
    private List<GankFragment> mGankFragmentList = new ArrayList<>();
    private PagerSlidingTabStrip mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initToolbar();
        initDrawerLayout();
        initFragment();
    }

    private void initFragment() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        GankFragment gankFragment;
        Bundle bundle;
        for (String mTitle : mTitles) {
            bundle = new Bundle();
            bundle.putString(GankFragment.KEY_FRAGMENT_CONTENT_CATEGORY, mTitle);
            gankFragment = new GankFragment();
            gankFragment.setArguments(bundle);
            mGankFragmentList.add(gankFragment);
        }
        mViewPager.setAdapter(new GankFragmentAdapter(getSupportFragmentManager(),mGankFragmentList,mTitles));
        mTabs.setViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    private void initDrawerLayout() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
