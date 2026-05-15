package com.example.gramavaxi

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnRegister =
            findViewById<Button>(
                R.id.btnRegister
            )

        val btnView =
            findViewById<Button>(
                R.id.btnView
            )

        val btnDisease =
            findViewById<Button>(
                R.id.btnDisease
            )

        val title =
            findViewById<TextView>(
                R.id.tvTitle
            )

        val tvAlert =
            findViewById<TextView>(
                R.id.tvAlert
            )

        createNotificationChannel()

        // NOTIFICATION PERMISSION
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS
                ),
                101
            )
        }

        val language =
            intent.getStringExtra("LANG")

        // KANNADA
        if (language == "KANNADA") {

            title.text =
                "ಗ್ರಾಮ ವ್ಯಾಕ್ಸಿ"

            btnRegister.text =
                "ಪ್ರಾಣಿ ನೋಂದಣಿ"

            btnView.text =
                "ಪ್ರಾಣಿಗಳನ್ನು ನೋಡಿ"

            btnDisease.text =
                "ಅನಾರೋಗ್ಯ ಪ್ರಾಣಿ ವರದಿ"

            tvAlert.text =
                "⚠️ ಲಸಿಕೆ ಜಾಗೃತಿ ಸೂಚನೆ\n\n" +
                        "• ಪ್ರಾಣಿಗಳಿಗೆ ಪ್ರತಿ 6 ತಿಂಗಳಿಗೆ ಲಸಿಕೆ ನೀಡಿ\n\n" +
                        "• ಪ್ರಾಣಿ ಆರೋಗ್ಯವನ್ನು ನಿಯಮಿತವಾಗಿ ಪರಿಶೀಲಿಸಿ\n\n" +
                        "• ಅನಾರೋಗ್ಯ ಪ್ರಾಣಿಯನ್ನು ತಕ್ಷಣ ವರದಿ ಮಾಡಿ"

        } else {

            title.text =
                "Grama-Vaxi"

            btnRegister.text =
                "Register Animal"

            btnView.text =
                "View Animals"

            btnDisease.text =
                "Report Sick Animal"

            tvAlert.text =
                "⚠️ Vaccination Awareness Alert\n\n" +
                        "• Vaccinate cattle every 6 months\n\n" +
                        "• Monitor animal health regularly\n\n" +
                        "• Report sick animals immediately"
        }

        // REGISTER PAGE
        btnRegister.setOnClickListener {

            val intent =
                Intent(
                    this,
                    RegisterAnimalActivity::class.java
                )

            intent.putExtra(
                "LANG",
                language
            )

            startActivity(intent)
        }

        // VIEW PAGE
        btnView.setOnClickListener {

            val intent =
                Intent(
                    this,
                    AnimalListActivity::class.java
                )

            intent.putExtra(
                "LANG",
                language
            )

            startActivity(intent)
        }

        // REPORT PAGE
        btnDisease.setOnClickListener {

            val intent =
                Intent(
                    this,
                    ReportActivity::class.java
                )

            intent.putExtra(
                "LANG",
                language
            )

            startActivity(intent)
        }
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel =
                NotificationChannel(
                    "vaccine_channel",
                    "Vaccination Alerts",
                    NotificationManager.IMPORTANCE_HIGH
                )

            val manager =
                getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(
                channel
            )
        }
    }
}