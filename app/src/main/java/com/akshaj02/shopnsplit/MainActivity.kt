package com.akshaj02.shopnsplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val registerButton = findViewById<TextView>(R.id.register)
        registerButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val loginButton = findViewById<TextView>(R.id.signin)
        val emailAddress = findViewById<EditText>(R.id.emailAddress)
        val userPassword = findViewById<EditText>(R.id.password)
        loginButton.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser != null) {
                // User is signed in (getCurrentUser() will be null if not signed in)
                val intent = Intent(this, productSearch::class.java)
                startActivity(intent)
                finish()
            }
            when {
                TextUtils.isEmpty(emailAddress.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(userPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //login the user with last registered email and password




                else -> {
                    val email: String = emailAddress.text.toString().trim { it <= ' ' }
                    val password: String = userPassword.text.toString().trim { it <= ' ' }

                    //val auth = FirebaseAuth.getInstance()



                    // Create an instance and create a register a user with email and password.
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            // If the registration is successfully done
                            if (task.isSuccessful) {

                                // Firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@MainActivity,
                                    "You are logged in successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this, productSearch::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                // If the registering is not successful then show error message.
                                Toast.makeText(
                                    this@MainActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                }
            }

        }
    }
}