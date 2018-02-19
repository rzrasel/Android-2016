package com.rz.alienfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rz Rasel on 2018-02-14.
 */

public class FragForgotPassword extends Fragment {
    private Activity activity;
    private Context context;
    public int fragmentIndex = 2;
    public FragForgotPassword() {
        // Required empty public constructor
    }

    public static FragForgotPassword newInstance(String param1, String param2) {
        FragForgotPassword fragment = new FragForgotPassword();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frag_forgot_password, container, false);
        activity = getActivity();
        context = getContext();
        return rootView;
    }
}