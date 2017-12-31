package com.rz.axtraearngoplay;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private TextView idTvShaking;
    private Button sysBtnShake;
    private AnimTransition animTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        animTransition = new AnimTransition();
        Intent intentRedirectWindow = new Intent(context, ActInstruction.class);
        intentRedirectWindow.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intentRedirectWindow.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        startActivity(intentRedirectWindow);
        finish();
        idTvShaking = (TextView) findViewById(R.id.idTvShaking);
        sysBtnShake = (Button) findViewById(R.id.sysBtnShake);
        ObjectAnimator
                .ofFloat(idTvShaking, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                .setDuration(500)
                .start();
        sysBtnShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                ObjectAnimator
                        .ofFloat(idTvShaking, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                        .setDuration(500)
                        .start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        super.onBackPressed();
    }
}