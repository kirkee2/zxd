package com.hellmoney.thca.recyclerView.mainRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.hellmoney.thca.R;
import com.hellmoney.thca.common.util.StringUtil;
import com.hellmoney.thca.common.util.TimeUtil;
import com.hellmoney.thca.fragment.MainFragment;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.module.network.networkData.LikeRes;
import com.hellmoney.thca.module.network.networkData.Request;
import com.hellmoney.thca.view.DetailedRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hellmoney.thca.common.util.TimeUtil.formatNumber2;

/**
 * Created by kirkee on 2017. 11. 10..
 */

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = MainFragment.class.getName();

    private String kakaoId;
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
    private Context mContext;

    MainRecyclerViewHolder(LayoutInflater inflater, ViewGroup parent,String kakaoId,Context mContext) {
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

        this.kakaoId = kakaoId;
        this.mContext = mContext;
    }

    public void bindRequest(Request request) {

        mRequest = request;
        mEstimateCount.setText(mRequest.getCountEstimate());

        int secondParsing = TimeUtil.timeLeftSecondParsing(mRequest.getEndTime());
        int hour = secondParsing/3600;
        int tmp = secondParsing%3600;
        final int minute = tmp/60;
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
                mContext.startActivity(DetailedRequest.getIntent(mRequest.getRequestId(), mContext));
            }
        });

        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((ToggleButton) v).isChecked();
                if (isChecked) {
                    Call<LikeRes> insertFavorite = NetworkManager.service.like(kakaoId, mRequest.getRequestId());
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
                    Call<LikeRes> deleteFavorite = NetworkManager.service.unlike(kakaoId, mRequest.getRequestId());
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
