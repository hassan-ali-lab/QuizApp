package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var total = intent.getStringExtra("total")
        var right = intent.getStringExtra("right")
        var unattempted = intent.getStringExtra("unattempted")
        var wrong = intent.getStringExtra("wrong")
        var t = findViewById<TextView>(R.id.total)
        t.text = total
        var r = findViewById<TextView>(R.id.right)
        r.text = right
        var w = findViewById<TextView>(R.id.wrong)
        w.text = wrong
        var u = findViewById<TextView>(R.id.unattempted)
        u.setText(unattempted)
    }
}