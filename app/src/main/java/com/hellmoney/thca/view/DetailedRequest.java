package com.hellmoney.thca.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hellmoney.thca.R;

/**
 * Created by len on 2017. 6. 14..
 */

class DetailedRequest extends AppCompatActivity {

    private static final String REQUESTID = "requestId";
    private static final String TAG = DetailedRequest.class.getName();

    protected static Intent getIntent(int id, Context context) {
        Intent intent = new Intent(context, DetailedRequest.class);
        intent.putExtra(REQUESTID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_request);

        String requestId = (String) getIntent().getSerializableExtra(REQUESTID);
        Log.d(TAG, requestId);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}
