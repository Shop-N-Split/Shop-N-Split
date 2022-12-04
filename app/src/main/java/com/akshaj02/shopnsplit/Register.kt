package com.akshaj02.shopnsplit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.text.method.PasswordTransformationMethod
import android.widget.*



class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val registerButton = findViewById<TextView>(R.id.registerButton)
        val emailAddress = findViewById<EditText>(R.id.emailAddress)
        val userPassword = findViewById<EditText>(R.id.password)
        val confirmPass = findViewById<EditText>(R.id.confirmPass)
        val username = findViewById<EditText>(R.id.username)
        val eyeIcon = findViewById<ImageView>(R.id.eye)



        eyeIcon.setOnClickListener {
            if (userPassword.transformationMethod == null) {
                userPassword.transformationMethod = PasswordTransformationMethod()
                confirmPass.transformationMethod = PasswordTransformationMethod()
                eyeIcon.setImageResource(R.drawable.invisible)
            } else {
                userPassword.transformationMethod = null
                confirmPass.transformationMethod = null
                eyeIcon.setImageResource(R.drawable.visibility)
            }
        }




        registerButton.setOnClickListener {
            when {
                TextUtils.isEmpty(username.text.toString().trim { it <= ' ' }) -> {
                    username.error = "Please enter a username."
                }
                TextUtils.isEmpty(emailAddress.text.toString().trim { it <= ' ' }) -> {
                    emailAddress.error = "Please enter an email address."
                }

                !emailAddress.text.toString().contains("@") -> {
                    emailAddress.error = "Please enter valid email."
                }

                TextUtils.isEmpty(userPassword.text.toString().trim { it <= ' ' }) -> {
                    userPassword.error = "Please enter a password."
                }
                TextUtils.isEmpty(confirmPass.text.toString().trim { it <= ' ' }) -> {
                    confirmPass.error = "Please re-enter your password."
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