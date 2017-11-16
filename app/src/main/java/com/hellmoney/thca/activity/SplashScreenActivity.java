package com.hellmoney.thca.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.login.KakaoLoginActivity;
import com.hellmoney.thca.activity.login.KakaoSignupActivity;
import com.hellmoney.thca.activity.login.LocalLoginActivity;
import com.hellmoney.thca.common.util.SharedReferenceUtil;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.module.network.networkData.SingleResData;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        mHandler = new Handler();

        mRunnable = new Runnable(){
            @Override
            public void run() {
                if (!Session.getCurrentSession().checkAndImplicitOpen()) {
                    Intent intent = new Intent(SplashScreenActivity.this, KakaoLoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, KakaoSignupActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        };

        mHandler.postDelayed(mRunnable,SPLASH_TIME);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            mHandler.removeCallbacksAndMessages(mRunnable);
        }

        return super.onKeyDown(keyCode, event);
    }
}
