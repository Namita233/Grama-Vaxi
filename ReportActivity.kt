package com.example.gramavaxi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_report)

        val title =
            findViewById<TextView>(
                R.id.tvReportTitle
            )

        val etAnimal =
            findViewById<EditText>(
                R.id.etAnimalName
            )

        val etSymptoms =
            findViewById<EditText>(
                R.id.etSymptoms
            )

        val etVillage =
            findViewById<EditText>(
                R.id.etVillage
            )

        val btnSend =
            findViewById<Button>(
                R.id.btnSend
            )

        val language =
            intent.getStringExtra("LANG")

        // KANNADA
        if (language == "KANNADA") {

            title.text =
                "ಅನಾರೋಗ್ಯ ಪ್ರಾಣಿ ವರದಿ"

            etAnimal.hint =
                "ಪ್ರಾಣಿಯ ಹೆಸರು"

            etSymptoms.hint =
                "ಲಕ್ಷಣಗಳು"

            etVillage.hint =
                "ಗ್ರಾಮದ ಹೆಸರು"

            btnSend.text =
                "ವೆಟ್ ಅಲರ್ಟ್ ಕಳುಹಿಸಿ"
        }

        btnSend.setOnClickListener {

            Toast.makeText(
                this,
                "Vet Alert Sent Successfully",
                Toast.LENGTH_LONG
            ).show()

            etAnimal.text.clear()

            etSymptoms.text.clear()

            etVillage.text.clear()
        }
    }
}