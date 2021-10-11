package com.foster.whichdornobareyou

class Quiz(var quest : List<Question>) {
    var qNum = -1
    var score = 0

    public fun qRemaining(): Boolean {
        return quest.size - qNum > 1
    }

    public fun qCurrent(): Question {
        qNum++
        return quest.get(qNum)
    }

    public fun updateScore(answerSelected : Boolean): Boolean{
        if(answerSelected == quest.get(qNum).answer){
            score++
            return true

        } else{
            return false
        }

    }

    fun finalScore(score : Int) : String {
        return when(score) {

            in 0..2 -> "door knob"
            in 3..5 -> "handleset"
            in 6..8 -> "lever"
            in 9..9999 -> "crystal knob"

            else -> "door knob"
        }
        }
    }
