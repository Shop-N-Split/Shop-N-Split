package com.akshaj02.shopnsplit;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_view);
        Bundle extras = getIntent().getExtras();
        ArrayList<String> arrayList = extras.getStringArrayList("productlist");
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(items);
    }
}
