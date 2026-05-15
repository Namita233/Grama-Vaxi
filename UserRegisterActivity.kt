package com.example.gramavaxi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserRegisterActivity : AppCompatActivity() {

    companion object {

        var savedPhone = ""

        var savedPassword = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_user_register
        )

        val etName =
            findViewById<EditText>(
                R.id.etUserName
            )

        val etPhone =
            findViewById<EditText>(
                R.id.etPhone
            )

        val etVillage =
            findViewById<EditText>(
                R.id.etVillage
            )

        val etPassword =
            findViewById<EditText>(
                R.id.etPassword
            )

        val btnCreate =
            findViewById<Button>(
                R.id.btnCreateAccount
            )

        val language =
            intent.getStringExtra("LANG")

        // KANNADA
        if (language == "KANNADA") {

            etName.hint =
                "ರೈತನ ಹೆಸರು"

            etPhone.hint =
                "ಮೊಬೈಲ್ ಸಂಖ್ಯೆ"

            etVillage.hint =
                "ಗ್ರಾಮದ ಹೆಸರು"

            etPassword.hint =
                "ಪಾಸ್ವರ್ಡ್"

            btnCreate.text =
                "ನೋಂದಣಿ"
        }

        btnCreate.setOnClickListener {

            val name =
                etName.text.toString()

            val phone =
                etPhone.text.toString()

            val village =
                etVillage.text.toString()

            val password =
                etPassword.text.toString()

            // EMPTY CHECK
            if (
                name.isEmpty() ||
                phone.isEmpty() ||
                village.isEmpty() ||
                password.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Fill all details",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            // PHONE VALIDATION
            if (phone.length != 10) {

                Toast.makeText(
                    this,
                    "Enter valid 10-digit mobile number",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            // SAVE LOGIN DETAILS
            savedPhone = phone

            savedPassword = password

            Toast.makeText(
                this,
                "Registration Successful",
                Toast.LENGTH_LONG
            ).show()

            val intent =
                Intent(
                    this,
                    MainActivity::class.java
                )

            intent.putExtra(
                "LANG",
                language
            )

            startActivity(intent)
        }
    }
}