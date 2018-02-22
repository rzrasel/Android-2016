package com.rz.axtraearngoplay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.rz.alienfragment.FragAccountActivation;
import com.rz.alienfragment.FragForgotPassword;
import com.rz.alienfragment.FragForgotPasswordChange;
import com.rz.alienfragment.FragLogin;
import com.rz.alienfragment.FragRegistration;
import com.rz.alienfragment.OnFragmentInteractionListener;

public class ActUserLogin extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ActionBar toolbar;
    private Class aClass;
    private int currentFragmentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_login);
        activity = this;
        context = this;

        toolbar = getSupportActionBar();

        BottomNavigationView sysNavBottomNav = (BottomNavigationView) findViewById(R.id.sysNavBottomNav);
        sysNavBottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        toolbar.setTitle(getResources().getString(R.string.nav_login));
        aClass = FragRegistration.class;
        //loadFragment(new FragLogin());
        FragLogin fragObj = new FragLogin();
        Fragment fragment = (Fragment) fragObj;
        currentFragmentIndex = fragObj.fragmentIndex;
        onLoadFragment(fragment, false, false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem argMenuItem) {
            Fragment fragment;
            //onLoadFragment(new FragUserRegistration(), true, true);
            switch (argMenuItem.getItemId()) {
                case R.id.nav_login:
                    toolbar.setTitle(getResources().getString(R.string.nav_login));
                    //fragment = new FragLogin();
                    //loadFragment(fragment);
                    FragLogin fragObj = new FragLogin();
                    fragment = (Fragment) fragObj;
                    if (fragObj.fragmentIndex > currentFragmentIndex) {
                        onLoadFragment(fragment, true, true);
                    } else {
                        onLoadFragment(fragment, true, false);
                    }
                    currentFragmentIndex = fragObj.fragmentIndex;
                    return true;
                case R.id.nav_forgot_password:
                    toolbar.setTitle(getResources().getString(R.string.nav_forgot_password));
                    /*fragment = new FragForgotPassword();
                    loadFragment(fragment);*/
                    FragForgotPassword fragObjPass = new FragForgotPassword();
                    fragObjPass.onSetCallbackListener(new OnFragmentInteractionListener() {
                        @Override
                        public void SendToActivityListener(String argStr) {
                            //
                        }

                        @Override
                        public void onClickListener(View argView) {
                            toolbar.setTitle(getResources().getString(R.string.nav_forgot_password_change));
                            FragForgotPasswordChange fragObjPassChange = new FragForgotPasswordChange();
                            Fragment addFragment = (Fragment) fragObjPassChange;
                            if (fragObjPassChange.fragmentIndex > currentFragmentIndex) {
                                onLoadFragment(addFragment, true, true);
                            } else {
                                onLoadFragment(addFragment, true, false);
                            }
                            currentFragmentIndex = fragObjPassChange.fragmentIndex;
                            return;
                        }
                    });
                    fragment = (Fragment) fragObjPass;
                    if (fragObjPass.fragmentIndex > currentFragmentIndex) {
                        onLoadFragment(fragment, true, true);
                    } else {
                        onLoadFragment(fragment, true, false);
                    }
                    currentFragmentIndex = fragObjPass.fragmentIndex;
                    return true;
                case R.id.nav_account_activation:
                    toolbar.setTitle(getResources().getString(R.string.nav_account_activation));
                    /*fragment = new FragAccountActivation();
                    loadFragment(fragment);*/
                    FragAccountActivation fragObjActive = new FragAccountActivation();
                    fragment = (Fragment) fragObjActive;
                    if (fragObjActive.fragmentIndex > currentFragmentIndex) {
                        onLoadFragment(fragment, true, true);
                    } else {
                        onLoadFragment(fragment, true, false);
                    }
                    currentFragmentIndex = fragObjActive.fragmentIndex;
                    return true;
                case R.id.nav_registration:
                    toolbar.setTitle(getResources().getString(R.string.nav_registration));
                    /*fragment = new FragRegistration();
                    loadFragment(fragment);*/
                    FragRegistration fragObjRegi = new FragRegistration();
                    fragment = (Fragment) fragObjRegi;
                    if (fragObjRegi.fragmentIndex > currentFragmentIndex) {
                        onLoadFragment(fragment, true, true);
                    } else {
                        onLoadFragment(fragment, true, false);
                    }
                    currentFragmentIndex = fragObjRegi.fragmentIndex;
                    return true;
            }
            return false;
        }
    };

    private void onLoadFragment(Fragment argFragment, boolean argIsSetAnim, boolean argIsLeftToRight) {
        /*ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("animX");
        objectAnimator.setFloatValues(0, 1);
        objectAnimator.setDuration(1000);*/
        //FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (argIsSetAnim) {
            if (argIsLeftToRight) {
                fragmentTransaction.setCustomAnimations(R.animator.in_from_left, R.animator.in_from_right);
            } else {
                //fragmentTransaction.setCustomAnimations(R.animator.in_from_right, R.animator.in_from_left);
                fragmentTransaction.setCustomAnimations(R.animator.out_from_left, R.animator.out_from_right);
            }
        }
        //fragmentTransaction.replace(R.id.sysFrameLayout, argFragment);
        fragmentTransaction.replace(R.id.sysFrameContainer, argFragment);
        if (argIsSetAnim) {
            //fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.sysFrameContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
/*
try {
fragment = (FragRegistration) aClass.newInstance();
loadFragment(fragment);
} catch (InstantiationException e) {
e.printStackTrace();
} catch (IllegalAccessException e) {
e.printStackTrace();
}
*/