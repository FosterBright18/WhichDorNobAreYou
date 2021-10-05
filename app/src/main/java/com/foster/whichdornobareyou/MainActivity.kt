package com.foster.whichdornobareyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var trueButton : Button
    lateinit var falseButton : Button
    lateinit var question : TextView
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()
        setListeners()


    }

    private fun setListeners() {
        trueButton.setOnClickListener{score++}
        falseButton.setOnClickListener{}
    }

    private fun wireWidgets() {
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        question = findViewById(R.id.textView_main_question)
    }
}