package com.rz.axtraearngoplay;

import android.animation.ObjectAnimator;
//import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.view.Window;
import android.widget.FrameLayout;

public class ActAppLanding extends AppCompatActivity implements FragmentCallback {
    private Activity activity;
    private Context context;
    private FrameLayout sysFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_app_landing);
        activity = this;
        context = this;
        sysFrameLayout = (FrameLayout) findViewById(R.id.sysFrameLayout);
        onLoadFragment(new FragUserLogin(), false, false);
    }

    private void initWindowDecorActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
        Window window = getWindow();

        // Initializing the window decor can change window feature flags.
        // Make sure that we have the correct set before performing the test below.
        window.getDecorView();

        if (isChild() || !window.hasFeature(Window.FEATURE_ACTION_BAR) || actionBar != null) {
            return;
        }
        //actionBar = new WindowDecorActionBar(activity);
    }

    private void onLoadFragment(Fragment argFragment, boolean argIsSetAnim, boolean argIsLeftToRight) {
        /*ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("animX");
        objectAnimator.setFloatValues(0, 1);
        objectAnimator.setDuration(1000);*/
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        if (argIsSetAnim) {
            if (argIsLeftToRight) {
                fragmentTransaction.setCustomAnimations(R.animator.in_from_left, R.animator.in_from_right);
            } else {
                //fragmentTransaction.setCustomAnimations(R.animator.in_from_right, R.animator.in_from_left);
                fragmentTransaction.setCustomAnimations(R.animator.out_from_left, R.animator.out_from_right);
            }
        }
        fragmentTransaction.replace(R.id.sysFrameLayout, argFragment);
        if (argIsSetAnim) {
            //fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    private void onLoadFragment_001(Fragment argFragment) {
        /*FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setPropertyName("animX");
        objectAnimator.setFloatValues(0, 1);
        //objectAnimator.setTarget(logoView);//call this when your ready to set target
        objectAnimator.setDuration(1000);
        //setCustomAnimations(R.anim.enter_from_left, R.anim.enter_from_left, R.anim.exit_to_left, R.anim.exit_to_left);
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.slide_in, R.animator.slide_out);
        /*fragmentTransaction.setCustomAnimations(
                R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                R.animator.card_flip_left_in, R.animator.card_flip_left_out);*/
        fragmentTransaction.setCustomAnimations(R.animator.in_from_left, R.animator.in_from_right);
        //fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        //fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.sysFrameLayout, argFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        /*// create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.sysFrameLayout, argFragment);
        fragmentTransaction.commit(); // save sysFrameLayout changes*/
    }

    @Override
    public void onButtonClicked(String argValue) {
        if (argValue.equalsIgnoreCase("login")) {
            onLoadFragment(new FragUserRegistration(), true, true);
        } else if (argValue.equalsIgnoreCase("registration")) {
            onLoadFragment(new FragUserLogin(), true, false);
        }
        //Toast.makeText(context, "Clicked Button", Toast.LENGTH_LONG).show();
    }
}