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

    Button mHome;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_prices);

        recyclerView = findViewById(R.id.main_recyclervie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mHome = findViewById(R.id.home_button);

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplorePrices.this, homepage.class);
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

//        String[] wTitlesPrices = new String[wTitles.length + wPrices.length];
//        System.arraycopy(wTitles, 0, wTitlesPrices, 0, wTitles.length);
//        System.arraycopy(wPrices, 0, wTitlesPrices, wTitles.length, wPrices.length);
//
//        String[] tTitlesPrices = new String[tTitles.length + tPrices.length];
//        System.arraycopy(tTitles, 0, tTitlesPrices, 0, tTitles.length);
//        System.arraycopy(tPrices, 0, tTitlesPrices, tTitles.length, tPrices.length);



//        Merge wTitles and wPrices to a new array
        String[] wTitlesPrices = new String[wTitles.length];
        for (int i = 0; i < wTitles.length; i++) {
            wTitlesPrices[i] = wTitles[i] + "          $" + wPrices[i];
        }

        //Merge tTitles and tPrices to a new array
        String[] tTitlesPrices = new String[tTitles.length];
        for (int i = 0; i < tTitles.length; i++) {
            tTitlesPrices[i] = tTitles[i] + "          $" + tPrices[i];
        }

        //list1
        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add(wTitlesPrices[0]);
        nestedList1.add(wTitlesPrices[1]);
        nestedList1.add(wTitlesPrices[2]);
        nestedList1.add(wTitlesPrices[3]);
        nestedList1.add(wTitlesPrices[4]);


        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add(tTitlesPrices[0]);
        nestedList2.add(tTitlesPrices[1]);
        nestedList2.add(tTitlesPrices[2]);
        nestedList2.add(tTitlesPrices[3]);
        nestedList2.add(tTitlesPrices[4]);


        mList.add(new DataModel(nestedList1 , "Walmart", WalmartTotal));
        mList.add(new DataModel(nestedList2,"Target", TargetTotal));

        adapter = new ItemAdapter(mList);
        recyclerView.setAdapter(adapter);
    }
}