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
    private int counter = 0;
    LinearLayoutManager layoutManager;
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
        String expenseSplit = intent.getStringExtra("expenseSplit");

        initData(expense, date, moneyOwed, expenseSplit);
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
        mList.add(new SplitModel(expense, date, moneyOwed, amountLent));
    }
}
