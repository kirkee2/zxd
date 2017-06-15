package com.hellmoney.thca.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.model.Request;
import com.hellmoney.thca.model.SingleRequestRes;
import com.hellmoney.thca.network.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SendDetailedRequest extends AppCompatActivity {

    private static final String REQUESTID = "requestId";
    private static final String TAG = SendDetailedRequest.class.getName();

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

    @BindView(R.id.requestOverDue)
    TextView requestOverDue;

    @BindView(R.id.jobType)
    TextView jobType;

    @BindView(R.id.scheduledTime)
    TextView scheduledTime;

    @BindView(R.id.mainBank)
    TextView mainBank;

    //저장된 아이템 불러오는 버튼
    @BindView(R.id.callItem)
    ImageView callItem;

    @BindView(R.id.fixedLoanAmount)
    EditText fixedLoanAmount;

    @BindView(R.id.itemName)
    EditText itemName;

    @BindView(R.id.loanRate)
    EditText loanRate;

    @BindView(R.id.repaymentType)
    EditText repaymentType;

    @BindView(R.id.earlyRepaymentType)
    EditText earlyRepaymentType;

    @BindView(R.id.overDueTime1)
    EditText overDueTime1;

    @BindView(R.id.overDueInterestRate1)
    EditText overDueInterestRate1;

    @BindView(R.id.overDueTime2)
    EditText overDueTime2;

    @BindView(R.id.overDueInterestRate2)
    EditText overDueInterestRate2;

    @BindView(R.id.overDueTime3)
    EditText overDueTime3;

    @BindView(R.id.overDueInterestRate3)
    EditText overDueInterestRate3;

    @BindView(R.id.sendEstimate)
    Button sendEstimateButton;

    @OnClick(R.id.sendEstimate)
    public void submit(View view) {

        Log.d(TAG,request.getAgentId());
        Call<Request> insertEstimate = NetworkManager.service.addRequest(
                request.getAgentId(),
                fixedLoanAmount.getText().toString(),
                requestId,
                request.getAgentId(),
                request.getItemBank(),
                itemName.getText().toString(),
                request.getInterestRate(),
                request.getInterestRateType(),
                overDueInterestRate1.getText().toString(),
                overDueInterestRate2.getText().toString(),
                overDueInterestRate3.getText().toString(),
                overDueTime1.getText().toString(),
                overDueTime2.getText().toString(),
                overDueTime3.getText().toString(),
                earlyRepaymentType.getText().toString(),
                repaymentType.getText().toString());

        insertEstimate.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                if(response.isSuccessful()){

                    Log.d(TAG, "Estimate insert Success");
                    Request request = response.body();
                    String msg = request.getMsg();
                    Log.d(TAG, msg);

                    finish();
                }
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                    Log.d(TAG, t + "");
            }
        });

    }
    Request request;

    private int requestId;

    protected static Intent getIntent(int id, Context context) {
        Intent intent = new Intent(context, SendDetailedRequest.class);
        intent.putExtra(REQUESTID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_detailed_request);
        ButterKnife.bind(this);

        requestId = (int) getIntent().getSerializableExtra(REQUESTID);
        Log.d(TAG, "RequestID 는" + requestId + "");


    }

    @Override
    protected void onResume() {
        super.onResume();

        Call<SingleRequestRes> getRequest = NetworkManager.service.getRequest(String.valueOf(requestId), "agent1@naver.com");
        getRequest.enqueue(new Callback<SingleRequestRes>() {
            @Override
            public void onResponse(Call<SingleRequestRes> call, Response<SingleRequestRes> response) {
                if (response.isSuccessful()) {
                    SingleRequestRes results = response.body();
                    if (results.getMessage().equals("SUCCESS")) {
                        Request[] requests = results.getRequest();
                        // 배열로 보내져서 배열로 받아왔음
                        request = requests[0];
                        requestLimitAmount.setText(request.getLimiteAmount() + "만원");
                        loanAmount.setText(request.getLoanAmount() + "만원");
                        loanType.setText(request.getLoanType());
                        requestOverDue.setText(request.getOverdueRecord());
                        jobType.setText(request.getJobType());

                        //TODO 시간 해결
                        scheduledTime.setText(request.getScheduledTime() + "");
                        //TODO 요청서에 주거래 은행 없음.
                        mainBank.setText(request.getItemBank());
                    }
                }

            }

            @Override
            public void onFailure(Call<SingleRequestRes> call, Throwable t) {
                Log.e(TAG, t + "");
            }
        });
    }
}

