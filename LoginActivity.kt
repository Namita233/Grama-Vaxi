package com.example.gramavaxi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val etPhone =
            findViewById<EditText>(
                R.id.etLoginPhone
            )

        val etPassword =
            findViewById<EditText>(
                R.id.etLoginPassword
            )

        val btnLogin =
            findViewById<Button>(
                R.id.btnLogin
            )

        val language =
            intent.getStringExtra("LANG")

        // KANNADA
        if (language == "KANNADA") {

            etPhone.hint =
                "ಮೊಬೈಲ್ ಸಂಖ್ಯೆ"

            etPassword.hint =
                "ಪಾಸ್ವರ್ಡ್"

            btnLogin.text =
                "ಲಾಗಿನ್"
        }

        btnLogin.setOnClickListener {

            val phone =
                etPhone.text.toString()

            val password =
                etPassword.text.toString()

            // EMPTY CHECK
            if (
                phone.isEmpty() ||
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

            // LOGIN VALIDATION
            if (
                phone ==
                UserRegisterActivity.savedPhone &&

                password ==
                UserRegisterActivity.savedPassword
            ) {

                Toast.makeText(
                    this,
                    "Login Successful",
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

            } else {

                Toast.makeText(
                    this,
                    "Wrong mobile number or password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}