package com.akshaj02.shopnsplit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Split extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<SplitModel> mList;
    private SplitAdapter adapter;
    LinearLayoutManager layoutManager;
    private int counter = 0;

    Button mHomepage;
    Button mSplit;

    String date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        //if the intent is null, then do this
        Intent intent = getIntent();
        String expense = intent.getStringExtra("description");
        String moneyOwed = intent.getStringExtra("money");
        date = intent.getStringExtra("date");


        //convert moneyOwed to a double


//        String contactList = intent.getStringExtra("contacts");
//        int count = 4;
//        int count = Integer.parseInt(contactList.split(",").length + "");
//        //replace every character with " " if it is between | and ,
//        String contactList1 = contactList.replaceAll("\\|[^,]*\\,", " ");
//        //remove | from the string
//        String contactList2 = contactList1.replaceAll("\\|", "");
//        //remove the space before comma
//        String contactList3 = contactList2.replaceAll(" ,", ",");
//        //remove the comma at the end
//        String contactList4 = contactList3.replaceAll(",$", "");
        String amountLent = "15.00";
        //Split the money owed by the number of contacts
//        double money = Double.parseDouble(moneyOwed);
//        double moneySplit = money/count;
//        double total = moneySplit * (count-1);
//        String amountLent = Double.toString(total);

        initData(expense, date, moneyOwed, amountLent);
        initRecyclerView();


        //mSplit action
        mSplit = findViewById(R.id.split_button);
        mSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                Intent intent = new Intent(Split.this, AddExpense.class);
                startActivity(intent);
            }
        });

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.splitRecycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SplitAdapter(mList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void initData(String expense, String date, String moneyOwed, String amountLent) {
        mList = new ArrayList<>();
        //Hardcoded data, need to get data from AddExpense.java
        mList.add(new SplitModel(expense, date, "200", "250"));
    }
}