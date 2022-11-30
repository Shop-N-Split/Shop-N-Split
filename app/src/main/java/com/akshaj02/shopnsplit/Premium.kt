package com.akshaj02.shopnsplit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Premium : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_premium)

        val pay = findViewById<Button>(R.id.payButton)
        pay.setOnClickListener {
            val intent = Intent(this, Payments::class.java)
            startActivity(intent)
        }



    }

}

















