package com.hellmoney.thca.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.model.NoticeDetailRes;
import com.hellmoney.thca.model.NoticeRes;
import com.hellmoney.thca.network.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDetailActivity extends AppCompatActivity {
    private static final String TAG = NoticeDetailActivity.class.getName();

    public static final String EXTRA_NOTICE_ID = "notice-id";

    @BindView(R.id.text_test)
    protected TextView textViewTest;

    private int mNoticeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        ButterKnife.bind(this);

        mNoticeId = getIntent().getIntExtra(EXTRA_NOTICE_ID, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<NoticeDetailRes> getNotice = NetworkManager.service.getNotice(mNoticeId);
        getNotice.enqueue(new Callback<NoticeDetailRes>() {
            @Override
            public void onResponse(Call<NoticeDetailRes> call, Response<NoticeDetailRes> response) {
                if(response.isSuccessful()) {
                    NoticeDetailRes results = response.body();
                    if(results.getMessage().equals("SUCCESS")) {
                        textViewTest.setText(results.getNotice().toString());
                    } else {
                        Log.e(TAG, results.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<NoticeDetailRes> call, Throwable t) {
                Log.e(TAG, "NETWORKING_ERROR");
            }
        });
    }
}
