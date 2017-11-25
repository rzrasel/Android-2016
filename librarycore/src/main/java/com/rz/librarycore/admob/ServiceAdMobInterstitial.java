package com.rz.librarycore.admob;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rz Rasel on 2017-11-23.
 */

public class ServiceAdMobInterstitial extends Service {
    private int repeatedMillis;
    public static int repeatedSeconds;
    public static boolean isPaused = false;
    private ThreadTimer threadTimer;
    private int threadCounter = 0;
    private int threadDelayMillis = 1000;
    public static EventHandlerListener eventHandlerListener;

    @Override
    public IBinder onBind(Intent argIntent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent argIntent, int argFlags, int argStartId) {
        super.onStartCommand(argIntent, argFlags, argStartId);
        threadTimer = new ThreadTimer();
        threadTimer.start();
        return Service.START_STICKY;
        //return super.onStartCommand(argIntent, argFlags, argStartId);
    }

    @Override
    public void onDestroy() {
        System.out.println("THREAD_DESTROYED: " + getCurrentTime());
        stopSelf();
        handlerTimer.removeCallbacks(threadTimer);
        super.onDestroy();
    }

    private class ThreadTimer extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                //sleep(70 * 1000);
                //handler.sendEmptyMessage(0);
                /*Message msg = mHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString(MSG_KEY, getCurrentTime());
                msg.setData(bundle);
                mHandler.sendMessage(msg);*/
                //handler.postDelayed(this, 1000);
                Message message = handlerTimer.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("thread_run_time", getCurrentTime());
                message.setData(bundle);
                handlerTimer.sendMessageDelayed(message, threadDelayMillis);
                System.out.println("THREAD_COUNT: " + threadCounter + ", REPEAT_CALL: " + repeatedSeconds);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    private Handler handlerTimer = new Handler() {
        @Override
        public void handleMessage(Message argMessage) {
            super.handleMessage(argMessage);
            Bundle bundle = argMessage.getData();
            if (isPaused) {
                System.out.println("THREAD_PAUSED");
            } else {
                threadCounter++;
                System.out.println("HANDLER_COUNT: " + threadCounter);
                if (bundle != null) {
                    String string = bundle.getString("thread_run_time");
                    System.out.println("THREAD_TIME: " + string + " HANDLER_CALL_TIME: " + getCurrentTime());
                }
                if (threadCounter >= repeatedSeconds) {
                    System.out.println("TIME_REPEATED");
                    threadCounter = 0;
                    //ActDashboard.adMobInterstitial.onShow();
                    //ActDashboard.adMobRewardedVideo.onShow();
                    if (eventHandlerListener != null) {
                        eventHandlerListener.onShowListener();
                    }
                }
            }
            this.post(threadTimer);
        }
    };

    private String getCurrentTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public interface EventHandlerListener {
        public void onShowListener();
    }
}