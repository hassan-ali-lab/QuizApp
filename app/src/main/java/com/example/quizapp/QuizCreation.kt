package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class QuizCreation : AppCompatActivity() {
    var URL = Data.URL + "/quiz/"
    lateinit var quiz_name: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_creation)
        quiz_name = findViewById(R.id.quiz_name)

    }

    fun onClickCreateQuiz(view: View) {
        URL += quiz_name.text.toString()
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, URL, null, { response ->
                if (JSONObject(response.toString()).get("result").equals("passed")) {
                    var intent: Intent = Intent(this, QuestionCreation::class.java)
                    intent.putExtra("tableName",quiz_name.text.toString())
                    startActivity(intent)
                    finish()

                }
            },
            { error ->
                Log.d("suck error", "" + error.toString())
            }
        )
        queue.add(jsonObjectRequest)

    }
}