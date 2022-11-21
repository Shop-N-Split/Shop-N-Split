package com.akshaj02.shopnsplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.widget.Button
import android.widget.TextView


class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val registerButton = findViewById<TextView>(R.id.registerButton)
        val emailAddress = findViewById<EditText>(R.id.emailAddress)
        val userPassword = findViewById<EditText>(R.id.password)
        val confirmPass = findViewById<EditText>(R.id.confirmPass)
        val username = findViewById<EditText>(R.id.username)




        registerButton.setOnClickListener {
            when {
                TextUtils.isEmpty(username.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter username.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(emailAddress.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(userPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Register,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(confirmPass.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Register,
                        "Please confirm password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email: String = emailAddress.text.toString().trim { it <= ' ' }
                    val password: String = userPassword.text.toString().trim { it <= ' ' }
                    val confirm: String = confirmPass.text.toString().trim { it <= ' ' }
                    val name: String = username.text.toString().trim { it <= ' ' }

                    // Create an instance and create a register a user with email and password.
                    if(password == confirm) {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                // If the registration is successfully done
                                if (task.isSuccessful) {

                                    // Firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@Register,
                                        "You are registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // TODO (STEP 4: Redirect the user to the Main Screen.)
                                    // START
                                    // Finish the Register Screen
                                    val intent = Intent(this@Register, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                    // END
                                } else {
                                    Toast.makeText(
                                        this@Register,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                    else{
                        Toast.makeText(
                            this@Register,
                            "Passwords do not match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        }
    }
}