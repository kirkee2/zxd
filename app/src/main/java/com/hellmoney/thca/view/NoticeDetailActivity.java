package com.hellmoney.thca.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.common.CommonBaseAcitivity;
import com.hellmoney.thca.module.network.networkData.Notice;
import com.hellmoney.thca.module.network.networkData.NoticeDetailRes;
import com.hellmoney.thca.module.network.NetworkManager;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeDetailActivity extends AppCompatActivity {
    private static final String TAG = NoticeDetailActivity.class.getName();

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    public static final String EXTRA_NOTICE_ID = "extra-notice-id";

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.notice_title_text_view)
    protected TextView mTitleTextView;
    @BindView(R.id.notice_type_image_view)
    protected ImageView mTypeImageView;
    @BindView(R.id.notice_content_text_view)
    protected TextView mContentTextView;
//    @BindView(R.id.notice_register_time_text_view)
//    protected TextView mRegisterTimeTextView;

    private int mNoticeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        ButterKnife.bind(this);

        mNoticeId = getIntent().getIntExtra(EXTRA_NOTICE_ID, 1);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("공지사항");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
                        Notice notice = results.getNotice();
                        mTitleTextView.setText(notice.getTitle());
                        switch (notice.getType()) {
                            case "교육":
                                mTypeImageView.setImageResource(R.mipmap.ic_education);
                                break;
                            case "점검":
                                mTypeImageView.setImageResource(R.mipmap.ic_check);
                                break;
                            case "경고":
                                mTypeImageView.setImageResource(R.mipmap.ic_warning);
                                break;
                            default:
                                mTypeImageView.setImageResource(R.mipmap.ic_education);
                                break;
                        }
                        mContentTextView.setText(notice.getContent().replace("\\n", "\n"));
//                        mRegisterTimeTextView.setText(dateFormat.format(notice.getRegisterTime()));
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
