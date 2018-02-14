package com.rz.alienfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rz Rasel on 2018-02-14.
 */

public class FragLogin extends Fragment {
    public FragLogin() {
        // Required empty public constructor
    }

    public static FragLogin newInstance(String param1, String param2) {
        FragLogin fragment = new FragLogin();
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
        View rootView = inflater.inflate(R.layout.frag_login, container, false);

        return rootView;
    }
}
