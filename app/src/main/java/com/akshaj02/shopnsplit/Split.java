package com.akshaj02.shopnsplit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class Split extends AppCompatActivity {




    private RecyclerView recyclerView;
    private List<SplitModel> mList;
    private SplitAdapter adapter;
    LinearLayoutManager layoutManager;

    Button mHomepage;
    Button mSplit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        //if the intent is null, then do this



        //TODO: getting intent causes app to crash
        //if the arraylist is empty, then do this

            initData();
            initRecyclerView();


        //mSplit action
        mSplit = findViewById(R.id.split_button);
        mSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void initData() {

//        Intent intent = getIntent();
//        String expense = intent.getStringExtra("expense");
//        String moneyOwed = intent.getStringExtra("money");
        mList = new ArrayList<>();
        //Hardcoded data, need to get data from AddExpense.java
        mList.add(new SplitModel("expense", "Dec 15", "money", "$10.00"));

    }
}
