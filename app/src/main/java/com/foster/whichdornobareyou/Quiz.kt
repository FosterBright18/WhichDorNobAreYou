package com.foster.whichdornobareyou

import android.content.res.Resources

public class Quiz(var quest: List<Question>) {
    var qNum = -1
    var score = 0

    public fun qRemaining(): Boolean {
        return quest.size - qNum > 1
    }

    public fun qCurrent(): Question {
        qNum++
        return quest.get(qNum)
    }

    public fun updateScore(answerSelected: Boolean): Boolean {
        if (answerSelected == quest.get(qNum).answer) {
            score++
            return true

        } else {
            return false
        }

    }

    fun finalScore(score: Int): String {
        return when (score) {

            in 0..2 -> "${MyApplication.myResources.getString(R.string.doorknob)}"
            in 3..5 -> "${MyApplication.myResources.getString(R.string.handleset)}"
            in 6..8 -> "${MyApplication.myResources.getString(R.string.lever)}"
            in 9..9999 -> "${MyApplication.myResources.getString(R.string.crystalknob)}"

            else -> "${MyApplication.myResources.getString(R.string.doorknob)}"
        }
    }
}
