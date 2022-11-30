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

class Split : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_split)

        val addexpense = findViewById<Button>(R.id.split)
        addexpense.setOnClickListener {
            val intent = Intent(this, AddExpense::class.java)
            startActivity(intent)
        }

    }

}