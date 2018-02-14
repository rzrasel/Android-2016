package com.rz.alienfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Rz Rasel on 16-Aug-2016.
 */
public class FragTest extends Fragment {
    //|------------------------------------------------------------|
    private OnFragmentInteractionListener onFragmentInteractionListener;
    //private UserSession userSession = new UserSession();
    //|------------------------------------------------------------|
    //|------------------------------------------------------------|

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //|------------------------------------------------------------|
        View rootView = inflater.inflate(R.layout.frag_test, container, false);
        //|------------------------------------------------------------|
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //userSession = (UserSession) bundle.getSerializable(APPConstants.SESSION.KEY);
        }
        //|------------------------------------------------------------|
        TextView idTextViewTest = (TextView) rootView.findViewById(R.id.idTextViewTest);
        //idTextViewTest.setText(userSession.getUserMobileNo());
        //|------------------------------------------------------------|
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.SendToActivityListener(this.getClass().getSimpleName() + " to activity");
        }
        //|------------------------------------------------------------|
        return rootView;
        //|------------------------------------------------------------|
    }

    @Override
    public void onAttach(Context argContext) {
        super.onAttach(argContext);
        try {
            //eventListener = (EventListener) context;
            onFragmentInteractionListener = (OnFragmentInteractionListener) argContext;
        } catch (ClassCastException e) {
            //LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    /*public interface EventListener {
        public void ValuePassingListener();
    }*/
}