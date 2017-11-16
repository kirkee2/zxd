package com.hellmoney.thca.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.MainActivity;
import com.hellmoney.thca.activity.login.KakaoLoginActivity;
import com.hellmoney.thca.activity.login.KakaoSignupActivity;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

public abstract class CommonBaseAcitivity extends AppCompatActivity {
    private Intent failIntent;
    protected String kakaoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        failIntent = new Intent(CommonBaseAcitivity.this, KakaoLoginActivity.class);
        failIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        isSessionOpen();
    }

    private void isSessionOpen() {
        if (!Session.getCurrentSession().checkAndImplicitOpen()) {
            startActivity(failIntent);
        }else{
            kakaoId = String.valueOf(UserProfile.loadFromCache().getId());
        }
    }

    protected void enableProgressBar(ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void disableProgressBar(ProgressBar progressBar){
        progressBar.setVisibility(View.GONE);
    }

    protected abstract void networkDone();
}
