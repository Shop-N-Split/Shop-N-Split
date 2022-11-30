package com.akshaj02.shopnsplit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val logout_butt = findViewById<Button>(R.id.logout_butt)
        logout_butt.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }


        val pass_butt = findViewById<Button>(R.id.pass_update)
        pass_butt.setOnClickListener {
            var intent: Intent = Intent(this, change_password::class.java)
            startActivity(intent)
            finish()

        }

        val display_contacts = findViewById<Button>(R.id.contacts_butt)
        display_contacts.setOnClickListener {
            var intent: Intent = Intent(this, contact_main::class.java)
            startActivity(intent)
            finish()

        }

        val premium = findViewById<Button>(R.id.premium)
        premium.setOnClickListener {
            var intent: Intent = Intent(this, Premium::class.java)
            startActivity(intent)
            finish()

        }

        // }

    }
}