package com.hellmoney.thca.viewPager.myQuotationList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellmoney.thca.R;
import com.hellmoney.thca.module.network.networkData.Estimate;
import com.hellmoney.thca.recyclerView.myQuotationRecyclerView.MyQuotationRecyclerViewAdapter;

import java.util.ArrayList;

public class MyQuotationFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyQuotationRecyclerViewAdapter mMyQuotationRecyclerViewAdapter;
    private ArrayList<Estimate> mEstimateArrayList;
    private LinearLayoutManager linearLayoutManager;

    int timerCheck;
    int myPage;


    public MyQuotationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MyQuotationFragment newInstance(ArrayList<Estimate> mEstimateArrayList) {
        MyQuotationFragment fragment = new MyQuotationFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("mEstimateArrayList",mEstimateArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.mEstimateArrayList = getArguments().getParcelableArrayList("mEstimateArrayList");
        }

        mMyQuotationRecyclerViewAdapter = new MyQuotationRecyclerViewAdapter(mEstimateArrayList,getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_quotation, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false); //RecyclerView에 설정 할 LayoutManager 초기화
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mMyQuotationRecyclerViewAdapter);

        return view;
    }
}