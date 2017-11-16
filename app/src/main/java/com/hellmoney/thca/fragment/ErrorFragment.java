package com.hellmoney.thca.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellmoney.thca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ErrorFragment extends Fragment {
    public static final String TAG = ErrorFragment.class.getName();


    public ErrorFragment() {
    }

    public static ErrorFragment newInstance() {
        ErrorFragment fragment = new ErrorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

}
