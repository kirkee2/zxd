package com.hellmoney.thca.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hellmoney.thca.R;

public class ItemDetailActivity extends AppCompatActivity {
    private static final String TAG = ItemDetailActivity.class.getName();

    public static final String EXTRA_ITEM_ID = "extra-item-id";

    private int mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mItemId = getIntent().getIntExtra(EXTRA_ITEM_ID, 1);

        Toast.makeText(this, String.valueOf(mItemId), Toast.LENGTH_LONG).show();
    }
}
