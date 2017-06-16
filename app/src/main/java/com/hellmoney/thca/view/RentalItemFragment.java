package com.hellmoney.thca.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellmoney.thca.R;
import com.hellmoney.thca.model.Item;
import com.hellmoney.thca.model.ItemRes;
import com.hellmoney.thca.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentalItemFragment extends Fragment {
    public static final String TAG = RentalItemFragment.class.getName();
    public static final String NAME = "전세자금대출";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;
    private List<Item> mItems;
    private ItemAdapter mItemAdapter;

    @BindView(R.id.rental_item_recycler_view)
    protected RecyclerView mRecyclerView;

    public RentalItemFragment() {
        // Required empty public constructor
    }

    public static RentalItemFragment newInstance(String param1, String param2) {
        RentalItemFragment fragment = new RentalItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rental_item, container, false);
        ButterKnife.bind(this, view);
        mItems = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // TODO : agentId 로그인 완성되면 대체
        String agentId = "agent1@naver.com";
        mItemAdapter = new ItemAdapter(mContext, mItems);
        mRecyclerView.setAdapter(mItemAdapter);
        Call<ItemRes> geItemsByAgentId = NetworkManager.service.getItemsByAgentId(agentId, this.NAME);
        geItemsByAgentId.enqueue(new Callback<ItemRes>() {
            @Override
            public void onResponse(Call<ItemRes> call, Response<ItemRes> response) {
                if(response.isSuccessful()) {
                    ItemRes results = response.body();
                    Log.d(TAG, results.getItems().toString());
                    mItems.clear();
                    mItems.addAll(results.getItems());
                    mItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ItemRes> call, Throwable t) {
                Log.e(TAG, "NETWORKING_ERROR");
            }
        });
    }
}
