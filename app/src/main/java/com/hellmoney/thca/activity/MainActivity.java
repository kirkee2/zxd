package com.hellmoney.thca.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.login.KakaoLoginActivity;
import com.hellmoney.thca.activity.login.KakaoSignupActivity;
import com.hellmoney.thca.common.CommonBaseAcitivity;
import com.hellmoney.thca.fragment.ErrorFragment;
import com.hellmoney.thca.fragment.MainFragment;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.module.network.networkData.SingleResActivity;
import com.hellmoney.thca.module.network.networkData.SingleResCount;
import com.hellmoney.thca.view.AgentFragment;
import com.hellmoney.thca.view.BottomNavigationViewHelper;
import com.hellmoney.thca.view.ContactActivity;
import com.hellmoney.thca.view.ItemFragment;
import com.hellmoney.thca.view.NoticeFragment;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends CommonBaseAcitivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();

    private long backPressedTime = 0;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;
    private View mNavHeader;
    private ProgressBar progressBar;

    private Call<SingleResActivity> activityCount;
    private Call<SingleResCount> reviewCount;

    private boolean isActivityCountSuccess = false;
    private boolean isReviewCountSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavHeader = mNavigationView.inflateHeaderView(R.layout.nav_header);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.navi_info) {
                    Toast.makeText(getApplicationContext(),"아직 안 만듬.",Toast.LENGTH_SHORT).show();
                }else if(id == R.id.navi_faq){
                    Toast.makeText(getApplicationContext(),"아직 안 만듬.",Toast.LENGTH_SHORT).show();
                }else if(id == R.id.navi_contact){
                    startActivity(new Intent(MainActivity.this, ContactActivity.class));
                }

                mDrawerLayout.closeDrawer(mNavigationView);
                return true;
            }
        });

        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        addFragment(R.id.main_container, MainFragment.newInstance(), MainFragment.TAG);

    }

    @Override
    protected void onStart() {
        super.onStart();

        enableProgressBar(progressBar);

        isActivityCountSuccess = false;
        isReviewCountSuccess = false;

        activityCount = NetworkManager.service.activityCount(kakaoId);
        activityCount.enqueue(new Callback<SingleResActivity>() {
            @Override
            public void onResponse(Call<SingleResActivity> call, Response<SingleResActivity> response) {
                if (response.isSuccessful()) {
                    SingleResActivity singleResActivity = response.body();
                    String msg = singleResActivity.getMsg();

                    if ("SUCCESS".equals(msg)) {
                        ((TextView) mNavHeader.findViewById(R.id.totalCount)).setText(String.valueOf(singleResActivity.getActivitys().getTotal()));
                        ((TextView) mNavHeader.findViewById(R.id.selectedCount)).setText(String.valueOf(singleResActivity.getActivitys().getSelected()));
                        ((TextView) mNavHeader.findViewById(R.id.finishedCount)).setText(String.valueOf(singleResActivity.getActivitys().getFinished()));
                        isActivityCountSuccess = true;
                        networkDone();
                    } else {
                        Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("????", " ??? " + response.body() + " ??? " + response.message() + " ??? " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SingleResActivity> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                networkDone();
            }
        });

        reviewCount = NetworkManager.service.reviewCount(kakaoId);

        reviewCount.enqueue(new Callback<SingleResCount>() {
            @Override
            public void onResponse(Call<SingleResCount> call, Response<SingleResCount> response) {
                if (response.isSuccessful()) {
                    SingleResCount singleResCount = response.body();
                    String msg = singleResCount.getMsg();

                    if ("SUCCESS".equals(msg)) {
                        ((TextView) mNavHeader.findViewById(R.id.reviewCount)).setText(String.valueOf(singleResCount.getCount().getCount()));
                        isReviewCountSuccess = true;
                        networkDone();
                    } else {
                        Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("????", " ??? " + response.body() + " ??? " + response.message() + " ??? " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SingleResCount> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                networkDone();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_navi_main:
                replaceFragment(R.id.main_container, MainFragment.newInstance(), MainFragment.TAG, MainFragment.TAG);
                return true;
            case R.id.bottom_navi_agent:
                replaceFragment(R.id.main_container, AgentFragment.newInstance(null, null), AgentFragment.TAG, AgentFragment.TAG);
                return true;
            case R.id.bottom_navi_item:
                replaceFragment(R.id.main_container, ItemFragment.newInstance(null, null), ItemFragment.TAG, ItemFragment.TAG);
                return true;
            case R.id.bottom_navi_notice:
                replaceFragment(R.id.main_container, NoticeFragment.newInstance(null, null), NoticeFragment.TAG, NoticeFragment.TAG);
                return true;
        }
        return false;
    }

    protected void addFragment(@IdRes int containerViewId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commitAllowingStateLoss();
    }

    protected void replaceFragment(@IdRes int containerViewId, @NonNull Fragment fragment, @NonNull String fragmentTag, @Nullable String backStackStateName) {
        FragmentManager fm = getSupportFragmentManager();
        boolean popped = fm.popBackStackImmediate(backStackStateName, 0);

        if (!popped) {
            fm.beginTransaction()
                    .replace(containerViewId, fragment, fragmentTag)
                    // 메뉴와 함께 BackStack을 사용하기 어려워서 일단 제외.
                    //.addToBackStack(backStackStateName)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(activityCount != null)
            activityCount.cancel();

        if(reviewCount != null)
            reviewCount.cancel();
    }

    @Override
    public void onBackPressed() {
        final long FINSH_INTERVAL_TIME = 2000;
        long currentTime = System.currentTimeMillis();
        long intervalTime = currentTime - backPressedTime;

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
            } else {
                backPressedTime = currentTime;
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.close_ment), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected synchronized void networkDone() {
        if(activityCount.isExecuted() && reviewCount.isExecuted()){
            if(!isActivityCountSuccess && !isReviewCountSuccess){
                replaceFragment(R.id.main_container, ErrorFragment.newInstance(), ErrorFragment.TAG,ErrorFragment.TAG);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            disableProgressBar(progressBar);
        }
    }
}
