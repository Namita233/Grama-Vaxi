package com.example.gramavaxi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        val tvTitle =
            findViewById<TextView>(
                R.id.tvWelcomeTitle
            )

        val tvSubtitle =
            findViewById<TextView>(
                R.id.tvSubtitle
            )

        val tvInstructions =
            findViewById<TextView>(
                R.id.tvInstructions
            )

        val btnRegister =
            findViewById<Button>(
                R.id.btnRegisterUser
            )

        val btnLogin =
            findViewById<Button>(
                R.id.btnLoginUser
            )

        val btnTutorial =
            findViewById<Button>(
                R.id.btnTutorial
            )

        val language =
            intent.getStringExtra("LANG")

        // KANNADA
        if (language == "KANNADA") {

            tvTitle.text =
                "ಗ್ರಾಮ ವ್ಯಾಕ್ಸಿಗೆ ಸ್ವಾಗತ"

            tvSubtitle.text =
                "ಗ್ರಾಮೀಣ ಪಶು ಆರೋಗ್ಯ ವ್ಯವಸ್ಥೆ"

            tvInstructions.text =
                "• ಪ್ರಾಣಿ ನೋಂದಣಿ ಮಾಡಿ\n\n" +
                        "• ಲಸಿಕೆ ದಿನಾಂಕ ಟ್ರ್ಯಾಕ್ ಮಾಡಿ\n\n" +
                        "• ಜ್ಞಾಪನೆ ಸೂಚನೆಗಳನ್ನು ಪಡೆಯಿರಿ\n\n" +
                        "• ಅನಾರೋಗ್ಯ ಪ್ರಾಣಿ ವರದಿ ಮಾಡಿ"

            btnRegister.text =
                "ನೋಂದಣಿ"

            btnLogin.text =
                "ಲಾಗಿನ್"

            btnTutorial.text =
                "ಆಪ್ ಬಳಸುವ ವಿಧಾನ"
        }

        // REGISTER PAGE
        btnRegister.setOnClickListener {

            val intent =
                Intent(
                    this,
                    UserRegisterActivity::class.java
                )

            intent.putExtra(
                "LANG",
                language
            )

            startActivity(intent)
        }

        // LOGIN PAGE
        btnLogin.setOnClickListener {

            val intent =
                Intent(
                    this,
                    LoginActivity::class.java
                )

            intent.putExtra(
                "LANG",
                language
            )

            startActivity(intent)
        }

        // TUTORIAL PAGE
        btnTutorial.setOnClickListener {

            val intent =
                Intent(
                    this,
                    TutorialActivity::class.java
                )

            intent.putExtra(
                "LANG",
                language
            )

            startActivity(intent)
        }
    }
}