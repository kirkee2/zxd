package com.hellmoney.thca.recyclerView.myQuotationRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.common.util.StringUtil;
import com.hellmoney.thca.common.util.TimeUtil;
import com.hellmoney.thca.module.network.networkData.Estimate;
import com.hellmoney.thca.view.DetailedEstimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kirkee on 2017. 11. 11..
 */
public class MyQuotationRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    private Context mContext;
    public MyQuotationRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public MyQuotationRecyclerViewHolder(LayoutInflater inflater, ViewGroup parent, Context mContext) {
        super(inflater.inflate(R.layout.estimate_item, parent, false));

        this.mContext = mContext;
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

        itemView.setOnClickListener(this);
    }

    public void bindRequest(Estimate estimate) {

        mEstimate = estimate;
        mEstimateStatus.setText(mEstimate.getStatus());

        int second = TimeUtil.timeLeftSecondParsing(mEstimate.getEndTime());

        int hour = second / 3600;
        int temp = second % 3600;
        int minute = temp / 60;
        int _second = temp % 60;

        if (second > 0) {
            mEstimateEndTime.setText("마감시간 " + hour + ": " + minute + "전");
        } else {
            mEstimateEndTime.setText("견적 마감");
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
        // LoanAmount가 사용자가 입력한 가격.
        mRequestAddressPrice.setText(StringUtil.toNumFormat(Integer.parseInt(mEstimate.getLoanAmount())) + "만원");
        mRequestJobType.setText(mEstimate.getJobType());

        mInterestLoanType.setText(mEstimate.getInterestRateType());

        if (mEstimate.getStatus().equals("대출실행완료")) {
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
        mContext.startActivity(DetailedEstimate.getIntent(mEstimate.getEstimateId(), mContext));
    }
}