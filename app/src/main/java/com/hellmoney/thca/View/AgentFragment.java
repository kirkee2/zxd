package com.hellmoney.thca.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.common.CommonBaseFragment;
import com.hellmoney.thca.module.network.networkData.Estimate;
import com.hellmoney.thca.module.network.networkData.EstimateRes;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.common.util.StringUtil;
import com.hellmoney.thca.common.util.TimeUtil;
import com.hellmoney.thca.recyclerView.myQuotationRecyclerView.MyQuotationRecyclerViewAdapter;
import com.hellmoney.thca.viewPager.myQuotationList.MyQuotationFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgentFragment extends CommonBaseFragment {
    public static final String TAG = AgentFragment.class.getName();

    private Context mContext;

    //private RecyclerView recyclerView;
    private MyQuotationFragmentPagerAdapter mMyQuotationFragmentPagerAdapter;
    //private MyQuotationRecyclerViewAdapter mMyQuotationRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Estimate> mOngoingQuotation;
    private ArrayList<Estimate> mBeforeSelectionQuotation;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    public AgentFragment() {
    }

    public static AgentFragment newInstance(String param1, String param2) {
        AgentFragment fragment = new AgentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_estimate, container, false);
        mContext = getActivity();
        mOngoingQuotation = new ArrayList<>();
        mBeforeSelectionQuotation = new ArrayList<>();
        mViewPager = (ViewPager)view.findViewById(R.id.viewPager);
        mTabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        //recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        //recyclerView.setLayoutManager(linearLayoutManager);


        //mMyQuotationRecyclerViewAdapter = new MyQuotationRecyclerViewAdapter(mOngoingEstimates, mContext);
        //recyclerView.setAdapter(mMyQuotationRecyclerViewAdapter);

        Call<EstimateRes> getMyEstimate = NetworkManager.service.getMyEstimate(kakaoId);
        getMyEstimate.enqueue(new Callback<EstimateRes>() {
            @Override
            public void onResponse(Call<EstimateRes> call, Response<EstimateRes> response) {
                if (response.isSuccessful()) {
                    EstimateRes estimateRes = response.body();
                    if(estimateRes.getMessage().equals("SUCCESS")) {
                        mOngoingQuotation.clear();
                        mBeforeSelectionQuotation.clear();
                        mOngoingQuotation.addAll(estimateRes.getEstimates());
                        mBeforeSelectionQuotation.addAll(estimateRes.getEstimates());

                        mMyQuotationFragmentPagerAdapter = new MyQuotationFragmentPagerAdapter(getFragmentManager(),mOngoingQuotation,mBeforeSelectionQuotation);
                        //myQuotationFragmentPagerAdapter.init(mainPageViewPagerObjectOneM,mainPageViewPagerObjectTwoM);

                        mViewPager.setAdapter(mMyQuotationFragmentPagerAdapter);
                        mTabLayout.setupWithViewPager(mViewPager, true);
                        mMyQuotationFragmentPagerAdapter.notifyDataSetChanged();
                        //mMyQuotationRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<EstimateRes> call, Throwable t) {
                Log.d("Len", t.toString());
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
