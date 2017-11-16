package com.hellmoney.thca.recyclerView.myQuotationRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hellmoney.thca.module.network.networkData.Estimate;
import com.hellmoney.thca.view.AgentFragment;

import java.util.List;

/**
 * Created by kirkee on 2017. 11. 11..
 */

public class MyQuotationRecyclerViewAdapter extends RecyclerView.Adapter<MyQuotationRecyclerViewHolder> {

    private List<Estimate> mEstimates;
    private Context mContext;

    public MyQuotationRecyclerViewAdapter(List<Estimate> estimates, Context context) {
        this.mEstimates = estimates;
        this.mContext = context;
    }

    @Override
    public MyQuotationRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyQuotationRecyclerViewHolder(LayoutInflater.from(parent.getContext()), parent,mContext);
    }

    @Override
    public void onBindViewHolder(MyQuotationRecyclerViewHolder holder, int position) {
        Estimate estimate = mEstimates.get(position);
        holder.bindRequest(estimate);
    }

    @Override
    public int getItemCount() {
        return mEstimates.size();
    }
}
