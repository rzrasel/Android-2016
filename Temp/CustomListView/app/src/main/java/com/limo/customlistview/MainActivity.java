package com.limo.customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setData();
  }

  private void setData() {
    ArrayList<DataModel> data = new ArrayList<>();
    data.add(new DataModel(RowType.TITLE, "MAIN", "a@b.com", "123456"));
    data.add(new DataModel(RowType.DETAILS, "Limo", "samiul@shurjomukhi.com.bd", "01613529475"));
    data.add(new DataModel(RowType.DETAILS, "Eimo", "sajid.eimo@gmail.com", "01714095499"));

    CustomAdapter customAdapter = new CustomAdapter(this, data);

    ListView list_main = findViewById(R.id.list_main);
    list_main.setAdapter(customAdapter);
  }
}
