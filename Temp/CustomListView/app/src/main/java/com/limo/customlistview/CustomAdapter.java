package com.limo.customlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
  private static String TAG = CustomAdapter.class.getSimpleName();
  private static LayoutInflater inflater = null;
  public Activity activity;
  private ArrayList<DataModel> data = null;

  public CustomAdapter(Activity a, ArrayList<DataModel> d) {
    activity = a;
    data = d;
    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public int getCount() {
    if (data != null) {
      return data.size();
    } else {
      return 0;
    }
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    View mView = convertView;
    DataModel dataModel = data.get(position);

    // Title
    if (dataModel.getRowType() == RowType.TITLE) {
      mView = inflater.inflate(R.layout.row_view_title, parent, false);
      ViewHolderTitle holder = new ViewHolderTitle(mView);
      holder.textTitle = mView.findViewById(R.id.text_title);
      holder.textTitle.setText(dataModel.getName());
    }
    // Details
    else if (dataModel.getRowType() == RowType.DETAILS) {
      mView = inflater.inflate(R.layout.row_view_details, parent, false);
      ViewHolderDetails holder = new ViewHolderDetails(mView);
      holder.textName = mView.findViewById(R.id.text_name);
      holder.textEmail = mView.findViewById(R.id.text_email);
      holder.textMobile = mView.findViewById(R.id.text_mobile);

      holder.textName.setText(dataModel.getName());
      holder.textEmail.setText(dataModel.getEmail());
      holder.textMobile.setText(dataModel.getMobile());
    }

    mView.setEnabled(false);
    return mView;
  }

  static class ViewHolderTitle {
    TextView textTitle;

    public ViewHolderTitle(View mView) {

    }
  }

  static class ViewHolderDetails {
    TextView textName;
    TextView textEmail;
    TextView textMobile;

    public ViewHolderDetails(View mView) {

    }
  }
}
