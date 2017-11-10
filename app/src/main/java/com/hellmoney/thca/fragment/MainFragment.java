package com.hellmoney.thca.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.hellmoney.thca.R;
import com.hellmoney.thca.TempAgent;
import com.hellmoney.thca.module.network.networkData.LikeRes;
import com.hellmoney.thca.module.network.networkData.Request;
import com.hellmoney.thca.module.network.networkData.RequestRes;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.common.util.StringUtil;
import com.hellmoney.thca.common.util.TimeUtil;
import com.hellmoney.thca.view.DetailedRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hellmoney.thca.common.util.TimeUtil.formatNumber2;

public class MainFragment extends Fragment {
    public static final String TAG = MainFragment.class.getName();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getActivity();
        mRequests = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainContentAdapter = new MainContentAdapter(mRequests, mContext);
        recyclerView.setAdapter(mMainContentAdapter);
        Call<RequestRes> getRequests = NetworkManager.service.getRequests(TempAgent.AGENT_ID);
        getRequests.enqueue(new Callback<RequestRes>() {
            @Override
            public void onResponse(Call<RequestRes> call, Response<RequestRes> response) {
                if (response.isSuccessful()) {
                    RequestRes requestRes = response.body();
                    if(requestRes.getMessage().equals("SUCCESS")) {
                        Log.d(TAG, requestRes.getRequests().toString());
                        mRequests.clear();
                        mRequests.addAll(requestRes.getRequests());
                        mMainContentAdapter.notifyDataSetChanged();
                    } else {

                    }
                }
                Log.d(TAG, "MAIN ON");
            }

            @Override
            public void onFailure(Call<RequestRes> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    private class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView mEstimateCount;
        private TextView mEstimateEndTime;
        private ImageView mLoanTypeImageView;
        private TextView mRequestAddress;
        private TextView mRequestAddressApt;
        private TextView mRequestAddressSize;
        private TextView mRequestAddressPrice;
        private TextView mRequestJobType;
        private TextView loanType;
        private ToggleButton mStar;
        private Request mRequest;

        public MainViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.main_item, parent, false));

            mEstimateCount = (TextView) itemView.findViewById(R.id.estimateCount);
            mEstimateEndTime = (TextView) itemView.findViewById(R.id.endTime);

            mEstimateEndTime.setSelected(true);

            mLoanTypeImageView = (ImageView) itemView.findViewById(R.id.loanTypeImageView);
            mRequestAddress = (TextView) itemView.findViewById(R.id.requestAddress);
            mRequestAddressApt = (TextView) itemView.findViewById(R.id.requestAddressApt);
            mRequestAddressSize = (TextView) itemView.findViewById(R.id.requestAddressSize);
            mRequestAddressPrice = (TextView) itemView.findViewById(R.id.requestPrice);
            mRequestJobType = (TextView) itemView.findViewById(R.id.requestJobType);
            loanType = (TextView) itemView.findViewById(R.id.loanType);
            mStar = (ToggleButton) itemView.findViewById(R.id.star);
        }

        public void bindRequest(Request request) {

            mRequest = request;
            mEstimateCount.setText(mRequest.getCountEstimate());

            int secondParsing = TimeUtil.timeLeftSecondParsing(mRequest.getEndTime());
            int hour = secondParsing/3600;
            int tmp = secondParsing%3600;
            int minute = tmp/60;
            int second = tmp%60;

            if(secondParsing > 0) {
                mEstimateEndTime.setText("마감 " + formatNumber2(hour) + " : " + formatNumber2(minute) + "전");
            } else {
                mEstimateEndTime.setText("견적 마감");

            }
            mRequestAddress.setText(mRequest.getTotalAddress());
            mRequestAddressApt.setText(mRequest.getAptName());
            mRequestAddressSize.setText(mRequest.getSize());
            mRequestAddressPrice.setText(StringUtil.toNumFormat(Integer.parseInt(mRequest.getLoanAmount())) + " 만원");
            mRequestJobType.setText(mRequest.getJobType());
            loanType.setText(mRequest.getInterestRateType());
            switch (mRequest.getFavorite()) {
                case 1:
                    mStar.setChecked(true);
                    break;
                case 0:
                    mStar.setChecked(false);
                    break;
            }
            switch (mRequest.getLoanType()) {
                case "주택담보대출":
                    mLoanTypeImageView.setImageResource(R.drawable.dambuu);
                    break;
                case "전세자금대출":
                    mLoanTypeImageView.setImageResource(R.drawable.sunjaa);
                    break;
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().startActivity(DetailedRequest.getIntent(mRequest.getRequestId(), mContext));
                }
            });

            mStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = ((ToggleButton) v).isChecked();
                    if (isChecked) {
                        Call<LikeRes> insertFavorite = NetworkManager.service.like(TempAgent.AGENT_ID, mRequest.getRequestId());
                        insertFavorite.enqueue(new Callback<LikeRes>() {
                            @Override
                            public void onResponse(Call<LikeRes> call, Response<LikeRes> response) {
                                if (response.isSuccessful()) {
//                                    Toast.makeText(mContext, "좋아요!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<LikeRes> call, Throwable t) {
                                Log.e(TAG, "onFailure: ");
                            }
                        });
                    } else {
                        Call<LikeRes> deleteFavorite = NetworkManager.service.unlike(TempAgent.AGENT_ID, mRequest.getRequestId());
                        deleteFavorite.enqueue(new Callback<LikeRes>() {
                            @Override
                            public void onResponse(Call<LikeRes> call, Response<LikeRes> response) {
                                if (response.isSuccessful()) {
//                                    Toast.makeText(mContext, "좋아요를 취소합니다.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<LikeRes> call, Throwable t) {
                                Log.e(TAG, "onFailure: ");
                            }
                        });
                    }
                }
            });
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
