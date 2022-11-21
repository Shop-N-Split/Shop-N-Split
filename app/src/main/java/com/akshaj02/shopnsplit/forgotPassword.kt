package com.akshaj02.shopnsplit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class forgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val emailSend = findViewById<TextView>(R.id.emailSend)

        emailSend.setOnClickListener {
            //take email from user
            val emailAddress = findViewById<TextView>(R.id.emailAddress)
            val email: String = emailAddress.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(
                    this@forgotPassword,
                    "Please enter email.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@forgotPassword,
                                "Email sent.",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@forgotPassword, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else {
                            Toast.makeText(
                                this@forgotPassword,
                                "Please enter valid email.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

        }


    }
}