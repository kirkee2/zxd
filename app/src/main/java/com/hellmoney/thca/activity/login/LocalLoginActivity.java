package com.hellmoney.thca.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.TempAgent;
import com.hellmoney.thca.common.util.SharedReferenceUtil;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.module.network.networkData.Request;
import com.hellmoney.thca.module.network.networkData.SingleRes;
import com.hellmoney.thca.activity.MainActivity;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalLoginActivity extends AppCompatActivity {
    private EditText name;
    private EditText nickname;
    private EditText greeting;
    private EditText companyName;
    private EditText registerNumber;
    private EditText region1;
    private EditText region2;
    private Button signup;

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

        signup = (Button) findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText())){
                    Toast.makeText(getApplicationContext(),"이름을 적어주세요.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(nickname.getText())){
                    Toast.makeText(getApplicationContext(),"닉네임을 적어주세요.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(greeting.getText())){
                    Toast.makeText(getApplicationContext(),"인시말을 적어주세요.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(companyName.getText())){
                    Toast.makeText(getApplicationContext(),"회사명을 적어주세요.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(registerNumber.getText())){
                    Toast.makeText(getApplicationContext(),"등록 번호를 적어주세요.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(region1.getText())){
                    Toast.makeText(getApplicationContext(),"도를 적어주세요.",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(region2.getText())){
                    Toast.makeText(getApplicationContext(),"시를 적어주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    final Call<SingleRes> signupLocal = NetworkManager.service.signupLocal(
                            name.getText().toString(),
                            nickname.getText().toString(),
                            greeting.getText().toString(),
                            1,
                            companyName.getText().toString(),
                            Integer.parseInt(registerNumber.getText().toString()),
                            region1.getText().toString(),
                            region2.getText().toString());

                    signupLocal.enqueue(new Callback<SingleRes>() {
                        @Override
                        public void onResponse(Call<SingleRes> call, Response<SingleRes> response) {

                            if (response.isSuccessful()) {
                                SingleRes singleRes = response.body();
                                String msg = singleRes.getMsg();

                                if ("SUCCESS".equals(msg)) {
                                    Toast.makeText(getApplicationContext(), "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                    SharedReferenceUtil.setLocalLogin();
                                    Intent intent = new Intent(LocalLoginActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "네트워크에 문제가 있습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Log.e("????"," ??? " + response.body() + " ??? " + response.message() + " ??? " + response.code());
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
    protected void onDestroy() {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
            }
        });

        super.onDestroy();

    }
}
