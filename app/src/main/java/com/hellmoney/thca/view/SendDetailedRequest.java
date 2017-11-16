package com.hellmoney.thca.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.MainActivity;
import com.hellmoney.thca.activity.login.KakaoLoginActivity;
import com.hellmoney.thca.activity.login.KakaoSignupActivity;
import com.hellmoney.thca.common.CommonBaseAcitivity;
import com.hellmoney.thca.fragment.MainFragment;
import com.hellmoney.thca.module.network.networkData.Request;
import com.hellmoney.thca.module.network.networkData.SingleRequestRes;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.common.util.StringUtil;
import com.hellmoney.thca.common.util.TimeUtil;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SendDetailedRequest extends AppCompatActivity {

    private static final String REQUESTID = "requestId";
    private static final String TAG = SendDetailedRequest.class.getName();
    private String kakaoId;

    isPost mIsPost;

    private Context mContext;

    public interface isPost {
        void isPostSuccess(boolean on);
    }

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

    @BindView(R.id.interestLoanType)
    TextView interestLoanType;

    @BindView(R.id.jobType)
    TextView jobType;

    @BindView(R.id.scheduledTime)
    TextView scheduledTime;

//    //저장된 아이템 불러오는 버튼
//    @BindView(R.id.callItem)
//    TextView callItem;

    @BindView(R.id.fixedLoanAmount)
    EditText fixedLoanAmount;

    @BindView(R.id.itemName)
    EditText itemName;

    @BindView(R.id.loanRate)
    EditText loanRate;

    @BindView(R.id.repaymentType)
    EditText repaymentType;

//    @BindView(R.id.earlyRepaymentType)
//    EditText earlyRepaymentType;
//
//    @BindView(R.id.overDueTime1)
//    EditText overDueTime1;
//
//    @BindView(R.id.overDueInterestRate1)
//    EditText overDueInterestRate1;
//
//    @BindView(R.id.overDueTime2)
//    EditText overDueTime2;
//
//    @BindView(R.id.overDueInterestRate2)
//    EditText overDueInterestRate2;
//
//    @BindView(R.id.overDueTime3)
//    EditText overDueTime3;
//
//    @BindView(R.id.overDueInterestRate3)
//    EditText overDueInterestRate3;

    @BindView(R.id.sendEstimate)
    Button sendEstimateButton;


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

        mContext = this;

        requestId = (int) getIntent().getSerializableExtra(REQUESTID);
        Log.d(TAG, "RequestID 는" + requestId + "");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("견적서 작성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        requestMe();
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

    @OnClick(R.id.sendEstimate)
    public void submit(View view) {
        final Call<Request> insertEstimate = NetworkManager.service.addRequest(
                kakaoId,
                fixedLoanAmount.getText().toString(),
                requestId,
                kakaoId,
                request.getItemBank(),
                itemName.getText().toString(),
                loanRate.getText().toString(),
                request.getInterestRateType(),
                repaymentType.getText().toString());

//        overDueInterestRate1.getText().toString(),
//                overDueInterestRate2.getText().toString(),
//                overDueInterestRate3.getText().toString(),
//                overDueTime1.getText().toString(),
//                overDueTime2.getText().toString(),
//                overDueTime3.getText().toString(),
//                earlyRepaymentType.getText().toString()
        insertEstimate.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {

                if (response.isSuccessful()) {

                    Log.d(TAG, "Estimate insert Success");
                    Request request = response.body();
                    String msg = request.getMsg();
                    Log.d(TAG, msg);


                    //깔끔하지 않음..
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    intent.putExtra("on", true);
//
//                    startActivity(intent);
//                    finish();

                    Toast.makeText(mContext, "견적서가 작성되었습니다.", Toast.LENGTH_SHORT).show();

                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                Log.d(TAG, t + "");
            }
        });
    }

    private void requestMe() {
        final Intent failIntent = new Intent(SendDetailedRequest.this, KakaoLoginActivity.class);
        failIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);


        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_message_for_service_unavailable), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Intent intent = new Intent(SendDetailedRequest.this, KakaoSignupActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                startActivity(failIntent);
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                kakaoId = String.valueOf(userProfile.getId());

                Call<SingleRequestRes> getRequest = NetworkManager.service.getRequest(String.valueOf(requestId), kakaoId);
                getRequest.enqueue(new Callback<SingleRequestRes>() {
                    @Override
                    public void onResponse(Call<SingleRequestRes> call, Response<SingleRequestRes> response) {
                        if (response.isSuccessful()) {
                            SingleRequestRes results = response.body();
                            if (results.getMessage().equals("SUCCESS")) {
                                request = results.getRequest();

                                requestAddress.setText(request.getTotalAddress());
                                requestAddressApt.setText(request.getAptName());
                                requestAddressSize.setText(request.getSize());


//                        Toast.makeText(SendDetailedRequest.this, TempAgent.AGENT_ID + "", Toast.LENGTH_SHORT).show();
                                requestLimitAmount.setText(StringUtil.toNumFormat(request.getLimiteAmount()) + " 만원");
                                loanAmount.setText(StringUtil.toNumFormat(Integer.parseInt(request.getLoanAmount())) + "만원");
                                loanType.setText(request.getLoanType());
                                interestLoanType.setText(request.getInterestRateType());
                                jobType.setText(request.getJobType());
                                scheduledTime.setText(TimeUtil.dateFormat.format(request.getScheduledTime()));
                                switch (request.getLoanType()) {
                                    case "주택담보대출":
                                        loanTypeImage.setImageResource(R.drawable.dambuu);
                                        break;
                                    case "전세자금대출":
                                        loanTypeImage.setImageResource(R.drawable.sunjaa);
                                        break;
                                }
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<SingleRequestRes> call, Throwable t) {
                        Log.e(TAG, t + "");
                    }
                });
            }

            @Override
            public void onNotSignedUp() {
                startActivity(failIntent);
            }
        });
    }
}

