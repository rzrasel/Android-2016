package com.rz.alienfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Rz Rasel on 2018-02-14.
 */

public class FragForgotPassword extends Fragment {
    private Activity activity;
    private Context context;
    public int fragmentIndex = 330;
    private Button sysBtnSubmit;
    private OnFragmentInteractionListener onFragmentInteractionListener;

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

    public void onSetCallbackListener(OnFragmentInteractionListener argOnFragmentInteractionListener) {
        this.onFragmentInteractionListener = argOnFragmentInteractionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frag_forgot_password, container, false);
        activity = getActivity();
        context = getContext();
        sysBtnSubmit = (Button) rootView.findViewById(R.id.sysBtnSubmit);
        sysBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                if (onFragmentInteractionListener != null) {
                    onFragmentInteractionListener.onClickListener(argView);
                }
            }
        });
        return rootView;
    }
}