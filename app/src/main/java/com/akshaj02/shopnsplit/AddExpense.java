package com.akshaj02.shopnsplit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;


public class AddExpense extends AppCompatActivity {

    Button addContact;
    TextView text;
    EditText description;
    EditText money;
    Button mAdd;
    ImageView calendar;
    TextView dateText;
    String[] permissions = {"android.permission.READ_CONTACTS"};

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexp);

        initDatePicker();

        calendar = findViewById(R.id.calendar);
        dateText = findViewById(R.id.date);
        dateText.setText(getTodaysDate());

        calendar.setOnClickListener(v -> datePickerDialog.show());
        dateText.setOnClickListener(v -> datePickerDialog.show());



        description = findViewById(R.id.description);
        money = findViewById(R.id.money);
        String totalAmt = money.getText().toString();
        Toast.makeText(this, totalAmt, Toast.LENGTH_SHORT).show();
        mAdd = findViewById(R.id.add);
        addContact = findViewById(R.id.contact);

        // get intent from previous activity
        Intent intent = getIntent();
        String contacts = intent.getStringExtra("contacts");
        //double count = 4.00;
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
        //String garbage = String.valueOf(count);

        //divide the money by the number of contacts

        //change money to double
//        double moneyOwed = Double.parseDouble(money.getText().toString());
//        //divide money by 2
//        double moneySplit = moneyOwed/2;
//
//        //change moneySplit to string
//        String moneySplitString = Double.toString(moneySplit);



        text = (TextView) findViewById(R.id.text);
        text.setText(contacts);
        // set the string to the text view


        addContact.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 80);
             //   Intent intent = new Intent(AddExpense.this, contact_main.class);
             //   startActivity(intent);
            }
        });

        //split the contacts from every space and add it to a string array
//        String[] contactList = contacts.split(" ");



        mAdd.setOnClickListener(v -> {
            String desc = description.getText().toString();
            String money1 = money.getText().toString();
            String date = dateText.getText().toString();
            //split the date at ,
            String[] dateSplit = date.split(",");
            Intent intent1 = new Intent(AddExpense.this, Split.class);
            intent1.putExtra("description", desc);
            intent1.putExtra("money", money1);
            intent1.putExtra("contacts", contacts);
            intent1.putExtra("date", dateSplit[0]);
            //intent.putExtra("contactList", contactList);
            startActivity(intent1);
        });

    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + ", " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "Jan";
        if(month == 2)
            return "Feb";
        if(month == 3)
            return "Mar";
        if(month == 4)
            return "Apr";
        if(month == 5)
            return "May";
        if(month == 6)
            return "Jun";
        if(month == 7)
            return "Jul";
        if(month == 8)
            return "Aug";
        if(month == 9)
            return "Sep";
        if(month == 10)
            return "Oct";
        if(month == 11)
            return "Nov";
        if(month == 12)
            return "Dec";

        //default should never happen
        return "Jan";
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = getMonthFormat(month) + " " + dayOfMonth + ", " + year;
                dateText.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
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