package com.rz.axtraearngoplay;

/**
 * Created by developer on 12/11/17.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragUserLogin extends Fragment {
    private Activity activity;
    private Context context;
    private View rootView;
    private FragmentActivity listener;
    private FragmentCallback fragmentCallback;
    private Button sysBtnSubmit;
    private FloatingActionButton sysFloatBtnSubmit;

    @Override
    public View onCreateView(LayoutInflater argInflater, ViewGroup argContainer, Bundle argSavedInstanceState) {
        rootView = argInflater.inflate(R.layout.frag_user_login, argContainer, false);
        activity = getActivity();
        context = argContainer.getContext();
        sysFloatBtnSubmit = (FloatingActionButton) rootView.findViewById(R.id.sysFloatBtnSubmit);
        sysFloatBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                if (fragmentCallback != null) {
                    fragmentCallback.onButtonClicked("login");
                }
            }
        });
        ((Button) rootView.findViewById(R.id.sysBtnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                Intent intentRedirectWindow = new Intent(context, ActDashboard.class);
                intentRedirectWindow.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intentRedirectWindow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentRedirectWindow);
                activity.finish();
            }
        });
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
            fragmentCallback = (FragmentCallback) argContext;
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
        fragmentCallback = null;
        super.onDetach();
        /*super.onDetach();
        this.listener = null;*/
    }

    /*public interface Callback {
        public void onButtonClicked(View radiobtn);
    }*/
}
//https://guides.codepath.com/android/creating-and-using-fragments
//http://abhiandroid.com/ui/fragment
//DemoFragment fragmentDemo = (DemoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDemo);
//https://stackoverflow.com/questions/40115750/activity-to-fragment-on-button-click