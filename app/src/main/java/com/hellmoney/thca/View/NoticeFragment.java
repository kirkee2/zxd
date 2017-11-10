package com.hellmoney.thca.view;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.module.network.networkData.Notice;
import com.hellmoney.thca.module.network.networkData.NoticeRes;
import com.hellmoney.thca.module.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeFragment extends Fragment {
    public static final String TAG = NoticeFragment.class.getName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;
    private List<Notice> mNotices;
    private NoticeAdapter mNoticeAdapter;

    @BindView(R.id.notice_recycler_view)
    protected RecyclerView mRecyclerView;

    public NoticeFragment() {
        // Required empty public constructor
    }

    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        ButterKnife.bind(this, view);
        mNotices = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mNoticeAdapter = new NoticeAdapter(mContext, mNotices);
        mRecyclerView.setAdapter(mNoticeAdapter);
        Call<NoticeRes> getNotices = NetworkManager.service.getNotices();
        getNotices.enqueue(new Callback<NoticeRes>() {
            @Override
            public void onResponse(Call<NoticeRes> call, Response<NoticeRes> response) {
                if (response.isSuccessful()) {
                    NoticeRes results = response.body();
                    if(results.getMessage().equals("SUCCESS")) {
                        Log.d(TAG, results.getNotices().toString());
                        mNotices.clear();
                        mNotices.addAll(results.getNotices());
                        mNoticeAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<NoticeRes> call, Throwable t) {
                Log.e(TAG, "NETWORKING_ERROR");
            }
        });
    }

    class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {
        private final String TAG = NoticeAdapter.class.getName();
        private final Context mContext;
        private final List<Notice> mNotices;

        NoticeAdapter(Context context, List<Notice> notices) {
            this.mContext = context;
            this.mNotices = notices;
        }

        @Override
        public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.notice_item, parent, false);
            return new NoticeHolder(view);
        }

        @Override
        public void onBindViewHolder(NoticeHolder holder, int position) {
            final Notice notice = mNotices.get(position);

            holder.mTitleTextView.setText(notice.getTitle());
            switch (notice.getType()) {
                case "교육":
                    holder.mTypeImageView.setImageResource(R.mipmap.ic_education);
                    break;
                case "점검":
                    holder.mTypeImageView.setImageResource(R.mipmap.ic_check);
                    break;
                case "경고":
                    holder.mTypeImageView.setImageResource(R.mipmap.ic_warning);
                    break;
                default:
                    holder.mTypeImageView.setImageResource(R.mipmap.ic_education);
                    break;
            }
            holder.itemView.setTag(notice.getNoticeId());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, NoticeDetailActivity.class);
                    intent.putExtra(NoticeDetailActivity.EXTRA_NOTICE_ID, notice.getNoticeId());
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNotices.size();
        }

        class NoticeHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.notice_title_text_view)
            protected TextView mTitleTextView;
            @BindView(R.id.notice_type_image_view)
            protected ImageView mTypeImageView;

            NoticeHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
