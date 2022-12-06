package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class contact_main extends AppCompatActivity {

    TextView contactsDisplay;
    Button pickContacts;
    final int CONTACT_PICK_REQUEST = 1000;
    Button backSplit;

    //ArrayList<String> contacts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_main);


        contactsDisplay = (TextView) findViewById(R.id.txt_selected_contacts);
        pickContacts = (Button) findViewById(R.id.btn_pick_contacts);

        assert pickContacts != null;
        pickContacts.setOnClickListener(v -> {

                Intent intentContactPick = new Intent(contact_main.this, ContactsPickerActivity.class);
                contact_main.this.startActivityForResult(intentContactPick, CONTACT_PICK_REQUEST);

        });

        backSplit = (Button) findViewById(R.id.split);

        backSplit.setOnClickListener(v -> {
            Intent intent = new Intent(contact_main.this, AddExpense.class);
            intent.putExtra("contacts", contactsDisplay.getText().toString());
            startActivity(intent);
        });
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CONTACT_PICK_REQUEST && resultCode == RESULT_OK){

            ArrayList<Contact> selectedContacts = data.getParcelableArrayListExtra("SelectedContacts");



            StringBuilder display= new StringBuilder();
            for(int i=0;i<selectedContacts.size();i++){
                //contacts.add(selectedContacts.get(i).toString());
                display.append(selectedContacts.get(i).toString()).append(",");

            }
            contactsDisplay.setText(display);

        }

    }


}
