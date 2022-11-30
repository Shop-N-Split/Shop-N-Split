package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AddExpense extends AppCompatActivity {

    Button addContact;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexp);
        // get intent from previous activity
        Intent intent = getIntent();

        // get the string from the intent
        String contacts = intent.getStringExtra("contacts");


        //split the string from new line
        //String[] contactList = contacts.split("\\r?\\n");


        text = (TextView) findViewById(R.id.text);
        text.setText(contacts);
        // set the string to the text view

        addContact = findViewById(R.id.contact);

        addContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddExpense.this, contact_main.class);
                startActivity(intent);
            }
        });



    }
}