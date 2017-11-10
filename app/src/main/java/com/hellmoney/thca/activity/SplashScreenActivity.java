package com.hellmoney.thca.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.login.KakaoLoginActivity;
import com.hellmoney.thca.activity.login.LocalLoginActivity;
import com.hellmoney.thca.common.util.SharedReferenceUtil;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

public class SplashScreenActivity extends AppCompatActivity {
    private Handler mHandler;
    private Runnable mRunnable;
    private final static long SPLASH_TIME = 2000;

    private Intent failIntent;
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        failIntent = new Intent(SplashScreenActivity.this, KakaoLoginActivity.class);
        failIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mHandler = new Handler();

        mRunnable = new Runnable(){
            @Override
            public void run() {
                requestMe();
            }
        };

        mHandler.postDelayed(mRunnable,SPLASH_TIME);
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
                    Intent intent = new Intent(SplashScreenActivity.this, KakaoLoginActivity.class);
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
                Intent successIntent;

                if(SharedReferenceUtil.getLocalLogin() == false){
                    successIntent = new Intent(SplashScreenActivity.this, LocalLoginActivity.class);
                    successIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(successIntent);
                }else{
                    successIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    successIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(successIntent);
                }
            }

            @Override
            public void onNotSignedUp() {
                startActivity(failIntent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            mHandler.removeCallbacksAndMessages(mRunnable);
        }

        return super.onKeyDown(keyCode, event);
    }
}
