package com.rz.axtraearngoplay;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rz Rasel on 2018-01-01.
 */

public class FragDashboard extends Fragment {
    private Activity activity;
    private Context context;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater argInflater, ViewGroup argContainer, Bundle argSavedInstanceState) {
        rootView = argInflater.inflate(R.layout.frag_dashboard, argContainer, false);
        return rootView;
    }

    @Override
    public void onAttach(Context argContext) {
        super.onAttach(argContext);
        /*if (argContext instanceof Activity) {
            this.listener = (FragmentActivity) argContext;
        }*/
        //fragmentCallback = (FragmentCallback) argContext;
        try {
            //eventListener = (EventListener) context;
            //fragmentCallback = (FragmentCallback) argContext;
        } catch (ClassCastException e) {
            //LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
        /*try {
            //eventListener = (EventListener) context;
            fragmentEventListener = (FragmentEventListener) context;
        } catch (ClassCastException e) {
            LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }*/
    }

    @Override
    public void onDetach() {
        //fragmentCallback = null;
        super.onDetach();
        /*super.onDetach();
        this.listener = null;*/
    }
}
