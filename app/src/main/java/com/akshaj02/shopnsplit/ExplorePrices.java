package com.akshaj02.shopnsplit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExplorePrices extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DataModel> mList;
    private ItemAdapter adapter;
    private TextView TotalPrice;
    private String walTitles = "";
    private String walPrices = "";
    private String tarTitles = "";
    private String tarPrices = "";
    private String WalmartTotal = "";
    private String TargetTotal = "";

    Button mHomepage;
    Button mSplit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_prices);

        recyclerView = findViewById(R.id.main_recyclervie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mHomepage = findViewById(R.id.homepage);

        mHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplorePrices.this, homepage.class);
                startActivity(intent);
            }
        });

        mSplit = findViewById(R.id.split_button);

        mSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplorePrices.this, Split.class);
                startActivity(intent);
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                walTitles= null;
                walPrices= null;
                tarTitles= null;
                tarPrices= null;
                WalmartTotal= null;
                TargetTotal= null;
            } else {
                walTitles= extras.getString("wTitles");
                walPrices= extras.getString("wPrices");
                tarTitles= extras.getString("tTitles");
                tarPrices= extras.getString("tPrices");
                WalmartTotal= extras.getString("WalmartTotal");
                TargetTotal= extras.getString("TargetTotal");
            }
        } else {
            walTitles= (String) savedInstanceState.getSerializable("wTitles");
            walPrices= (String) savedInstanceState.getSerializable("wPrices");
            tarTitles= (String) savedInstanceState.getSerializable("tTitles");
            tarPrices= (String) savedInstanceState.getSerializable("tPrices");
            WalmartTotal= (String) savedInstanceState.getSerializable("WalmartTotal");
            TargetTotal= (String) savedInstanceState.getSerializable("TargetTotal");
        }

        //tokenize the strings
        String[] wTitles = walTitles.split("#");
        String[] wPrices = walPrices.split("#");
        String[] tTitles = tarTitles.split("#");
        String[] tPrices = tarPrices.split("#");


        mList = new ArrayList<>();

        TextView TotalPrice = findViewById(R.id.totalPrice);

        //limit the amount of characters in wTitles and tTitles, add ... if too long
        for (int i = 0; i < wTitles.length; i++) {
            if (wTitles[i].length() > 20) {
                wTitles[i] = wTitles[i].substring(0, 20) + "...";
            }
        }

        for (int i = 0; i < tTitles.length; i++) {
            if (tTitles[i].length() > 20) {
                tTitles[i] = tTitles[i].substring(0, 20) + "...";
            }
        }

        //list1
        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add(wTitles[0]);
        nestedList1.add(wTitles[1]);
        nestedList1.add(wTitles[2]);
        nestedList1.add(wTitles[3]);
        nestedList1.add(wTitles[4]);
        //nestedList1.add(WalmartTotal);
        //nestedList1.add(TargetTotal);


        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add(tTitles[0]);
        nestedList2.add(tTitles[1]);
        nestedList2.add(tTitles[2]);
        nestedList2.add(tTitles[3]);
        nestedList2.add(tTitles[4]);
        //nestedList2.add(WalmartTotal);
        //nestedList2.add(TargetTotal);


        mList.add(new DataModel(nestedList1 , "Walmart", WalmartTotal));
        mList.add(new DataModel(nestedList2,"Target", TargetTotal));

        adapter = new ItemAdapter(mList);
        recyclerView.setAdapter(adapter);
    }
}