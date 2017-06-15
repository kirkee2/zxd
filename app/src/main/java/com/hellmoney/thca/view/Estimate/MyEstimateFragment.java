package com.hellmoney.thca.view.Estimate;

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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.hellmoney.thca.R;
import com.hellmoney.thca.model.Estimate;
import com.hellmoney.thca.model.EstimateRes;
import com.hellmoney.thca.model.LikeRes;
import com.hellmoney.thca.model.Request;
import com.hellmoney.thca.model.RequestRes;
import com.hellmoney.thca.network.NetworkManager;
import com.hellmoney.thca.view.DetailedRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEstimateFragment extends Fragment {
    private static final String TAG = MyEstimateFragment.class.getName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;

    private RecyclerView recyclerView;
    private MainContentAdapter mMainContentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Estimate> mEstimates;


    public MyEstimateFragment() {
    }

    public static MyEstimateFragment newInstance(String param1, String param2) {
        MyEstimateFragment fragment = new MyEstimateFragment();
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

        View view = inflater.inflate(R.layout.fragment_estimate, container, false);
        mContext = getActivity();
        mEstimates = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mMainContentAdapter = new MainContentAdapter(mEstimates, mContext);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mMainContentAdapter);


        Call<EstimateRes> getMyEstimate = NetworkManager.service.getMyEstimate("agent1@naver.com");
        getMyEstimate.enqueue(new Callback<EstimateRes>() {
            @Override
            public void onResponse(Call<EstimateRes> call, Response<EstimateRes> response) {
                if (response.isSuccessful()) {
                    EstimateRes estimateRes = response.body();
                    mEstimates.clear();
                    mEstimates.addAll(estimateRes.getEstimates());
                    mMainContentAdapter.notifyDataSetChanged();
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

    private class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mEstimateStatus;
        private TextView mEstimateEndTime;
        private ImageView mLoanTypeImageView;
        private TextView mRequestAddress;
        private TextView mRequestAddressApt;
        private TextView mRequestAddressSize;
        private TextView mRequestAddressPrice;
        private TextView mRequestJobType;
        private TextView mOverDue;
        private ToggleButton mStar;
        private Estimate mEstimate;

        public MainViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.estimate_item, parent, false));

            mEstimateStatus = (TextView) itemView.findViewById(R.id.estimateStatus);
            mEstimateEndTime = (TextView) itemView.findViewById(R.id.endTime);
            mLoanTypeImageView = (ImageView) itemView.findViewById(R.id.loanTypeImageView);
            mRequestAddress = (TextView) itemView.findViewById(R.id.requestAddress);
            mRequestAddressApt = (TextView) itemView.findViewById(R.id.requestAddressApt);
            mRequestAddressSize = (TextView) itemView.findViewById(R.id.requestAddressSize);
            mRequestAddressPrice = (TextView) itemView.findViewById(R.id.requestPrice);
            mRequestJobType = (TextView) itemView.findViewById(R.id.requestJobType);
            mOverDue = (TextView) itemView.findViewById(R.id.OverDueRecord);
            mStar = (ToggleButton) itemView.findViewById(R.id.star);

            itemView.setOnClickListener(this);
        }

        public void bindRequest(Estimate estimate) {

            mEstimate = estimate;
            mEstimateStatus.setText(mEstimate.getCountEstimate());
            mEstimateEndTime.setText(mRequest.getEndTime());
            mRequestAddress.setText(mRequest.getTotalAddress());
            mRequestAddressApt.setText(mRequest.getAptName());
            mRequestAddressSize.setText(mRequest.getSize());
            mRequestAddressPrice.setText(mRequest.getPrice());
            mRequestJobType.setText(mRequest.getJobType());
            mOverDue.setText(mRequest.getOverdueRecord());


            switch (mEstimate.getLoanType()) {
                case "주택담보대출":
                    mLoanTypeImageView.setImageResource(R.drawable.dambuu);
                    break;
                case "전세자금대출":
                    mLoanTypeImageView.setImageResource(R.drawable.sunjaa);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            getActivity().startActivity(DetailedRequest.getIntent(mRequest.getRequestId(), mContext));
        }
    }

    private class MainContentAdapter extends RecyclerView.Adapter<MainViewHolder> {

        private List<Estimate> mEstimates;
        private Context mContext;

        public MainContentAdapter(List<Estimate> estimates, Context context) {
            this.mEstimates = estimates;
            this.mContext = context;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            Estimate estimate = mEstimates.get(position);
            holder.bindRequest(estimate);
        }

        @Override
        public int getItemCount() {
            return mEstimates.size();
        }
    }

}
