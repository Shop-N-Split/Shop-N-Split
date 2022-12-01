package com.akshaj02.shopnsplit

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Payments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        val cardNumber = findViewById<EditText>(R.id.cardNumber)
        //call Lunh.java to check if the card number is valid
        val cardNumberValid = isValid(cardNumber.text.toString())

        val name = findViewById<EditText>(R.id.Name)

        val cardType = findViewById<ImageView>(R.id.cardType)

        //when something is typed in the card number field, check if it is valid
        cardNumber.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

                //if the number of digits is 16, separate it with a space every 4 digits
                if (cardNumber.text.toString().length == 16) {
                    val cardNumberString = cardNumber.text.toString()
                    val cardNumberString1 = cardNumberString.substring(0, 4)
                    val cardNumberString2 = cardNumberString.substring(4, 8)
                    val cardNumberString3 = cardNumberString.substring(8, 12)
                    val cardNumberString4 = cardNumberString.substring(12, 16)
                    cardNumber.setText("$cardNumberString1 $cardNumberString2 $cardNumberString3 $cardNumberString4")
                }

                //if the number of digits is 15, separate it with a space every 4, 5 and 5 digits
                if (cardNumber.text.toString().length == 15) {
                    val cardNumberString = cardNumber.text.toString()
                    val cardNumberString1 = cardNumberString.substring(0, 4)
                    val cardNumberString2 = cardNumberString.substring(4, 10)
                    val cardNumberString3 = cardNumberString.substring(10, 15)
                    cardNumber.setText("$cardNumberString1 $cardNumberString2 $cardNumberString3")
                }

                //if number of digits is not 15 or 16, show an error message
                if (cardNumber.text.toString().length != 15 || cardNumber.text.toString().length != 16) {
                    cardNumber.error = "Invalid card number"
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

        name.setOnClickListener() {

            //find first digit of card number

        }

//        val cardType = when (cardNumber.text.toString().substring(0, 1)) {
//            "4" -> "Visa"
//            "5" -> "MasterCard"
//            "3" -> "American Express"
//            "6" -> "Discover"
//            else -> "Unknown"
//        }
//
        val cardTypeText = findViewById<ImageView>(R.id.cardType)
//
//        when (cardType) {
//            "Visa" -> cardTypeText.setImageResource(R.drawable.visa)
//            "MasterCard" -> cardTypeText.setImageResource(R.drawable.mastercard)
//            "American Express" -> cardTypeText.setImageResource(R.drawable.amex)
//            "Discover" -> cardTypeText.setImageResource(R.drawable.discover)
//        }
//
//        //If cardNumberValid is true, then the card number is valid
//        if (cardNumberValid) {
//            //Pop up a message saying the card number is valid
//            //Toast a message saying the card number is valid
//            Toast.makeText(this, "Card number is valid", Toast.LENGTH_SHORT).show()
//        } else {
//            //Pop up a message saying the card number is invalid
//            //Toast a message saying the card number is invalid
//            Toast.makeText(this, "Card number is invalid", Toast.LENGTH_SHORT).show()
//        }


    }

    //Lunh algorithm to check if the card number is valid
    fun isValid(cardNumber: String): Boolean {
        val digits = cardNumber.toCharArray()
        var sum = 0
        for (i in digits.size - 1 downTo 0) {
            var digit = digits[i] - '0'
            if (digits.size % 2 == i % 2) {
                digit *= 2
            }
            if (digit > 9) {
                digit -= 9
            }
            sum += digit
        }
        return sum % 10 == 0
    }
}