package com.hellmoney.thca.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.container)
    CustomViewPager mViewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    ViewPageAdapter mViewPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPageAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(1);
        prevBottomNavigation = navigation.getMenu().getItem(0);
        mViewPager.setOffscreenPageLimit(4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(this);
    }

    private MenuItem prevBottomNavigation;

    @Override
    public void onPageSelected(int position) {
        if (prevBottomNavigation != null){
            prevBottomNavigation.setChecked(false);
        }
        prevBottomNavigation = navigation.getMenu().getItem(position);
        prevBottomNavigation.setChecked(true);
        mViewPageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}