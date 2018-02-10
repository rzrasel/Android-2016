package com.rz.axtraearngoplay;

import android.app.Activity;

/**
 * Created by Rz Rasel on 2017-12-20.
 */

public class AnimTransition {
    private Activity activity;

    public void onFinish() {
        overridePendingTransitionExit();
    }

    public void onStartActivity() {
        overridePendingTransitionEnter();
    }

    public void overridePendingTransitionEnter() {
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void overridePendingTransitionExit() {
        activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void onBackPressed() {
        overridePendingTransitionExit();
    }
}
//https://kylewbanks.com/blog/left-and-right-slide-animations-on-android-activity-or-view
