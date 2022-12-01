package com.akshaj02.shopnsplit;

import android.annotation.SuppressLint
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

        val amountPrint = findViewById<TextView>(R.id.money)
        val description = findViewById<TextView>(R.id.desc)
        val contactsss = findViewById<TextView>(R.id.people)
        //get intent from previous activity
        val intent = getIntent()
        val description1 = intent.getStringExtra("description")
        val amount1 = intent.getStringExtra("money")
        val contacts = intent.getStringExtra("contacts")


        //Get number of contacts and split amount
        val numContacts = contacts?.split(",")?.size
        val splitAmount = amount1?.toDouble()?.div(numContacts?.toDouble()!!)
        //Multiple the amount by size - 1 to get the amount to be paid by the person who added the expense
        val amountToBePaid = splitAmount?.toDouble()?.times(numContacts?.minus(1)!!)
        //Print the amount to be paid by the person who added the expense
        amountPrint.text = amountToBePaid.toString()
        //Print the description of the expense

        //replace every character with " " if it is between | and ,
        val contacts1 = contacts?.replace(Regex("(?<=\\|)[^,]*(?=,)"), " ")
        //remove | from the string
        val contacts2 = contacts1?.replace("|", "")
        //remove the space before comma
        val contacts3 = contacts2?.replace("  ,", ", ")
        //remove the comma at the end
        val contacts4 = contacts3?.replace(",$", "")
        //add a $ before amount
        val amount2 = "$" + amount1


        //set the textviews to the values
        description.text = description1
        contactsss.text = contacts4


        val addexpense = findViewById<Button>(R.id.split)
        addexpense.setOnClickListener {
            val intent = Intent(this, AddExpense::class.java)
            startActivity(intent)
        }



    }

}