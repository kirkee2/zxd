package com.hellmoney.thca.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.hellmoney.thca.CustomViewPager;
import com.hellmoney.thca.R;
import com.hellmoney.thca.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.container)
    protected CustomViewPager mViewPager;
    @BindView(R.id.navigation)
    protected BottomNavigationView mBottomNavigationView;
    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    private ViewPageAdapter mViewPageAdapter;
    private List<Fragment> mFragments;
    private MenuItem mPrevBottomNavigationItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFragments = new ArrayList<>();
        mFragments.add(MainFragment.newInstance(null, null));
        mFragments.add(AgentFragment.newInstance(null, null));
        mFragments.add(NoticeFragment.newInstance(null, null));
        mFragments.add(ItemFragment.newInstance(null, null));
        mViewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), mFragments);

        mViewPager.setAdapter(mViewPageAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(4);

        mPrevBottomNavigationItem = mBottomNavigationView.getMenu().getItem(0);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main:
                        mViewPager.setCurrentItem(0);
                        mViewPager.invalidate();
                        Log.d(TAG, "Page 0 selected");
                        return true;
                    case R.id.navigation_agent:
                        mViewPager.setCurrentItem(1);
                        mViewPager.invalidate();
                        Log.d(TAG, "Page 1 selected");
                        return true;
                    case R.id.navigation_notice:
                        mViewPager.setCurrentItem(2);
                        mViewPager.invalidate();
                        Log.d(TAG, "Page 2 selected");
                        return true;
                    case R.id.navigation_item:
                        mViewPager.setCurrentItem(3);
                        mViewPager.invalidate();
                        Log.d(TAG, "Page 3 selected");
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int position) {
        if (mPrevBottomNavigationItem != null) {
            mPrevBottomNavigationItem.setChecked(false);
        }
        mPrevBottomNavigationItem = mBottomNavigationView.getMenu().getItem(position);
        mPrevBottomNavigationItem.setChecked(true);
        mViewPageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}