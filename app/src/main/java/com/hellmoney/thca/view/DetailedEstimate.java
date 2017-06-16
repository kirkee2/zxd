package com.hellmoney.thca.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.model.Estimate;
import com.hellmoney.thca.model.SingleEstimateRes;
import com.hellmoney.thca.model.SingleRes;
import com.hellmoney.thca.network.NetworkManager;
import com.hellmoney.thca.util.timeUtil;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailedEstimate extends AppCompatActivity {

    private static final String ESTIMATEID = "estimateId";
    private static final String TAG = DetailedEstimate.class.getName();

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.loanTypeImageView)
    ImageView loanTypeImage;

    @BindView(R.id.requestAddress)
    TextView requestAddress;

    @BindView(R.id.requestAddressApt)
    TextView requestAddressApt;

    @BindView(R.id.requestAddressSize)
    TextView requestAddressSize;

    @BindView(R.id.requestLimitAmount)
    TextView requestLimitAmount;

    @BindView(R.id.loanAmount)
    TextView loanAmount;

    @BindView(R.id.typeLoan)
    TextView loanType;

    @BindView(R.id.jobType)
    TextView jobType;

    @BindView(R.id.scheduledTime)
    TextView scheduledTime;

    @BindView(R.id.fixedLoanAmount)
    TextView fixedLoanAmount;

    @BindView(R.id.itemName)
    TextView itemName;

    @BindView(R.id.loanRate)
    TextView loanRate;

    @BindView(R.id.repaymentType)
    TextView repaymentType;

    @BindView(R.id.earlyRepaymentType)
    TextView earlyRepaymentType;

    @BindView(R.id.overDueTime1)
    TextView overDueTime1;

    @BindView(R.id.overDueInterestRate1)
    TextView overDueInterestRate1;

    @BindView(R.id.overDueTime2)
    TextView overDueTime2;

    @BindView(R.id.overDueInterestRate2)
    TextView overDueInterestRate2;

    @BindView(R.id.overDueTime3)
    TextView overDueTime3;

    @BindView(R.id.overDueInterestRate3)
    TextView overDueInterestRate3;

    @BindView(R.id.estimateTextStatus)
    TextView statusTextView;

    @BindView(R.id.endTime)
    TextView endTime;

    @BindView(R.id.estimateLoanType)
    TextView estimateLoanType;

    @BindView(R.id.statusImageViewaa)
    ImageView statusImageView;

    @BindView(R.id.your_state_progress_bar_id)
    StateProgressBar stateProgressBar;

    @BindView(R.id.topContainer)
    FrameLayout mFrameLayout;

    @BindView(R.id.oneStatus)
    View oneStatusView;

    @OnClick(R.id.oneStatus)
    public void setOneStatusView(View view) {
        Log.d("STATUS", "clicked First Status View" );
    }

    @BindView(R.id.twoStatus)
    View twoStatusView;

    @OnClick(R.id.twoStatus)
    public void setTwoStatusView(View view) {
        new LovelyStandardDialog(view.getContext())
                .setTopColorRes(R.color.colorAccent)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setIcon(R.drawable.icon)
                .setTitle("진행 변화")
                .setMessage("상태를 변화하시겠습니가?")
                .setPositiveButton("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus("심사중", estimateId);
                        Toast.makeText(DetailedEstimate.this, "심사중으로 상태 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        stateProgressBar.enableAnimationToCurrentState(true);
                        stateProgressBar.setAnimationStartDelay(10);
                        stateProgressBar.invalidate();
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    @BindView(R.id.threeStatus)
    View threeStatusView;

    @OnClick(R.id.threeStatus)
    public void setThreeStatusView(View view) {
        new LovelyStandardDialog(view.getContext())
                .setTopColorRes(R.color.colorAccent)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setIcon(R.drawable.icon)
                .setTitle("진행 변화")
                .setMessage("상태를 변화하시겠습니가?")
                .setPositiveButton("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus("승인완료", estimateId);
                        Toast.makeText(DetailedEstimate.this, "승인완료 상태 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        stateProgressBar.enableAnimationToCurrentState(true);
                        stateProgressBar.setAnimationStartDelay(10);
                        stateProgressBar.invalidate();
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    @BindView(R.id.fourStatus)
    View fourStatusView;

    @OnClick(R.id.fourStatus)
    public void setFourStatusView(View view) {
        new LovelyStandardDialog(view.getContext())
                .setTopColorRes(R.color.colorAccent)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setIcon(R.drawable.icon)
                .setTitle("진행 변화")
                .setMessage("상태를 변화하시겠습니가?")
                .setPositiveButton("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus("대출실행완료", estimateId);
                        Toast.makeText(DetailedEstimate.this, "대출실행완료 상태 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                        stateProgressBar.enableAnimationToCurrentState(true);
                        stateProgressBar.setAnimationStartDelay(10);
                        stateProgressBar.invalidate();
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    private int estimateId;

    protected static Intent getIntent(int id, Context context) {
        Intent intent = new Intent(context, DetailedEstimate.class);
        intent.putExtra(ESTIMATEID, id);
        return intent;
    }

    String[] descriptionData = {"상담중", "심사중", "승인완료", "대출실행완료"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_estimate);
        ButterKnife.bind(this);

        estimateId = (int) getIntent().getSerializableExtra(ESTIMATEID);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("내 견적 상세");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        endTime.setSelected(true);
        Log.d(TAG, "EstimateId 는" + estimateId + "");




    }

    private Estimate estimates;
    private Estimate singleEstimate;

    @Override
    protected void onResume() {
        super.onResume();

        Call<SingleEstimateRes> getSingleMyEstimate = NetworkManager.service.getSingleMyEstimte(String.valueOf(estimateId));
        getSingleMyEstimate.enqueue(new Callback<SingleEstimateRes>() {
            @Override
            public void onResponse(Call<SingleEstimateRes> call, Response<SingleEstimateRes> response) {
                if (response.isSuccessful()) {

                    Log.d(TAG, "가져옴");

                    SingleEstimateRes estimate = response.body();
                    estimates = estimate.getEstimates();
                    singleEstimate = estimates;
                    statusTextView.setText(singleEstimate.getStatus());

                    int time = timeUtil.timeLeftSecondParsing(singleEstimate.getEndTime());

                    int hour = time / 3600;
                    int temp = time % 3600;
                    int minute = temp / 60;
                    int second = temp % 60;

                    if (time > 0) {

                        endTime.setText("마감 " + timeUtil.formatNumber2(hour) + "시간 " + timeUtil.formatNumber2(minute) + " 분 " + timeUtil.formatNumber2(second) + " 전");
                        endTime.setSelected(true);
                    } else {
                        endTime.setText("마감까지 " + "00" + "시" + "00 분" + " 전");
                        endTime.setSelected(true);
                    }

                    requestAddress.setText(singleEstimate.getTotalAddress());
                    requestAddressApt.setText(singleEstimate.getAptName());
                    requestAddressSize.setText(singleEstimate.getSize());

                    String pattern = "#####";
                    DecimalFormat dformat = new DecimalFormat(pattern);

                    requestLimitAmount.setText(dformat.format(singleEstimate.getLimiteAmount()) + "만원");
                    loanAmount.setText(singleEstimate.getLoanAmount() + "만원");
                    loanType.setText(singleEstimate.getLoanType());
                    jobType.setText(singleEstimate.getJobType());
                    scheduledTime.setText(timeUtil.dateFormat.format(singleEstimate.getScheduledTime()));


                    itemName.setText(singleEstimate.getItemName());

                    fixedLoanAmount.setText(singleEstimate.getFixedLoanAmount() + "만원");
                    estimateLoanType.setText(singleEstimate.getInterestRateType());
                    loanRate.setText(singleEstimate.getInterestRate() + "%");
                    repaymentType.setText(singleEstimate.getRepaymentType());
                    earlyRepaymentType.setText(singleEstimate.getEarlyRepaymentFee() + "%");
                    overDueTime1.setText(singleEstimate.getOverdueTime1());
                    overDueTime2.setText(singleEstimate.getOverdueTime2());
                    overDueTime3.setText(singleEstimate.getOverdueTime3());
                    overDueInterestRate1.setText(singleEstimate.getOverdueInterestRate1() + "%");
                    overDueInterestRate2.setText(singleEstimate.getOverdueInterestRate2() + "%");
                    overDueInterestRate3.setText(singleEstimate.getOverdueInterestRate3() + "%");


                    stateProgressBar.setStateDescriptionData(descriptionData);

                    switch (singleEstimate.getStatus()) {

                        case "상담중":
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                            statusImageView.setImageResource(R.drawable.myestimate_selected);
                            stateProgressBar.enableAnimationToCurrentState(true);
                            stateProgressBar.checkStateCompleted(true);
                            stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.FOUR);

                            break;
                        case "심사중":
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                            statusImageView.setImageResource(R.drawable.myestimate_selected);
                            stateProgressBar.enableAnimationToCurrentState(true);
                            stateProgressBar.checkStateCompleted(true);
                            stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.FOUR);
                            break;
                        case "승인완료":
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                            statusImageView.setImageResource(R.drawable.myestimate_selected);
                            stateProgressBar.enableAnimationToCurrentState(true);
                            stateProgressBar.checkStateCompleted(true);
                            stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.FOUR);
                            break;
                        case "대출실행완료":
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                            statusImageView.setImageResource(R.drawable.myestiamte_ing);
                            stateProgressBar.enableAnimationToCurrentState(true);
                            stateProgressBar.checkStateCompleted(true);
                            stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.FOUR);
                            break;
                    }

                    String estimateId = String.valueOf(singleEstimate.getEstimateId());

                    if (!singleEstimate.getSelectedEstimateId().equals(estimateId)) {
                        mFrameLayout.setVisibility(View.GONE);
                    }else {
                        mFrameLayout.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<SingleEstimateRes> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void setStatus(String status, int estimateId) {

        Call<SingleRes> call = NetworkManager.service.setStatus(estimateId, status);
        call.enqueue(new Callback<SingleRes>() {
            @Override
            public void onResponse(Call<SingleRes> call, Response<SingleRes> response) {
                if (response.isSuccessful()) {
                    SingleRes res = response.body();
                    if (res.getMsg().equals("SUCCESS")) {
                        Log.d("STATUS", "CHANGED_STATUS");
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleRes> call, Throwable t) {
                Log.d("STATUS", "ERROR_STATUS");
            }
        });
    }

    public void dialog(final Context context, final String status, final int estimateId) {
        new LovelyStandardDialog(context)
                .setTopColorRes(R.color.indigo)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setTitle("진행 변화")
                .setMessage("상태를 변화하시겠습니가?")
                .setPositiveButton("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }
}

