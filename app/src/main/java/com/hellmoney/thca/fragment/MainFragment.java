package com.hellmoney.thca.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hellmoney.thca.R;
import com.hellmoney.thca.activity.MainActivity;
import com.hellmoney.thca.activity.login.KakaoLoginActivity;
import com.hellmoney.thca.activity.login.KakaoSignupActivity;
import com.hellmoney.thca.common.CommonBaseFragment;
import com.hellmoney.thca.module.network.networkData.Request;
import com.hellmoney.thca.module.network.networkData.RequestRes;
import com.hellmoney.thca.module.network.NetworkManager;
import com.hellmoney.thca.module.network.networkData.SingleResActivity;
import com.hellmoney.thca.module.network.networkData.SingleResCount;
import com.hellmoney.thca.recyclerView.mainRecyclerView.MainRecyclerViewAdapter;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    public static final String TAG = MainFragment.class.getName();

    private Context mContext;
    private String kakaoId;

    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter mMainContentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Request> mRequests;

    Call<RequestRes> getRequests;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mContext = getActivity();
        mRequests = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        requestMe();
    }

    private void requestMe() {
        final Intent failIntent = new Intent(getActivity(), KakaoLoginActivity.class);
        failIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    Toast.makeText(mContext, getString(R.string.error_message_for_service_unavailable), Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {
                    Intent intent = new Intent(getActivity(), KakaoSignupActivity.class);
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
                kakaoId = String.valueOf(userProfile.getId());

                mMainContentAdapter = new MainRecyclerViewAdapter(mRequests,kakaoId, mContext);
                recyclerView.setAdapter(mMainContentAdapter);

                getRequests = NetworkManager.service.getRequests(kakaoId);

                getRequests.enqueue(new Callback<RequestRes>() {
                    @Override
                    public void onResponse(Call<RequestRes> call, Response<RequestRes> response) {
                        if (response.isSuccessful()) {
                            RequestRes requestRes = response.body();

                            if("SUCCESS".equals(requestRes.getMessage())) {
                                mRequests.clear();
                                mRequests.addAll(requestRes.getRequests());
                                mMainContentAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestRes> call, Throwable t) {
                    }
                });
            }

            @Override
            public void onNotSignedUp() {
                startActivity(failIntent);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        if(getRequests != null){
            getRequests.cancel();
        }
    }
}
