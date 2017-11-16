package com.hellmoney.thca.viewPager.myQuotationList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hellmoney.thca.module.network.networkData.Estimate;

import java.util.ArrayList;

/**
 * Created by kirkee on 2017. 11. 11..
 */

public class MyQuotationFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Estimate> ongoingQuotation;
    ArrayList<Estimate> beforeSelectionQuotation;

    public MyQuotationFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyQuotationFragmentPagerAdapter(FragmentManager fm,ArrayList<Estimate> ongoingQuotation,ArrayList<Estimate> beforeSelectionQuotation) {
        super(fm);
        this.ongoingQuotation = ongoingQuotation;
        this.beforeSelectionQuotation = beforeSelectionQuotation;
        notifyDataSetChanged();
    }

    public void init(ArrayList<Estimate> mainPageViewPagerObjectArrayListOne,ArrayList<Estimate> mainPageViewPagerObjectArrayListTwo){
        ongoingQuotation = mainPageViewPagerObjectArrayListOne;
        beforeSelectionQuotation = mainPageViewPagerObjectArrayListTwo;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return MyQuotationFragment.newInstance(ongoingQuotation);
        }else{
            return MyQuotationFragment.newInstance(beforeSelectionQuotation);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "진행중";
        }else{
            return "선택 대기";
        }
    }
}
