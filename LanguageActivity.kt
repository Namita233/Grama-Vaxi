package com.example.gramavaxi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_language)

        val btnEnglish =
            findViewById<Button>(R.id.btnEnglish)

        val btnKannada =
            findViewById<Button>(R.id.btnKannada)

        // ENGLISH
        btnEnglish.setOnClickListener {

            val intent =
                Intent(
                    this,
                    WelcomeActivity::class.java
                )

            intent.putExtra(
                "LANG",
                "ENGLISH"
            )

            startActivity(intent)
        }

        // KANNADA
        btnKannada.setOnClickListener {

            val intent =
                Intent(
                    this,
                    WelcomeActivity::class.java
                )

            intent.putExtra(
                "LANG",
                "KANNADA"
            )

            startActivity(intent)
        }
    }
}