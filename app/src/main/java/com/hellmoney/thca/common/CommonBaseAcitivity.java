package com.hellmoney.thca.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.login.KakaoLoginActivity;
import com.hellmoney.thca.activity.login.KakaoSignupActivity;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

public class CommonBaseAcitivity extends AppCompatActivity {
    private Intent failIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        failIntent = new Intent(CommonBaseAcitivity.this, KakaoLoginActivity.class);
        failIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        requestMe();
    }

    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_message_for_service_unavailable), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Intent intent = new Intent(CommonBaseAcitivity.this, KakaoSignupActivity.class);
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
            }

            @Override
            public void onNotSignedUp() {
                startActivity(failIntent);
            }
        });

    }
}
