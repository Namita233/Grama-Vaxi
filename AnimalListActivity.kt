package com.example.gramavaxi

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.concurrent.TimeUnit

class AnimalListActivity : AppCompatActivity() {

    private lateinit var animalContainer: LinearLayout
    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animal_list)

        animalContainer =
            findViewById(R.id.animalContainer)

        etSearch =
            findViewById(R.id.etSearch)

        showAnimals("")

        etSearch.addTextChangedListener(
            object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    showAnimals(s.toString())
                }
            }
        )
    }

    private fun showAnimals(searchText: String) {

        if (animalContainer.childCount > 2) {

            animalContainer.removeViews(
                2,
                animalContainer.childCount - 2
            )
        }

        for (animal in DatabaseHelper.animalDatabase) {

            if (
                animal.name.contains(searchText, true) ||
                animal.breed.contains(searchText, true)
            ) {

                // IMAGE
                val imageView = ImageView(this)

                try {

                    imageView.setImageURI(
                        Uri.parse(animal.imageUri)
                    )

                } catch (e: Exception) {

                    imageView.setImageResource(
                        R.mipmap.ic_launcher
                    )
                }

                imageView.layoutParams =
                    LinearLayout.LayoutParams(
                        300,
                        300
                    )

                imageView.setPadding(
                    0,
                    20,
                    0,
                    20
                )

                // SAFE DATE PARSING
                var day = 1
                var month = 1
                var year = 2025

                try {

                    val parts =
                        animal.vaccineDate.split("-")

                    day =
                        parts[0].toInt()

                    month =
                        parts[1].toInt()

                    year =
                        if (parts[2].length == 2) {

                            2000 + parts[2].toInt()

                        } else {

                            parts[2].toInt()
                        }

                } catch (e: Exception) {

                    day = 1
                    month = 1
                    year = 2025
                }

                // NEXT DUE DATE
                var nextMonth = month + 6

                var nextYear = year

                if (nextMonth > 12) {

                    nextMonth -= 12

                    nextYear += 1
                }

                val nextDueDate =
                    "📅 Next Due: $day-$nextMonth-$nextYear"

                // CURRENT DATE
                val currentCalendar =
                    Calendar.getInstance()

                // DUE DATE CALENDAR
                val dueCalendar =
                    Calendar.getInstance()

                dueCalendar.set(
                    nextYear,
                    nextMonth - 1,
                    day
                )

                // DAYS DIFFERENCE
                val difference =
                    dueCalendar.timeInMillis -
                            currentCalendar.timeInMillis

                val daysRemaining =
                    TimeUnit.MILLISECONDS.toDays(
                        difference
                    )

                // STATUS ONLY
                var vaccineStatus =
                    "✅ Vaccinated"

                if (daysRemaining < 0) {

                    vaccineStatus =
                        "🚨 Overdue"

                } else if (daysRemaining == 0L) {

                    vaccineStatus =
                        "🚨 Due Today"

                } else if (daysRemaining in 1..7) {

                    vaccineStatus =
                        "⚠️ Due Soon"
                }

                // TEXT
                val textView = TextView(this)

                textView.text =
                    "🐄 Name: ${animal.name}\n" +
                            "Breed: ${animal.breed}\n" +
                            "Age: ${animal.age}\n" +
                            "💉 Vaccination: ${animal.vaccineDate}\n" +
                            "Status: $vaccineStatus\n" +
                            "$nextDueDate"

                textView.textSize = 20f

                textView.setPadding(
                    20,
                    20,
                    20,
                    20
                )

                // DELETE BUTTON
                val deleteButton = Button(this)

                deleteButton.text =
                    "🗑 DELETE"

                deleteButton.setOnClickListener {

                    DatabaseHelper.animalDatabase.remove(animal)

                    showAnimals(searchText)
                }

                // ADD VIEWS
                animalContainer.addView(imageView)
                animalContainer.addView(textView)
                animalContainer.addView(deleteButton)
            }
        }
    }
}