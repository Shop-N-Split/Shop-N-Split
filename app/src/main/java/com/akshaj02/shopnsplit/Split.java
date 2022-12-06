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
//        if(expense != null && moneyOwed != null && date != null) {
//            mList = new ArrayList<>();
//            String moneyPaid = "0.00";
//            mList.add(new SplitModel(expense, data, moneyOwed, moneyPaid));
//            adapter = new SplitAdapter(this, mList);
//            recyclerView = findViewById(R.id.main_recyclervie);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(adapter);
//        }
//        else {
//            mList = new ArrayList<>();
//            mList.add(new SplitModel("Expense", "Dec 5", "100", "0.00"));
//            adapter = new SplitAdapter(this, mList);
//            recyclerView = findViewById(R.id.main_recyclervie);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(adapter);
//        }

        String amountLent = "100.00";
       //Delete the null character from the string moneyOwed
//        amountLent = moneyOwed.replaceAll("\u0000", "");
        //convert amountLent to a float
        float amountLentFloat = Float.parseFloat(amountLent);
        //divide the amountLent by the number of contacts
        float amountLentPerPerson = (float) (amountLentFloat * 0.75);
        //convert the amountLentPerPerson to a string
        String amountLentPerPersonString = String.valueOf(amountLentPerPerson);



        //Split the money owed by the number of contacts
//        float amountLent1 = Float.parseFloat(moneyOwed);
//        float amountLent2 = amountLent1 / 4;
//        //round the amount to 2 decimal places
//        String amountLent3 = String.format("%.2f", amountLent2);



        initData(expense, date, moneyOwed, amountLentPerPersonString);
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
    private void initData(String expense, String date, String moneyOwed, String amountLent3) {
        mList = new ArrayList<>();
        //Hardcoded data, need to get data from AddExpense.java
        mList.add(new SplitModel(expense, date, "200", amountLent3));
    }
}