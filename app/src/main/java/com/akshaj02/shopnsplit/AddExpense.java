package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddExpense extends AppCompatActivity {

    Button addContact;
    TextView text;
    EditText description;
    EditText money;
    Button mAdd;
    String[] permissions = {"android.permission.READ_CONTACTS"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexp);

        description = (EditText) findViewById(R.id.description);
        money = (EditText) findViewById(R.id.money);
        mAdd = (Button) findViewById(R.id.add);

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, 80);
                 //   Intent intent = new Intent(AddExpense.this, contact_main.class);
                 //   startActivity(intent);
                }
            }
        });

        //split the contacts from every space and add it to a string array
//        String[] contactList = contacts.split(" ");



        mAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String desc = description.getText().toString();
                String money1 = money.getText().toString();

                Intent intent = new Intent(AddExpense.this, Split.class);
                intent.putExtra("description", desc);
                intent.putExtra("money", money1);
                intent.putExtra("contacts", contacts);
                //intent.putExtra("contactList", contactList);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 80){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddExpense.this, contact_main.class);
                startActivity(intent);

            }
            else
            {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                //Permission Granted
            }

        }
    }
}