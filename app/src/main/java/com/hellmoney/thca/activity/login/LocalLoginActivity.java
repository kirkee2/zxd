package com.hellmoney.thca.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.common.CommonBaseAcitivity;
import com.hellmoney.thca.common.util.SharedReferenceUtil;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.module.network.networkData.Request;
import com.hellmoney.thca.module.network.networkData.SingleRes;
import com.hellmoney.thca.activity.MainActivity;
import com.hellmoney.thca.module.network.networkData.SingleResData;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalLoginActivity extends CommonBaseAcitivity {
    private EditText name;
    private EditText nickname;
    private EditText greeting;
    private EditText companyName;
    private EditText registerNumber;
    private EditText region1;
    private EditText region2;
    private EditText phoneNumber;
    private EditText authCheck;
    private Button signup;
    private Button requestAuth;
    private Button assign;
    private ProgressBar progressBar;

    private int authNumber;
    private boolean authNumberChecked = false;

    private Call<SingleRes> signupLocal;
    private boolean isSignupLocalSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_login);

        name = (EditText) findViewById(R.id.name);
        nickname = (EditText) findViewById(R.id.nickname);
        greeting = (EditText) findViewById(R.id.greeting);
        companyName = (EditText) findViewById(R.id.companyName);
        registerNumber = (EditText) findViewById(R.id.registerNumber);
        region1 = (EditText) findViewById(R.id.region1);
        region2 = (EditText) findViewById(R.id.region2);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        authCheck = (EditText) findViewById(R.id.authCheck);

        signup = (Button) findViewById(R.id.signup);
        requestAuth = (Button) findViewById(R.id.requestAuth);
        assign = (Button) findViewById(R.id.assign);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        phoneNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                authNumberChecked = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        requestAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSignupLocalSuccess = false;
                enableProgressBar(progressBar);

                final Call<SingleResData> phoneAuth = NetworkManager.service.phoneAuth(phoneNumber.getText().toString());

                phoneAuth.enqueue(new Callback<SingleResData>() {
                    @Override
                    public void onResponse(Call<SingleResData> call, Response<SingleResData> response) {
                        if (response.isSuccessful()) {
                            SingleResData singleResData = response.body();
                            String msg = singleResData.getMsg();
                            int data = singleResData.getData();

                            if ("SUCCESS".equals(msg)) {
                                authNumber = data;
                                isSignupLocalSuccess = true;
                                networkDone();
                            } else {
                                Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("????", " ??? " + response.body() + " ??? " + response.message() + " ??? " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<SingleResData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(authCheck.getText().toString()) == authNumber) {
                    Toast.makeText(getApplicationContext(), "안증되었습니다.", Toast.LENGTH_SHORT).show();
                    authNumberChecked = true;
                } else {
                    Toast.makeText(getApplicationContext(), "인증번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    authNumberChecked = false;
                }
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText())) {
                    Toast.makeText(getApplicationContext(), "이름을 적어주세요.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(nickname.getText())) {
                    Toast.makeText(getApplicationContext(), "닉네임을 적어주세요.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(greeting.getText())) {
                    Toast.makeText(getApplicationContext(), "인시말을 적어주세요.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(companyName.getText())) {
                    Toast.makeText(getApplicationContext(), "회사명을 적어주세요.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(registerNumber.getText())) {
                    Toast.makeText(getApplicationContext(), "등록 번호를 적어주세요.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(region1.getText())) {
                    Toast.makeText(getApplicationContext(), "도를 적어주세요.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(region2.getText())) {
                    Toast.makeText(getApplicationContext(), "시를 적어주세요.", Toast.LENGTH_SHORT).show();
                } else if (!authNumberChecked) {
                    Toast.makeText(getApplicationContext(), "전화번호를 인증해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    signupLocal = NetworkManager.service.signupLocal(
                            name.getText().toString(),
                            nickname.getText().toString(),
                            greeting.getText().toString(),
                            1,
                            companyName.getText().toString(),
                            Integer.parseInt(registerNumber.getText().toString()),
                            region1.getText().toString(),
                            region2.getText().toString(),
                            1,
                            phoneNumber.getText().toString(),
                            kakaoId);

                    signupLocal.enqueue(new Callback<SingleRes>() {
                        @Override
                        public void onResponse(Call<SingleRes> call, Response<SingleRes> response) {
                            if (response.isSuccessful()) {
                                SingleRes singleRes = response.body();

                                if (singleRes != null) {
                                    String msg = singleRes.getMsg();

                                    if ("SUCCESS".equals(msg)) {
                                        Toast.makeText(getApplicationContext(), "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LocalLoginActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SingleRes> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (signupLocal != null) {
            signupLocal.cancel();
        }
    }

    @Override
    protected void networkDone() {
        if (isSignupLocalSuccess) {
            disableProgressBar(progressBar);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
