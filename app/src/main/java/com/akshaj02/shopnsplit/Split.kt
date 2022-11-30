package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text


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

        //get intent from previous activity
        val intent = getIntent()
        val description1 = intent.getStringExtra("description")
        val amount1 = intent.getStringExtra("money")
        val contacts = intent.getStringExtra("contacts")



        val addexpense = findViewById<Button>(R.id.split)
        addexpense.setOnClickListener {
            val intent = Intent(this, AddExpense::class.java)
            startActivity(intent)
        }

        val description = findViewById<TextView>(R.id.text1)

        val amount = findViewById<EditText>(R.id.money)

        val contact = findViewById<Button>(R.id.contact)

        //split contacts
       // val splitContacts = contacts.split("")


        description.setText(contacts)
        //amount.setText(amount1)


    }

}