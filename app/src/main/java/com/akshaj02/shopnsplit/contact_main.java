package com.akshaj02.shopnsplit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class contact_main extends AppCompatActivity {

    TextView contactsDisplay;
    Button pickContacts;
    final int CONTACT_PICK_REQUEST = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactmain);


        contactsDisplay = (TextView) findViewById(R.id.txt_selected_contacts);
        pickContacts = (Button) findViewById(R.id.btn_pick_contacts);
        assert pickContacts != null;

//        new ActivityResultContracts.StartActivityForResult().registerForActivityResult(this, result -> {
//            if (result.getResultCode() == RESULT_OK) {
//                Intent data = result.getData();
//                ArrayList<String> contacts = data.getStringArrayListExtra("contacts");
//                contactsDisplay.setText(contacts.toString());
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CONTACT_PICK_REQUEST && resultCode == RESULT_OK){

            ArrayList<Contact> selectedContacts = data.getParcelableArrayListExtra("SelectedContacts");

            StringBuilder display= new StringBuilder();
            for(int i=0;i<selectedContacts.size();i++){

                display.append(i + 1).append(". ").append(selectedContacts.get(i).toString()).append("\n");

            }
            contactsDisplay.setText("Selected Contacts : \n\n"+display);

        }

    }


}
