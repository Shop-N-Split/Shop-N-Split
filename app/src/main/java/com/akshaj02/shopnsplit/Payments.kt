package com.akshaj02.shopnsplit

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Payments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        val cardNumber = findViewById<EditText>(R.id.cardNumber)
        //call Lunh.java to check if the card number is valid
        var cardNumberValid = false

        val name = findViewById<EditText>(R.id.Name)

        val expiryDate = findViewById<EditText>(R.id.expiryDate)

        val cardType = findViewById<ImageView>(R.id.cardType)

        val mPayButton = findViewById<Button>(R.id.payButton)


        //when the user is typing in expiry date, add a "/" after the first 2 digits
        expiryDate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 2) {
                    expiryDate.setText(s.toString() + "/")
                    expiryDate.setSelection(expiryDate.text.length)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })



        //when something is typed in the card number field, check if it is valid
        cardNumber.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

                //if the number of digits is 16, separate it with a space every 4 digits
                when (cardNumber.text.toString().length) {
                    16 -> {
                        val cardNumberString = cardNumber.text.toString()
                        val cardNumberString1 = cardNumberString.substring(0, 4)
                        val cardNumberString2 = cardNumberString.substring(4, 8)
                        val cardNumberString3 = cardNumberString.substring(8, 12)
                        val cardNumberString4 = cardNumberString.substring(12, 16)
                        cardNumber.setText("$cardNumberString1 $cardNumberString2 $cardNumberString3 $cardNumberString4")
                        cardNumberValid = isValid(cardNumber.text.toString())
                    }

                    //if the number of digits is 15, separate it with a space every 4, 5 and 5 digits
                    15 -> {
                        val cardNumberString = cardNumber.text.toString()
                        val cardNumberString1 = cardNumberString.substring(0, 4)
                        val cardNumberString2 = cardNumberString.substring(4, 10)
                        val cardNumberString3 = cardNumberString.substring(10, 15)
                        cardNumber.setText("$cardNumberString1 $cardNumberString2 $cardNumberString3")
                        cardNumberValid = isValid(cardNumber.text.toString())

                    }
                    else -> {
                        cardNumber.error = "Please enter a valid card number."
                        cardNumberValid = false
                    }
                }






                val firstDigit = cardNumber.text.toString().substring(0,1)
                //if the first digit is 4, then it is a visa card
                if (firstDigit == "4") {
                    //change drawable from mastercard to visa
                    //relativeLayout.setBackgroundResource(R.drawable.visabox)
                    cardType.setImageResource(R.drawable.visa)
                }
                if (firstDigit == "5"){
                    cardType.setImageResource(R.drawable.mastercard)
                }
                if (firstDigit == "3"){
                    cardType.setImageResource(R.drawable.amex)
                }
                if (firstDigit == "6"){
                    cardType.setImageResource(R.drawable.discover)
                }
            }
        }

        mPayButton.setOnClickListener() {
            //check is name is empty
            //when any of the fields are empty, show an error

            if (cardNumber.text.toString().isEmpty()) {
                cardNumber.error = "Please enter your card number."
            }


            if(cardNumberValid) {
                if (name.text.toString().isEmpty()) {
                    name.error = "Please enter your name."
                }
                if (expiryDate.text.toString().isEmpty()) {
                    expiryDate.error = "Please enter your expiry date."
                }
                //if the first two digits of the expiry date are greater than 12, show an error
                if (expiryDate.text.toString().substring(0, 2).toInt() > 12) {
                    expiryDate.error = "Please enter a valid expiry date."
                }
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
                //move to next activity
                Intent(this, Premium::class.java).also {
                    startActivity(it)
                }
            }
            else {
                Toast.makeText(this, "Invalid Card Number", Toast.LENGTH_SHORT).show()
                //clear the card number field
                cardNumber.text.clear()
            }
        }
    }

    //Lunh algorithm to check if the card number is valid
    fun isValid(number: String): Boolean {
        var checksum: Int = 0

        for (i in number.length - 1 downTo 0 step 2) {
            checksum += number.get(i) - '0'
        }
        for (i in number.length - 2 downTo 0 step 2) {
            val n: Int = (number.get(i) - '0') * 2
            checksum += if (n > 9) n - 9 else n
        }

        return checksum%10 == 0
    }
}