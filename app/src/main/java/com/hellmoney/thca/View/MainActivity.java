package com.hellmoney.thca.view;

import android.os.Bundle;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hellmoney.thca.R;
import com.hellmoney.thca.TempAgent;
import com.hellmoney.thca.message.MyFirebaseInstanceIDService;
import com.hellmoney.thca.model.Agent;
import com.hellmoney.thca.model.AgentDetailRes;
import com.hellmoney.thca.network.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    protected NavigationView mNavigationView;
    @BindView(R.id.navigation)
    protected BottomNavigationView mBottomNavigationView;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    private View mNavHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addFragment(R.id.main_container, MainFragment.newInstance(null, null), MainFragment.TAG);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavHeader = mNavigationView.inflateHeaderView(R.layout.nav_header);

        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Call<AgentDetailRes> getAgent = NetworkManager.service.getAgent(TempAgent.AGENT_ID);
        getAgent.enqueue(new Callback<AgentDetailRes>() {
            @Override
            public void onResponse(Call<AgentDetailRes> call, Response<AgentDetailRes> response) {
                if(response.isSuccessful()) {
                    AgentDetailRes results = response.body();
                    if(results.getMessage().equals("SUCCESS")) {
                        Agent agent = results.getAgent();
                        ((TextView) mNavHeader.findViewById(R.id.agent_name_text_view)).setText(agent.getName());
                        ((TextView) mNavHeader.findViewById(R.id.agent_company_name_text_view)).setText(agent.getCompanyName());
                        ((TextView) mNavHeader.findViewById(R.id.test_fcm_token)).setText(FirebaseInstanceId.getInstance().getToken());
                        Glide.with(getApplicationContext())
                                .load(agent.getPhoto())
                                .thumbnail(0.1f)
                                .into((ImageView) mNavHeader.findViewById(R.id.image_view));
                    } else {
                        Log.e(TAG, results.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AgentDetailRes> call, Throwable t) {
                Log.e(TAG, "NETWORKING_ERROR");
            }
        });
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_main:
                replaceFragment(R.id.main_container, MainFragment.newInstance(null, null), MainFragment.TAG, MainFragment.TAG);
                return true;
            case R.id.navigation_agent:
                replaceFragment(R.id.main_container, AgentFragment.newInstance(null, null), AgentFragment.TAG, AgentFragment.TAG);
                return true;
            case R.id.navigation_notice:
                replaceFragment(R.id.main_container, NoticeFragment.newInstance(null, null), NoticeFragment.TAG, NoticeFragment.TAG);
                return true;
            case R.id.navigation_item:
                replaceFragment(R.id.main_container, ItemFragment.newInstance(null, null), ItemFragment.TAG, ItemFragment.TAG);
                return true;
        }
        return false;
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        FragmentManager fm = getSupportFragmentManager();
        boolean popped = fm.popBackStackImmediate(backStackStateName, 0);

        if (!popped) {
            fm.beginTransaction()
                    .replace(containerViewId, fragment, fragmentTag)
                    // 메뉴와 함께 BackStack을 사용하기 어려워서 일단 제외.
                    //.addToBackStack(backStackStateName)
                    .commit();
        }
    }
}