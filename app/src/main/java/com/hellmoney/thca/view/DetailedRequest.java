package com.hellmoney.thca.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.hellmoney.thca.R;
import com.hellmoney.thca.TempAgent;
import com.hellmoney.thca.model.LikeRes;
import com.hellmoney.thca.model.Request;
import com.hellmoney.thca.model.RequestRes;
import com.hellmoney.thca.model.SingleRequestRes;
import com.hellmoney.thca.network.NetworkManager;
import com.hellmoney.thca.util.StringUtil;
import com.hellmoney.thca.util.TimeUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailedRequest extends AppCompatActivity {
    private final static int REQUEST_EXIT = 1342;

    private static final String REQUESTID = "requestId";
    private static final String TAG = DetailedRequest.class.getName();

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.loanTypeImageView)
    ImageView loanTypeImage;

    @BindView(R.id.finalQuotationCount)
    TextView finalQuotationCount;

    @BindView(R.id.averageInterestRate)
    TextView averageInterestRate;

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

    @BindView(R.id.barChart)
    BarChart mBarChart;

    @BindView(R.id.calledAddEstimate)
    Button mButton;

    @BindView(R.id.linearChart)
    LinearLayout mLinearLayout;

    @BindView(R.id.bottomLinear)
    LinearLayout mLinearLayout2;

    @BindView(R.id.empty_data_layout)
    RelativeLayout mEmptyDataLayout;

    @BindView(R.id.star)
    ToggleButton mToggleButton;

    @BindView(R.id.endTime)
    TextView endTime;


    ArrayList<Request> loanRates;
    private int requestId;
    Context mContext;

    protected static Intent getIntent(int id, Context context) {
        Intent intent = new Intent(context, DetailedRequest.class);
        intent.putExtra(REQUESTID, id);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_request);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("견적요청 상세");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        requestId = (int) getIntent().getSerializableExtra(REQUESTID);
        Log.d(TAG, "RequestID 는" + requestId + "");
        mContext = getApplicationContext();
    }

    @OnClick(R.id.calledAddEstimate)
    void onClick() {
        startActivityForResult(SendDetailedRequest.getIntent(requestId, mContext), REQUEST_EXIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_EXIT) {
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        loanRates = new ArrayList<>();

        Call<RequestRes> getLoanRate = NetworkManager.service.getLoanRate(String.valueOf(requestId));
        getLoanRate.enqueue(new Callback<RequestRes>() {
            @Override
            public void onResponse(Call<RequestRes> call, Response<RequestRes> response) {
                if (response.isSuccessful()) {
                    RequestRes requests = response.body();
                    if (requests.getMessage().equals("SUCCESS")) {


                        ArrayList<BarEntry> entries = new ArrayList<>();
                        ArrayList<BarEntry> entriesMin = new ArrayList<>();

                        float min = 100;
                        int minIndex = 0;
                        loanRates.clear();

                        loanRates.addAll(requests.getRequests());


                        int length = loanRates.size();
                        if (length == 0) {

                        } else {
                            Double sum = 0.0;

                            int i = 0;
                            for (Request loanLate : loanRates) {
                                Float rate = Float.parseFloat(loanLate.getInterestRate());
                                sum += rate;
                                entries.add(new BarEntry(i * 0.5F, rate));

                                if (rate < min) {
                                    min = rate;
                                    minIndex = i;
                                }
                                i++;
                            }

                            entriesMin.add(entries.get(minIndex));
                            entries.remove(minIndex);


                            Description d = new Description();
                            d.setText("");

                            BarDataSet dataSet = new BarDataSet(entries, "금리");
                            BarDataSet dataSetMin = new BarDataSet(entriesMin, "최저 금리");

                            dataSet.setColor(0xFF00BFA5);
                            dataSet.setHighlightEnabled(false);
                            dataSet.setValueTextSize(10);

                            dataSetMin.setColor(0xFFFF4081);
                            dataSetMin.setHighlightEnabled(false);
                            dataSetMin.setValueTextSize(10);

                            BarData data = new BarData(dataSet);
                            data.addDataSet(dataSetMin);

                            data.setBarWidth(0.15F);


                            mBarChart.setData(data);
                            mBarChart.getAxisRight().setEnabled(false);
                            mBarChart.getXAxis().setEnabled(false);
                            mBarChart.setDescription(d);
                            mBarChart.setEnabled(false);
                            mBarChart.animateY(500);
                            mBarChart.setDoubleTapToZoomEnabled(false);
                            mBarChart.setScaleEnabled(false);

                            mBarChart.invalidate();

                            Double average = (sum / length);

                            String pattern = "#####.##";
                            DecimalFormat dformat = new DecimalFormat(pattern);

                            averageInterestRate.setText(dformat.format(average) + "%");
                        }
                    } else {
                        mEmptyDataLayout.setVisibility(View.VISIBLE);
                        mLinearLayout.setVisibility(View.GONE);
                        mLinearLayout2.setVisibility(View.GONE);
                        mBarChart.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestRes> call, Throwable t) {
                Log.d(TAG, "평균 OR 그래프 에러" + t);
            }
        });


        Call<SingleRequestRes> getRequest = NetworkManager.service.getRequest(String.valueOf(requestId), TempAgent.AGENT_ID);
        getRequest.enqueue(new Callback<SingleRequestRes>() {
            @Override
            public void onResponse(Call<SingleRequestRes> call, Response<SingleRequestRes> response) {
                if (response.isSuccessful()) {
                    SingleRequestRes results = response.body();
                    if (results.getMessage().equals("SUCCESS")) {
                        Request request = results.getRequest();

                        if (request.getEstiamteCount() > 10) {
                            mButton.setBackgroundColor(Color.parseColor("#cccccc"));
                            mButton.setClickable(false);
                        }

                        if (request.getAgentAlreadyEstimated() == 1) {
                            mButton.setText("이미 작성한 요청서입니다.");
                            mButton.setBackgroundColor(Color.parseColor("#cccccc"));
                            mButton.setClickable(false);
                        }

                        finalQuotationCount.setText(String.valueOf(request.getEstiamteCount()));

                        switch (request.getFavorite()) {
                            case 1:
                                mToggleButton.setChecked(true);
                                break;
                            case 0:
                                mToggleButton.setChecked(false);
                                break;
                        }
                        switch (request.getLoanType()) {

                            case "주택담보대출":
                                loanTypeImage.setImageResource(R.drawable.dambuu);
                                break;

                            case "전세자금대출":
                                loanTypeImage.setImageResource(R.drawable.sunjaa);
                                break;
                        }
                        requestAddress.setText(request.getTotalAddress());
                        requestAddressApt.setText(request.getAptName());
                        requestAddressSize.setText(request.getSize());

                        String pattern = "#####";
                        DecimalFormat decimalFormat = new DecimalFormat(pattern);

                        requestLimitAmount.setText(StringUtil.toNumFormat(Integer.parseInt(decimalFormat.format(request.getLimiteAmount()))) + " 만원");
                        loanAmount.setText(StringUtil.toNumFormat(Integer.parseInt(request.getLoanAmount())) + " 만원");
                        loanType.setText(request.getLoanType());
                        jobType.setText(request.getJobType());
                        scheduledTime.setText(TimeUtil.dateFormat.format(request.getScheduledTime()));

                        int time = TimeUtil.timeLeftSecondParsing(request.getEndTime());

                        int hour = time / 3600;
                        int temp = time % 3600;
                        int minute = temp / 60;
                        int second = temp % 60;


                        if (time > 0) {
                            endTime.setText("마감 " + TimeUtil.formatNumber2(hour) + " : " + TimeUtil.formatNumber2(minute) + " 전");
                        } else {
                            endTime.setText("견적 마감");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleRequestRes> call, Throwable t) {
                Log.e(TAG, t + "");
            }
        });


        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((ToggleButton) v).isChecked();
                if (isChecked) {
                    Call<LikeRes> insertFavorite = NetworkManager.service.like(TempAgent.AGENT_ID, requestId);
                    insertFavorite.enqueue(new Callback<LikeRes>() {
                        @Override
                        public void onResponse(Call<LikeRes> call, Response<LikeRes> response) {
                            if (response.isSuccessful()) {
//                                Toast.makeText(mContext, "좋아요!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LikeRes> call, Throwable t) {
                            Log.e(TAG, "onFailure: ");
                        }
                    });
                } else {
                    Call<LikeRes> deleteFavorite = NetworkManager.service.unlike(TempAgent.AGENT_ID, requestId);
                    deleteFavorite.enqueue(new Callback<LikeRes>() {
                        @Override
                        public void onResponse(Call<LikeRes> call, Response<LikeRes> response) {
                            if (response.isSuccessful()) {
//                                Toast.makeText(mContext, "좋아요를 취소합니다.", Toast.LENGTH_SHORT).show();
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

}

