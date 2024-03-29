package com.akshaj02.shopnsplit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.text.method.PasswordTransformationMethod
import android.widget.ImageView
import android.widget.TextView
//import google sign in package
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class MainActivity : AppCompatActivity() {

    //onPressedBack variable to check if back button is pressed
    private var onPressedBack = false
    //FirebaseAuth instance


    @SuppressLint("ResourceType", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //If eye icon is clicked, password is shown and eye icon is changed
        val eyeIcon = findViewById<ImageView>(R.id.eye)
        eyeIcon.setImageResource(R.drawable.invisible)
        val password = findViewById<EditText>(R.id.password)
        eyeIcon.setOnClickListener {
            if (password.transformationMethod == null) {
                password.transformationMethod = PasswordTransformationMethod()
                eyeIcon.setImageResource(R.drawable.invisible)
            } else {
                password.transformationMethod = null
                eyeIcon.setImageResource(R.drawable.visibility)
            }
        }

        val registerButton = findViewById<TextView>(R.id.register)
        registerButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val resetPassword = findViewById<TextView>(R.id.forgotPassword)
        resetPassword.setOnClickListener {
            val intent = Intent(this, forgotPassword::class.java)
            startActivity(intent)
        }



        val googleSignin = findViewById<com.google.android.material.button.MaterialButton>(R.id.googleSignInButton)
        googleSignin.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            val GoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
            val signInIntent = GoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, 1)

            //if the user is already signed in, then go to homepage
            if (auth.currentUser != null) {
                val intent = Intent(this, homepage::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(
                    this@MainActivity,
                    "Please sign in.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            //if google sign in is successful, then go to homepage


        }

        //once google sign in is done, go to home page


        val loginButton = findViewById<TextView>(R.id.signin)
        val emailAddress = findViewById<EditText>(R.id.emailAddress)
        val userPassword = findViewById<EditText>(R.id.password)
        loginButton.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser != null) {
                // User is signed in (getCurrentUser() will be null if not signed in)
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }
            when {
                TextUtils.isEmpty(emailAddress.text.toString().trim { it <= ' ' }) -> {
                    //error message if email is empty
                    emailAddress.error = "Please enter email."
                }

                TextUtils.isEmpty(userPassword.text.toString().trim { it <= ' ' }) -> {
                    userPassword.error = "Please enter password."
                }
                //login the user with last registered email and password

                //if the email does not contain @, then show error message
                !emailAddress.text.toString().contains("@") -> {
                    emailAddress.error = "Please enter valid email."
                }


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

                                val intent = Intent(this, homepage::class.java)
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

//onBackPressed function to check if back button is pressed
    override fun onBackPressed() {

    }

}