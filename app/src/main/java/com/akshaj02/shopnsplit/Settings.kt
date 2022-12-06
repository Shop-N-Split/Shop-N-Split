package com.akshaj02.shopnsplit

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Settings : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val permissions = arrayOf("android.permission.READ_CONTACTS")

        val logout_butt = findViewById<Button>(R.id.logout_butt)
        logout_butt.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            var intent: Intent = Intent(this, homepage::class.java)
            startActivity(intent)
            finish()

        }

        val about_us = findViewById<Button>(R.id.tocbut)
//        about_us.setOnClickListener {
//            var intent: Intent = Intent(this, aboutus::class.java)
//            startActivity(intent)
//            finish()
//
//        }

        val terms_condition = findViewById<Button>(R.id.terms_butt)
        terms_condition.setOnClickListener {
            var intent: Intent = Intent(this, terms_cond::class.java)
            startActivity(intent)
            finish()

        }

        val userInfo = findViewById<TextView>(R.id.userInfo)
        //fetch email address from firebase
        val email = FirebaseAuth.getInstance().currentUser?.email
        userInfo.text = email

        //fetch email address from database and display it





        val pass_butt = findViewById<Button>(R.id.pass_update)
        pass_butt.setOnClickListener {
            var intent: Intent = Intent(this, change_password::class.java)
            startActivity(intent)
            finish()

        }




        val premium = findViewById<Button>(R.id.premium)
        premium.setOnClickListener {
            var intent: Intent = Intent(this, Premium::class.java)
            startActivity(intent)
            finish()

        }

        val display_contacts = findViewById<Button>(R.id.contacts_butt)
        display_contacts.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, 80)
                //   Intent intent = new Intent(AddExpense.this, contact_main.class);
                //   startActivity(intent);

                //     var intent: Intent = Intent(this, contact_main::class.java)
                //      startActivity(intent)
                //      finish()


                // }

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 80) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                var intent: Intent = Intent(this, ContactsPickerActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }


    }


}
