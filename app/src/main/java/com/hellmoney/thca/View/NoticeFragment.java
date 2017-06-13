package com.hellmoney.thca.View;

import android.content.Context;
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
import com.hellmoney.thca.model.Notice;
import com.hellmoney.thca.model.NoticeRes;
import com.hellmoney.thca.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeFragment extends Fragment {

    private final String TAG = NoticeFragment.class.getName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Context mContext;

    private List<Notice> notices;
    private NoticeAdapter mNoticeAdapter;

    RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;

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
        notices = new ArrayList<>();
        mNoticeAdapter = new NoticeAdapter(mContext, notices);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.notice_recyclerView);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mNoticeAdapter);
        Call<NoticeRes> getNotices = NetworkManager.service.getNotices();
        getNotices.enqueue(new Callback<NoticeRes>() {
            @Override
            public void onResponse(Call<NoticeRes> call, Response<NoticeRes> response) {
                if (response.isSuccessful()) {
                    NoticeRes results = response.body();
//                    Log.d("Network", results.getNotices().toString());
                    notices.clear();
                    notices.addAll(results.getNotices());
                    mNoticeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NoticeRes> call, Throwable t) {
                Log.d(TAG, "ERROR");
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * NoticeAdapter 부분
     */
    public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {
        private final Context context;
        private final List<Notice> notices;

        public NoticeAdapter(Context context, List<Notice> notices) {
            this.context = context;
            this.notices = notices;
        }

        @Override
        public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.notice_item, parent, false);
            return new NoticeHolder(view);
        }

        @Override
        public void onBindViewHolder(NoticeHolder holder, int position) {
            Notice notice = notices.get(position);

            holder.tvTitle.setText(notice.getTitle());
            switch (notice.getType()) {
                case "공지":
                    holder.tvType.setImageResource(R.drawable.alert1);
                    break;
                case "점검":
                    holder.tvType.setImageResource(R.drawable.alert2);
                    break;
                case "경고":
                    holder.tvType.setImageResource(R.drawable.alert3);
                    break;
                default:
                    holder.tvType.setImageResource(R.drawable.ic_favorite);
                    break;

            }

//            holder.tvRegisterTime.setText(notice.getRegisterTime().toString());
            holder.itemView.setTag(notice.getNoticeId());
        }

        @Override
        public int getItemCount() {
            return notices.size();
        }

        class NoticeHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.TitleNotice)
            protected TextView tvTitle;

            @BindView(R.id.TypeNotice)
            protected ImageView tvType;

            public NoticeHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
