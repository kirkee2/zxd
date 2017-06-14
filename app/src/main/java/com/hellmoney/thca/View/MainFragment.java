package com.hellmoney.thca.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.model.Request;
import com.hellmoney.thca.model.RequestRes;
import com.hellmoney.thca.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;

    private RecyclerView recyclerView;
    private MainContentAdapter mMainContentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Request> mRequests;



    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getActivity();
        mRequests = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mMainContentAdapter = new MainContentAdapter(mRequests, mContext);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mMainContentAdapter);


        Call<RequestRes> getRequests = NetworkManager.service.getRequests("agent1@naver.com");
        getRequests.enqueue(new Callback<RequestRes>() {
            @Override
            public void onResponse(Call<RequestRes> call, Response<RequestRes> response) {
                if(response.isSuccessful()){
                    RequestRes requestRes = response.body();
                    Log.d("Len", requestRes.getRequests().toString());
                    mRequests.clear();
                    mRequests.addAll(requestRes.getRequests());
                    mMainContentAdapter.notifyDataSetChanged();
                }
                Log.d("Len", "MAIN ON");
            }

            @Override
            public void onFailure(Call<RequestRes> call, Throwable t) {
                Log.d("Len",t.toString());
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

    private class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mEstimateCount;
        private TextView mEstimateEndTime;
        private ImageView mLoanTypeImageView;
        private TextView mRequestAddress;
        private TextView mRequestAddressApt;
        private TextView mRequestAddressSize;
        private TextView mRequestAddressPrice;
        private TextView mRequestJobType;
        private TextView mOverDue;
        private ImageView mStar;
        private Request mRequest;

        public MainViewHolder(LayoutInflater inflater, ViewGroup parent)  {
            super(inflater.inflate(R.layout.main_item, parent, false));

            mEstimateCount = (TextView) itemView.findViewById(R.id.estimateCount);
            mEstimateEndTime = (TextView) itemView.findViewById(R.id.endTime);
            mLoanTypeImageView = (ImageView) itemView.findViewById(R.id.loanTypeImageView);
            mRequestAddress = (TextView) itemView.findViewById(R.id.requestAddress);
            mRequestAddressApt = (TextView) itemView.findViewById(R.id.requestAddressApt);
            mRequestAddressSize = (TextView) itemView.findViewById(R.id.requestAddressSize);
            mRequestAddressPrice = (TextView) itemView.findViewById(R.id.requestPrice);
            mRequestJobType = (TextView) itemView.findViewById(R.id.requestJobType);
            mOverDue = (TextView) itemView.findViewById(R.id.OverDueRecord);
            mStar = (ImageView) itemView.findViewById(R.id.star);

            itemView.setOnClickListener(this);
        }

        public void bindRequest(Request request) {

            mRequest = request;
            mEstimateCount.setText(mRequest.getCountEstimate());
            mEstimateEndTime.setText(mRequest.getEndTime());
            mRequestAddress.setText(mRequest.getTotalAddress());
            mRequestAddressApt.setText(mRequest.getAptName());
            mRequestAddressSize.setText(mRequest.getSize());
            mRequestAddressPrice.setText(mRequest.getPrice());
            mRequestJobType.setText(mRequest.getJobType());
            mOverDue.setText(mRequest.getOverdueRecord());

            switch (mRequest.getFavorite()){
                case 1 :
                    mStar.setImageResource(R.mipmap.ic_favorite_active);
                    break;
                case 0 :
                    mStar.setImageResource(R.mipmap.ic_favorite_inactive_trim);
                    break;
            }

            switch(mRequest.getLoanType()){
                case "담보":
                    mLoanTypeImageView.setImageResource(R.mipmap.ic_dambu);
                    break;
                case "전세":
                    mLoanTypeImageView.setImageResource(R.mipmap.ic_junsa);
                    break;

            }
        }
        @Override
        public void onClick(View v) {
            getActivity().startActivity(DetailedRequest.getIntent(mRequest.getRequestId(), mContext));
        }
    }

    private class MainContentAdapter extends RecyclerView.Adapter<MainViewHolder> {

        private List<Request> mRequests;
        private Context mContext;

        public MainContentAdapter(List<Request> requests, Context context) {
            this.mRequests = requests;
            this.mContext = context;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            Request request = mRequests.get(position);
            holder.bindRequest(request);
        }

        @Override
        public int getItemCount() {
            return mRequests.size();
        }
    }

}
