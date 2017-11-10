package com.hellmoney.thca.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.common.CommonBaseAcitivity;
import com.hellmoney.thca.module.network.networkData.Item;
import com.hellmoney.thca.module.network.networkData.ItemDetailRes;
import com.hellmoney.thca.module.network.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends CommonBaseAcitivity {
    private static final String TAG = ItemDetailActivity.class.getName();

    public static final String EXTRA_ITEM_ID = "extra-item-id";

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.item_name_text_view)
    protected TextView mItemNameTextView;
    @BindView(R.id.item_interest_rate_type_text_view)
    protected TextView mItemInterestRateTypeTextView;
    @BindView(R.id.item_repayment_type_text_view)
    protected TextView mItemRepaymentTypeTextView;
    @BindView(R.id.item_min_interest_rate_text_view)
    protected TextView mItemMinInterestRateTextView;
    @BindView(R.id.item_max_interest_rate_text_view)
    protected TextView mItemMaxInterestRateTextView;
    @BindView(R.id.item_early_repayment_fee_text_view)
    protected TextView mItemEarlyRepaymentFeeTextView;
    @BindView(R.id.item_overdue_time_1_text_view)
    protected TextView mItemOverdueTime1TextView;
    @BindView(R.id.item_overdue_interest_rate_1_text_view)
    protected TextView mItemOverdueInterestRate1TextView;
    @BindView(R.id.item_overdue_time_2_text_view)
    protected TextView mItemOverdueTime2TextView;
    @BindView(R.id.item_overdue_interest_rate_2_text_view)
    protected TextView mItemOverdueInterestRate2TextView;
    @BindView(R.id.item_overdue_time_3_text_view)
    protected TextView mItemOverdueTime3TextView;
    @BindView(R.id.item_overdue_interest_rate_3_text_view)
    protected TextView mItemOverdueInterestRate3TextView;

    private int mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        mItemId = getIntent().getIntExtra(EXTRA_ITEM_ID, 1);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("대출상품");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<ItemDetailRes> getItem = NetworkManager.service.getItem(mItemId);
        getItem.enqueue(new Callback<ItemDetailRes>() {
            @Override
            public void onResponse(Call<ItemDetailRes> call, Response<ItemDetailRes> response) {
                if (response.isSuccessful()) {
                    ItemDetailRes results = response.body();
                    if (results.getMessage().equals("SUCCESS")) {
                        Item item = results.getItem();
                        mItemNameTextView.setText(item.getItemName());
                        mItemInterestRateTypeTextView.setText(item.getInterestRateType());
                        mItemRepaymentTypeTextView.setText(item.getRepaymentType());
                        mItemMinInterestRateTextView.setText(item.getMinInterestRate().toString() + "%");
                        mItemMaxInterestRateTextView.setText(item.getMaxInterestRate().toString() + "%");
                        mItemEarlyRepaymentFeeTextView.setText(item.getEarlyRepaymentFee().toString() + "%");
                        mItemOverdueTime1TextView.setText(item.getOverdueTime1());
                        mItemOverdueInterestRate1TextView.setText(item.getOverdueInterestRate1().toString() + "%");
                        mItemOverdueTime2TextView.setText(item.getOverdueTime2());
                        mItemOverdueInterestRate2TextView.setText(item.getOverdueInterestRate2().toString() + "%");
                        mItemOverdueTime3TextView.setText(item.getOverdueTime3());
                        mItemOverdueInterestRate3TextView.setText(item.getOverdueInterestRate3().toString() + "%");
                    } else {
                        Log.e(TAG, results.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ItemDetailRes> call, Throwable t) {
                Log.e(TAG, "NETWORKING_ERROR");
            }
        });
    }

    @OnClick({R.id.button_delete, R.id.button_update})
    protected void onClick(View v) {
        Snackbar.make(v, "기능이 추가될 예정입니다.", Snackbar.LENGTH_SHORT).show();
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
