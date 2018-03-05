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

public class FragAccountActivation extends Fragment {
    private Activity activity;
    private Context context;
    public int fragmentIndex = 440;
    public FragAccountActivation() {
        // Required empty public constructor
    }

    public static FragAccountActivation newInstance(String param1, String param2) {
        FragAccountActivation fragment = new FragAccountActivation();
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
        View rootView = inflater.inflate(R.layout.frag_account_activation, container, false);
        activity = getActivity();
        context = getContext();
        return rootView;
    }
}
