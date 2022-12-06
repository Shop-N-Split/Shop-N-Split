package com.akshaj02.shopnsplit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class homepage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepg)

        val shop = findViewById<Button>(R.id.shop)
        shop.setOnClickListener {
            val intent = Intent(this, ChecklistPage::class.java)
            startActivity(intent)
        }

        val split = findViewById<Button>(R.id.split)
        split.setOnClickListener {
            val intent = Intent(this, Split::class.java)
            startActivity(intent)
        }

        val home = findViewById<ImageView>(R.id.icon1)
        home.setOnClickListener {
        }

        val shop2 = findViewById<ImageView>(R.id.icon2)
        shop2.setOnClickListener {
            val intent = Intent(this, ChecklistPage::class.java)
            startActivity(intent)
        }

        val split2 = findViewById<ImageView>(R.id.icon3)
        split2.setOnClickListener {
            val intent = Intent(this, Split::class.java)
            startActivity(intent)
        }

        val settings = findViewById<ImageView>(R.id.icon4)
        settings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

    }


}