package me.apphive.doordekhaetui.receiver;

/**
 * Created by Rz Rasel on 2017-11-05.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ReceiverHostResponse extends BroadcastReceiver {
    private OnListenerHandler onListenerHandler;
    private String intentFilter = "";

    public ReceiverHostResponse() {
    }

    /**
     * Cons
     *
     * @param argOnListenerHandler
     */
    public ReceiverHostResponse(OnListenerHandler argOnListenerHandler) {
        this.onListenerHandler = argOnListenerHandler;
    }

    @Override
    public void onReceive(Context argContext, Intent argIntent) {
        String intentAction = argIntent.getAction();
        if (onListenerHandler != null) {
            /*if (intentAction.equals(intentFilter)) {
                onListenerHandler.onReceive(argContext, argIntent, intentFilter, intentAction);
            }*/
            //LogWriter.Log("INTENT_ACTION:- " + intentAction);
            Bundle bundle = argIntent.getExtras();
            if (bundle != null) {
            }
            onListenerHandler.onReceive(argContext, argIntent, intentAction);
        }
        //LogWriter.Log(TAG_CLASS_NAME + "_RECEIVER:- ");
    }

    public interface OnListenerHandler {
        //public void onReceive(Context argContext, Intent argIntent, String argIntentFilterSupplied, String argIntentAction);
        public void onReceive(Context argContext, Intent argIntent, String argIntentAction);
    }
}