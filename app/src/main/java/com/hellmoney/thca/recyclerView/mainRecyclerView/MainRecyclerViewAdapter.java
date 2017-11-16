package com.hellmoney.thca.recyclerView.mainRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hellmoney.thca.module.network.networkData.Request;

import java.util.List;

/**
 * Created by kirkee on 2017. 11. 10..
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder> {
    private List<Request> mRequests;
    private Context mContext;
    private String kakaoId;

    public MainRecyclerViewAdapter(List<Request> requests,String kakaoId, Context context) {
        this.mRequests = requests;
        this.kakaoId = kakaoId;
        this.mContext = context;
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainRecyclerViewHolder(LayoutInflater.from(parent.getContext()), parent,kakaoId,mContext);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {
        Request request = mRequests.get(position);
        holder.bindRequest(request);
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }
}