package com.example.gramavaxi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tutorial)

        val tvTitle =
            findViewById<TextView>(R.id.tvTutorialTitle)

        val tvSteps =
            findViewById<TextView>(R.id.tvTutorialSteps)

        val language =
            intent.getStringExtra("LANG")

        // KANNADA
        if (language == "KANNADA") {

            tvTitle.text =
                "ಆಪ್ ಬಳಸುವ ವಿಧಾನ"

            tvSteps.text =
                "1. ಭಾಷೆ ಆಯ್ಕೆ ಮಾಡಿ\n\n" +
                        "2. ನೋಂದಣಿ ಅಥವಾ ಲಾಗಿನ್ ಮಾಡಿ\n\n" +
                        "3. ಪ್ರಾಣಿ ವಿವರ ಸೇರಿಸಿ\n\n" +
                        "4. ಪ್ರಾಣಿ ಫೋಟೋ ಅಪ್ಲೋಡ್ ಮಾಡಿ\n\n" +
                        "5. ಲಸಿಕೆ ದಿನಾಂಕ ನಮೂದಿಸಿ\n\n" +
                        "6. ಜ್ಞಾಪನೆ ಸೂಚನೆಗಳನ್ನು ಪಡೆಯಿರಿ\n\n" +
                        "7. ಅನಾರೋಗ್ಯ ಪ್ರಾಣಿ ವರದಿ ಮಾಡಿ"
        }
    }
}