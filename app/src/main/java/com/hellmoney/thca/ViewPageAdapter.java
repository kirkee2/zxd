package com.hellmoney.thca;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hellmoney.thca.View.AgentFragment;
import com.hellmoney.thca.View.ItemFragment;
import com.hellmoney.thca.View.MainFragment;
import com.hellmoney.thca.View.NoticeFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Returning the current tabs
        switch (position) {
            case 0:
                return MainFragment.newInstance(null,null);
            case 1:
                return AgentFragment.newInstance(null,null);
            case 2:
                return NoticeFragment.newInstance(null,null);
            case 3:
                return ItemFragment.newInstance(null,null);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
            return POSITION_NONE;
    }
}
