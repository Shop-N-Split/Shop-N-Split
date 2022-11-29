package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


//class Split : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_split)
//
//        val addexpense = findViewById<Button>(R.id.split)
//        addexpense.setOnClickListener {
//            val intent = Intent(this, AddExpense::class.java)
//            startActivity(intent)
//        }
//
//    }
//
//}

public class Split extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        Button addexpense = findViewById(R.id.split);
        addexpense.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddExpense.class);
            startActivity(intent);
        });
    }
}