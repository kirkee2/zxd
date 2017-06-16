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
import com.hellmoney.thca.model.Estimate;
import com.hellmoney.thca.model.EstimateRes;
import com.hellmoney.thca.network.NetworkManager;
import com.hellmoney.thca.util.timeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgentFragment extends Fragment {

    public static final String TAG = AgentFragment.class.getName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;

    private RecyclerView recyclerView;
    private MainContentAdapter mMainContentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Estimate> mEstimates;


    public AgentFragment() {
    }

    public static AgentFragment newInstance(String param1, String param2) {
        AgentFragment fragment = new AgentFragment();
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
        recyclerView.setNestedScrollingEnabled(false);


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

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mEstimateStatus;
        private TextView mEstimateEndTime;
        private ImageView mLoanTypeImageView;
        private TextView mRequestAddress;
        private TextView mRequestAddressApt;
        private TextView mRequestAddressSize;
        private TextView mRequestAddressPrice;
        private TextView mRequestJobType;
        private TextView mInterestLoanType;
        private Estimate mEstimate;

        @BindView(R.id.statusImageView)
        protected ImageView mImageView;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

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
            mInterestLoanType = (TextView) itemView.findViewById(R.id.estimateLoanType);
            mImageView = (ImageView) itemView.findViewById(R.id.statusImageView);

            mEstimateEndTime.setSelected(true);

            itemView.setOnClickListener(this);
        }

        public void bindRequest(Estimate estimate) {

            mEstimate = estimate;
            mEstimateStatus.setText(mEstimate.getStatus());

            int second = timeUtil.timeLeftSecondParsing(mEstimate.getEndTime());

            int hour = second / 3600;
            int temp = second % 3600;
            int minute = temp / 60;
            int _second = temp % 60;

            if (second > 0) {
                mEstimateEndTime.setText("마감시간이 " + hour + "시 " + minute + "분" + _second + "초 남았습니다.");
            } else {
                mEstimateEndTime.setText("마감까지 " + "00" + ":" + "00" + " 남았습니다.");
            }

              /*
        사용방법

            int leftSecond  = mainPageViewPagerObject.getLeftSecond();
            int hour = leftSecond/3600;
            int tmp = leftSecond%3600;
            int minute = tmp/60;
            int second = tmp%60;

            if(leftSecond > 0){
               leftTime.setText("마감까지 " + CommonClass.formatNumber2(hour) + ":" + CommonClass.formatNumber2(minute)  + ":" + CommonClass.formatNumber2(second) + " 남았습니다.");

            }else{
                leftTime.setText("마감까지 " + "00" + ":" +"00" + " 남았습니다.");
            }
             */

            mRequestAddress.setText(mEstimate.getTotalAddress());
            mRequestAddressApt.setText(mEstimate.getAptName());
            mRequestAddressSize.setText(mEstimate.getSize());
            mRequestAddressPrice.setText(mEstimate.getPrice());
            mRequestJobType.setText(mEstimate.getJobType());

            mInterestLoanType.setText(mEstimate.getInterestRateType());

            if (mEstimate.getStatus().equals("대출실행완료")){
                mImageView.setImageResource(R.drawable.myestiamte_ing);
            } else {
                mImageView.setImageResource(R.drawable.myestimate_selected);
            }


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
            getActivity().startActivity(DetailedEstimate.getIntent(mEstimate.getEstimateId(), mContext));
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
