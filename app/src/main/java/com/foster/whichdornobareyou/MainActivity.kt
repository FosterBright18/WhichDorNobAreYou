package com.foster.whichdornobareyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var trueButton: Button
    lateinit var falseButton: Button
    lateinit var questionTextView: TextView
    lateinit var quiz: Quiz


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()
        setListeners()

        // reading the json from the raw folder

        // step 1: open the raw resource as an InputStream
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonText")

        // use gson to convert the jsonText into a List<Question>
        val gson = Gson()
        val type = object : TypeToken<List<Question>>() {}.type
        val questions = gson.fromJson<List<Question>>(jsonText, type)
        Log.d(TAG, "onCreate: \n${questions.toString()}")


        quiz = Quiz(questions)


        // any quiz related action is a duty of quiz class

        // main activity is incharge of ui and passing information to and from the quiz class


        questionTextView.text = "${quiz.qCurrent().question}"


    }

    private fun setListeners() {
        trueButton.setOnClickListener {
            qStart(true)
        }
        falseButton.setOnClickListener {
            qStart(false)
        }
    }


    private fun qStart(bool: Boolean) {
        if (quiz.qRemaining()) {
            questionTextView.text = "${quiz.qCurrent().question}"
            Toast.makeText(MainActivity@ this, "${quiz.updateScore(bool)}",
                Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(MainActivity@ this, "${quiz.updateScore(bool)}",
                Toast.LENGTH_SHORT).show()
            questionTextView.text =
                "${resources.getString(R.string.final1)} ${quiz.score}" +
                        " ${resources.getString(R.string.final2)}\n" +
                        "${resources.getString(R.string.final3)} ${quiz.finalScore(quiz.score)}"
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }
    }


    private fun wireWidgets() {
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        questionTextView = findViewById(R.id.textView_main_question)
    }
}