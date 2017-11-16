/**
 * Copyright 2014-2015 Kakao Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hellmoney.thca.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.MainActivity;
import com.hellmoney.thca.activity.SplashScreenActivity;
import com.hellmoney.thca.common.util.SharedReferenceUtil;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.module.network.networkData.SingleEstimateRes;
import com.hellmoney.thca.module.network.networkData.SingleRequestRes;
import com.hellmoney.thca.module.network.networkData.SingleRes;
import com.hellmoney.thca.module.network.networkData.SingleResData;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 유효한 세션이 있다는 검증 후
 * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
 */
public class KakaoSignupActivity extends AppCompatActivity {
    private Intent failIntent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        failIntent = new Intent(KakaoSignupActivity.this, KakaoLoginActivity.class);
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
                    Intent intent = new Intent(KakaoSignupActivity.this, KakaoLoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                startActivity(failIntent);
            }

            @Override
            public void onSuccess(final UserProfile userProfile) {
                final Call<SingleResData> isLocalLogin = NetworkManager.service.isLocalLogin(String.valueOf(userProfile.getId()));

                isLocalLogin.enqueue(new Callback<SingleResData>() {
                    @Override
                    public void onResponse(Call<SingleResData> call, Response<SingleResData> response) {
                        Intent successIntent;

                        if (response.isSuccessful()) {
                            SingleResData singleRes = response.body();
                            String msg = singleRes.getMsg();

                            if ("SUCCESS".equals(msg)) {
                                successIntent = new Intent(KakaoSignupActivity.this, MainActivity.class);
                                successIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(successIntent);
                            } else {
                                successIntent = new Intent(KakaoSignupActivity.this, KakaoLoginActivity.class);
                                successIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(successIntent);
                            }

                        }else{
                            Log.e("????"," ??? " + response.body() + " ??? " + response.message() + " ??? " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<SingleResData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNotSignedUp() {
                startActivity(failIntent);
            }
        });
    }
}
