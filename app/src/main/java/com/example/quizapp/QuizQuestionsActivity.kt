package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizQuestionsActivity : AppCompatActivity() {
    var index = 0
    var list_of_questions = Data.questions
    var length = list_of_questions.size
    var answers = IntArray(length) { 0 }

    lateinit var status: TextView
    lateinit var question: TextView
    lateinit var option1: Button
    lateinit var option2: Button
    lateinit var option3: Button
    lateinit var option4: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


        status = findViewById(R.id.status)
        question = findViewById(R.id.question)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        option1 = findViewById(R.id.option1)

        setState(index)
    }

    fun setState(index: Int) {
        this.status.text = (index + 1).toString() + "/" + length
        var question = list_of_questions[index]
        this.question.text = question.question
        this.option1.text = question.option1
        this.option2.text = question.option2
        this.option3.text = question.option3
        this.option4.text = question.option4
        var answer = answers[index]
        when (answer) {
            1 -> {

                option1.setBackgroundColor(Color.BLUE)
                option1.setTextColor(Color.WHITE)
                option4.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option3.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option2.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option4.setTextColor(Color.WHITE)
                option3.setTextColor(Color.WHITE)
                option2.setTextColor(Color.WHITE)
                option1.isEnabled = false
                option2.isEnabled = false
                option3.isEnabled = false
                option4.isEnabled = false
            }
            2 -> {
                option2.setBackgroundColor(Color.BLUE)
                option2.setTextColor(Color.WHITE)
                option4.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option3.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option1.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option4.setTextColor(Color.WHITE)
                option3.setTextColor(Color.WHITE)
                option1.setTextColor(Color.WHITE)

                option1.isEnabled = false
                option2.isEnabled = false
                option3.isEnabled = false
                option4.isEnabled = false
            }
            3 -> {
                option3.setBackgroundColor(Color.BLUE)
                option3.setTextColor(Color.WHITE)

                option4.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option2.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option1.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option4.setTextColor(Color.WHITE)
                option2.setTextColor(Color.WHITE)
                option1.setTextColor(Color.WHITE)

                option1.isEnabled = false
                option2.isEnabled = false
                option3.isEnabled = false
                option4.isEnabled = false
            }
            4 -> {
                option4.setBackgroundColor(Color.BLUE)
                option4.setTextColor(Color.WHITE)

                option3.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option2.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option1.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option3.setTextColor(Color.WHITE)
                option2.setTextColor(Color.WHITE)
                option1.setTextColor(Color.WHITE)
                option1.isEnabled = false
                option2.isEnabled = false
                option3.isEnabled = false
                option4.isEnabled = false
            }
            else -> {

                option4.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option3.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option2.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option1.setBackgroundColor(getResources().getColor(R.color.purple_500))
                option4.setTextColor(Color.WHITE)
                option3.setTextColor(Color.WHITE)
                option2.setTextColor(Color.WHITE)
                option1.setTextColor(Color.WHITE)
                option1.isEnabled = true
                option2.isEnabled = true
                option3.isEnabled = true
                option4.isEnabled = true
            }
        }
    }

    fun prevHandle(view: View) {
        index = index - 1
        if (index == -1)
            index = length - 1
        setState(index)
    }

    fun nextHandle(view: View) {
        index = index + 1
        if (index == length)
            index = 0
        setState(index)
    }

    fun option1SelectionHandle(view: View) {
        answers[index] = 1
        Toast.makeText(this, "${(view as Button).text.toString()} selected", Toast.LENGTH_SHORT)
            .show()
        view.setBackgroundColor(Color.BLUE)
        view.setTextColor(Color.WHITE)
        option1.isEnabled = false
        option2.isEnabled = false
        option3.isEnabled = false
        option4.isEnabled = false
    }

    fun option2SelectionHandle(view: View) {
        answers[index] = 2
        Toast.makeText(this, "${(view as Button).text.toString()} selected", Toast.LENGTH_SHORT)
            .show()
        view.setBackgroundColor(Color.BLUE)
        view.setTextColor(Color.WHITE)
        option1.isEnabled = false
        option2.isEnabled = false
        option3.isEnabled = false
        option4.isEnabled = false
    }

    fun option3SelectionHandle(view: View) {
        answers[index] = 3
        Toast.makeText(this, "${(view as Button).text.toString()} selected", Toast.LENGTH_SHORT)
            .show()

        view.setBackgroundColor(Color.BLUE)
        view.setTextColor(Color.WHITE)
        option1.isEnabled = false
        option2.isEnabled = false
        option3.isEnabled = false
        option4.isEnabled = false
    }

    fun option4SelectionHandle(view: View) {
        answers[index] = 4
        Toast.makeText(this, "${(view as Button).text.toString()} selected", Toast.LENGTH_SHORT)
            .show()
        view.setBackgroundColor(Color.BLUE)
        view.setTextColor(Color.WHITE)
        option1.isEnabled = false
        option2.isEnabled = false
        option3.isEnabled = false
        option4.isEnabled = false
    }

    fun submitHandle(view: View) {
        val total = length
        var right = 0
        var lefted = 0
        var wrong = 0

        for (x in 0 until total) {
            var question = list_of_questions[x]
            var answer = answers[x]

            when (answer) {
                1 -> {
                    if (question.option1.equals(question.right_option)) {
                        right++
                    } else {
                        wrong++
                    }
                }
                2 -> {
                    if (question.option2.equals(question.right_option)) {
                        right++
                    } else {
                        wrong++

                    }
                }
                3 -> {
                    if (question.option3.equals(question.right_option)) {
                        right++
                    } else {
                        wrong++
                    }
                }
                4 -> {
                    if (question.option4.equals(question.right_option)) {
                        right++
                    } else {
                        wrong++
                    }
                }
                else -> {
                    lefted++
                }
            }
        }

        Toast.makeText(
            this,
            "Total $total , right $right , unattempted $lefted , wrong $wrong",
            Toast.LENGTH_SHORT
        ).show()

        var intent: Intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("total","" + total)
        intent.putExtra("right","" + right)
        intent.putExtra("unattempted","" + lefted)
        intent.putExtra("wrong","" + wrong)
        startActivity(intent)
        finish()
    }
}