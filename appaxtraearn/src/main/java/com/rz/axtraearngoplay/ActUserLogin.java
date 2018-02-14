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

import com.rz.alienfragment.FragAccountActivation;
import com.rz.alienfragment.FragForgotPassword;
import com.rz.alienfragment.FragLogin;
import com.rz.alienfragment.FragRegistration;

public class ActUserLogin extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ActionBar toolbar;

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
        loadFragment(new FragLogin());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem argMenuItem) {
            Fragment fragment;
            switch (argMenuItem.getItemId()) {
                case R.id.nav_login:
                    toolbar.setTitle(getResources().getString(R.string.nav_login));
                    fragment = new FragLogin();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_forgot_password:
                    toolbar.setTitle(getResources().getString(R.string.nav_forgot_password));
                    fragment = new FragForgotPassword();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_account_activation:
                    toolbar.setTitle(getResources().getString(R.string.nav_account_activation));
                    fragment = new FragAccountActivation();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_registration:
                    toolbar.setTitle(getResources().getString(R.string.nav_registration));
                    fragment = new FragRegistration();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.sysFrameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
