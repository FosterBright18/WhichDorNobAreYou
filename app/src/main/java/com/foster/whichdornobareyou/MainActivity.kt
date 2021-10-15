package com.foster.whichdornobareyou

import android.annotation.SuppressLint
import android.graphics.Color
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

    lateinit var trueButton : Button
    lateinit var falseButton : Button
    lateinit var question : TextView
    lateinit var feedBack : TextView
    lateinit var quiz : Quiz


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()
        setListeners()

        // reading the kson from the raw folder

        // step 1: open the raw resource as an InputStream
        val inputStream = resources.openRawResource(R.raw.english)
        val jsonText = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        Log.d(TAG, "onCreate: $jsonText")

        // use gson to convert the jsonText into a List<Question>
        val gson = Gson()
        val type = object : TypeToken<List<Question>>() { }.type
        val english = gson.fromJson<List<Question>>(jsonText, type)
        Log.d(TAG, "onCreate: \n${english.toString()}")


        quiz = Quiz(english)


        // any quiz related action is a duty of quiz class

        // main activity is incharge of ui and passing information to and from the quiz class



        question.text = "${quiz.qCurrent().question}"


        trueButton.setBackgroundColor((resources.getColor(R.color.buttoncolor)))
        falseButton.setBackgroundColor((resources.getColor(R.color.buttoncolor)))

    }

    private fun setListeners() {
        trueButton.setOnClickListener{
            qStart(true)
        }
        falseButton.setOnClickListener{
            qStart(false)
        }
    }


    private fun qStart(bool: Boolean) {
        if(quiz.qRemaining()) {
            question.text = "${quiz.qCurrent().question}"
            Toast.makeText(MainActivity@ this, "${quiz.updateScore(bool)}", Toast.LENGTH_SHORT)
                .show()
        } else{
            Toast.makeText(MainActivity@ this, "${quiz.updateScore(bool)}", Toast.LENGTH_SHORT)
            question.text = "${resources.getString(R.string.final1)} ${quiz.score} ${resources.getString(R.string.final2)}\n" +
                    "${resources.getString(R.string.final3)} ${quiz.finalScore(quiz.score)}"
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }
    }


    private fun wireWidgets() {
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        question = findViewById(R.id.textView_main_question)
        feedBack = findViewById(R.id.textView_main_feedBack)
    }
}