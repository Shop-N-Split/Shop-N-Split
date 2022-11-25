package com.akshaj02.shopnsplit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExplorePrices extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DataModel> mList;
    private ItemAdapter adapter;
    private TextView TotalPrice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_prices);

        recyclerView = findViewById(R.id.main_recyclervie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

//        Bundle extras = getIntent().getExtras();
//        ArrayList<String> arrayList = extras.getStringArrayList("productlist");
//        ListView listView = findViewById(R.id.listView);
//        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(items);

//        Bundle extras = getIntent().getExtras();
//        ArrayList<String> arrayList = extras.getStringArrayList("productlist");
////        ListView listView = findViewById(R.id.listView);
//        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
////        listView.setAdapter(items);
//        //Convert the arraylist to a string
//        String str = arrayList.toString();
//        //Remove the brackets
//        String str1 = str.substring(1, str.length()-1);
//        //Split the string based on comma
//        String[] arr = str1.split(",");


//        TextView TotalPrice = findViewById(R.id.TotalPrice);

        //list1
        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add("Milk");
        nestedList1.add("Bread");
        nestedList1.add("Eggs");

        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add("Milk");
        nestedList2.add("Bread");
        nestedList2.add("Eggs");

        //Loop through the array and add the items to the nested list
//        for (String s : arr) {
//            nestedList1.add(s);
//            nestedList2.add(s);
//        }

        mList.add(new DataModel(nestedList1 , "Walmart Total"));
        mList.add(new DataModel( nestedList2,"Target Total"));

        adapter = new ItemAdapter(mList);
        recyclerView.setAdapter(adapter);
    }
}