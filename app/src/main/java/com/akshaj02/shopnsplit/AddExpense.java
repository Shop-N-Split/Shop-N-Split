package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class AddExpense extends AppCompatActivity {

    Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexp);
        addContact = findViewById(R.id.contact);

        addContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddExpense.this, contact_main.class);
                startActivity(intent);
            }
        });



    }
}