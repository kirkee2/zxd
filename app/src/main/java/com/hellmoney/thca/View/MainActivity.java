package com.hellmoney.thca.view;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hellmoney.thca.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.navigation)
    protected BottomNavigationView mBottomNavigationView;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addFragment(R.id.main_container, MainFragment.newInstance(null, null), MainFragment.TAG);

        setSupportActionBar(mToolbar);

        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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