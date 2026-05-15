package com.example.gramavaxi

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.Calendar
import java.util.concurrent.TimeUnit

class RegisterAnimalActivity : AppCompatActivity() {

    private lateinit var imgAnimal: ImageView
    private var imageUri: Uri? = null

    companion object {
        const val IMAGE_PICK_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register_animal)

        val etName =
            findViewById<EditText>(R.id.etName)

        val etBreed =
            findViewById<EditText>(R.id.etBreed)

        val etAge =
            findViewById<EditText>(R.id.etAge)

        val etVaccine =
            findViewById<EditText>(R.id.etVaccine)

        val btnSave =
            findViewById<Button>(R.id.btnSave)

        val btnUpload =
            findViewById<Button>(R.id.btnUpload)

        val title =
            findViewById<TextView>(
                R.id.tvRegisterTitle
            )

        imgAnimal =
            findViewById(R.id.imgAnimal)

        createNotificationChannel()

        // LANGUAGE
        val language =
            intent.getStringExtra("LANG")

        if (language == "KANNADA") {

            title.text = "ಪ್ರಾಣಿ ನೋಂದಣಿ"

            etName.hint = "ಪ್ರಾಣಿಯ ಹೆಸರು"

            etBreed.hint = "ಜಾತಿ"

            etAge.hint = "ವಯಸ್ಸು"

            etVaccine.hint = "ಲಸಿಕೆ ದಿನಾಂಕ"

            btnUpload.text =
                "ಪ್ರಾಣಿ ಫೋಟೋ ಅಪ್ಲೋಡ್"

            btnSave.text =
                "ಪ್ರಾಣಿ ಉಳಿಸಿ"
        }

        // OPEN GALLERY
        btnUpload.setOnClickListener {

            val intent =
                Intent(Intent.ACTION_OPEN_DOCUMENT)

            intent.addCategory(
                Intent.CATEGORY_OPENABLE
            )

            intent.type = "image/*"

            intent.flags =
                Intent.FLAG_GRANT_READ_URI_PERMISSION or
                        Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION

            startActivityForResult(
                intent,
                IMAGE_PICK_CODE
            )
        }

        // SAVE
        btnSave.setOnClickListener {

            val name =
                etName.text.toString()

            val breed =
                etBreed.text.toString()

            val age =
                etAge.text.toString()

            val vaccineDate =
                etVaccine.text.toString()

            // DATE VALIDATION
            try {

                val parts =
                    vaccineDate.split("-")

                val enteredDay =
                    parts[0].toInt()

                val enteredMonth =
                    parts[1].toInt()

                var enteredYear =
                    parts[2].toInt()

                if (parts[2].length == 2) {

                    enteredYear += 2000
                }

                val currentCalendar =
                    Calendar.getInstance()

                val currentDay =
                    currentCalendar.get(
                        Calendar.DAY_OF_MONTH
                    )

                val currentMonth =
                    currentCalendar.get(
                        Calendar.MONTH
                    ) + 1

                val currentYear =
                    currentCalendar.get(
                        Calendar.YEAR
                    )

                val isFutureDate =
                    enteredYear > currentYear ||

                            (
                                    enteredYear == currentYear &&
                                            enteredMonth > currentMonth
                                    ) ||

                            (
                                    enteredYear == currentYear &&
                                            enteredMonth == currentMonth &&
                                            enteredDay > currentDay
                                    )

                if (isFutureDate) {

                    Toast.makeText(
                        this,
                        "❌ Invalid Vaccination Date",
                        Toast.LENGTH_LONG
                    ).show()

                    return@setOnClickListener
                }

                // SAVE ANIMAL
                val animal =
                    AnimalEntity(
                        name,
                        breed,
                        age,
                        vaccineDate,
                        imageUri.toString()
                    )

                DatabaseHelper.animalDatabase.add(
                    animal
                )

                // NEXT DUE DATE
                var nextMonth =
                    enteredMonth + 6

                var nextYear =
                    enteredYear

                if (nextMonth > 12) {

                    nextMonth -= 12

                    nextYear += 1
                }

                val dueCalendar =
                    Calendar.getInstance()

                dueCalendar.set(
                    nextYear,
                    nextMonth - 1,
                    enteredDay
                )

                val difference =
                    dueCalendar.timeInMillis -
                            currentCalendar.timeInMillis

                val daysRemaining =
                    TimeUnit.MILLISECONDS.toDays(
                        difference
                    )

                // MULTILINGUAL NOTIFICATION
                var notificationMessage = ""

                if (language == "KANNADA") {

                    if (daysRemaining < 0) {

                        notificationMessage =
                            "$name ಲಸಿಕೆ ಅವಧಿ ಮೀರಿದೆ!"

                    } else if (daysRemaining == 0L) {

                        notificationMessage =
                            "$name ಲಸಿಕೆ ಇಂದು ಇದೆ!"

                    } else if (daysRemaining in 1..7) {

                        notificationMessage =
                            "$name ಲಸಿಕೆ $daysRemaining ದಿನಗಳಲ್ಲಿ ಇದೆ"
                    }

                } else {

                    if (daysRemaining < 0) {

                        notificationMessage =
                            "$name vaccine overdue!"

                    } else if (daysRemaining == 0L) {

                        notificationMessage =
                            "$name vaccination is today!"

                    } else if (daysRemaining in 1..7) {

                        notificationMessage =
                            "$name vaccine due in $daysRemaining days"
                    }
                }

                // SHOW NOTIFICATION
                if (notificationMessage.isNotEmpty()) {

                    showReminderNotification(
                        notificationMessage,
                        name.hashCode(),
                        language
                    )
                }

                Toast.makeText(
                    this,
                    "Animal Saved Successfully!",
                    Toast.LENGTH_LONG
                ).show()

                etName.text.clear()
                etBreed.text.clear()
                etAge.text.clear()
                etVaccine.text.clear()

            } catch (e: Exception) {

                Toast.makeText(
                    this,
                    "❌ Enter Date as dd-mm-yyyy",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (
            requestCode == IMAGE_PICK_CODE &&
            resultCode == Activity.RESULT_OK &&
            data != null
        ) {

            imageUri = data.data

            contentResolver
                .takePersistableUriPermission(
                    imageUri!!,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

            imgAnimal.setImageURI(imageUri)
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

    private fun showReminderNotification(
        message: String,
        id: Int,
        language: String?
    ) {

        val title =
            if (language == "KANNADA") {
                "ಲಸಿಕೆ ಜ್ಞಾಪನೆ"
            } else {
                "Vaccination Reminder"
            }

        val builder =
            NotificationCompat.Builder(
                this,
                "vaccine_channel"
            )
                .setSmallIcon(
                    R.mipmap.ic_launcher
                )
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(
                    NotificationCompat.PRIORITY_HIGH
                )

        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        NotificationManagerCompat.from(this)
            .notify(
                id,
                builder.build()
            )
    }
}