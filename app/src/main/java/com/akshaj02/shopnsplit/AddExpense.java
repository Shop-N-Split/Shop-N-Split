package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AddExpense extends AppCompatActivity {

    Button addContact;
    TextView text;
    EditText description;
    EditText money;
    Button mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexp);

        description = (EditText) findViewById(R.id.description);
        money = (EditText) findViewById(R.id.money);
        String totalAmt = money.getText().toString();
        mAdd = (Button) findViewById(R.id.add);
        addContact = findViewById(R.id.contact);

        // get intent from previous activity
        Intent intent = getIntent();
        String contacts = intent.getStringExtra("contacts");
        double count = 4.00;
//        String contactList1 = contacts.replaceAll("\\|[^,]*\\,", " ");
//        //remove | from the string
//        String contactList2 = contactList1.replaceAll("\\|", "");
//        //remove the space before comma
//        String contactList3 = contactList2.replaceAll(" ,", ",");
//        //remove the comma at the end
//        String contactList4 = contactList3.replaceAll(",$", "");

        //Convert money to double
//        double moneyOwed = Double.parseDouble(totalAmt);
//        double moneySplit = moneyOwed/count;
//        double total = moneySplit * (count-1);
//        String amountLent = Double.toString(total);

//        String garbage2 = Calculations.calculate(contacts, totalAmt);
        String garbage = String.valueOf(count);


        text = (TextView) findViewById(R.id.text);
        text.setText(contacts);
        // set the string to the text view


        addContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddExpense.this, contact_main.class);
                startActivity(intent);
            }
        });

        //split the contacts from every space and add it to a string array
//        String[] contactList = contacts.split(" ");



        mAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String desc = description.getText().toString();
                String money1 = money.getText().toString() + garbage;

                Intent intent = new Intent(AddExpense.this, Split.class);
                intent.putExtra("description", desc);
                intent.putExtra("money", money1);
                intent.putExtra("contacts", contacts);
                //intent.putExtra("contactList", contactList);
                startActivity(intent);
            }
        });






    }
}