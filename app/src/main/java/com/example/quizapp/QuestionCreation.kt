package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException


class QuestionCreation : AppCompatActivity() {
    lateinit var tablename: String;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_creation)
        tablename = intent.getStringExtra("tableName").toString()
        val dropdown = findViewById<Spinner>(R.id.spinner)
        val items = arrayOf("1", "2", "3", "4")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        dropdown.adapter = adapter
        Log.d("test", tablename)
    }


    fun onClickCreateQuestion(view: View) {


        var question = findViewById<EditText>(R.id.question).text.toString()
        var option1 = findViewById<EditText>(R.id.option1).text.toString()
        var option2 = findViewById<EditText>(R.id.option2).text.toString()
        var option3 = findViewById<EditText>(R.id.option3).text.toString()
        var option4 = findViewById<EditText>(R.id.option4).text.toString()
        var correct_option = findViewById<Spinner>(R.id.spinner).selectedItem.toString()
        Log.d("afsafsag", correct_option)
        try {


            val jsonBody = JSONObject()
            when (correct_option) {
                "1" -> {
                    jsonBody.put("correct_option", option1)
                }
                "2" -> {
                    jsonBody.put("correct_option", option2)
                }
                "3" -> {
                    jsonBody.put("correct_option", option3)
                }
                "4" -> {
                    jsonBody.put("correct_option", option4)
                }
            }
            val requestQueue = Volley.newRequestQueue(this)
            val URL = Data.URL + "/question"
            jsonBody.put("table_name", tablename)
            jsonBody.put("question", question)
            jsonBody.put("option1", option1)
            jsonBody.put("option2", option2)
            jsonBody.put("option3", option3)
            jsonBody.put("option4", option4)
            val requestBody = jsonBody.toString()
            val stringRequest: JsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                URL, null,
                Response.Listener { response ->
                    Toast.makeText(
                        this,
                        "Success is " + response.getBoolean("success").toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    findViewById<EditText>(R.id.question).setText("")
                    findViewById<EditText>(R.id.option1).setText("")
                    findViewById<EditText>(R.id.option2).setText("")
                    findViewById<EditText>(R.id.option3).setText("")
                    findViewById<EditText>(R.id.option4).setText("")

                },
                Response.ErrorListener { error -> Log.e("VOLLEY", error.toString()) }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray {
                    return try {
                        requestBody.toByteArray(charset("utf-8"))
                    } catch (uee: UnsupportedEncodingException) {
                        VolleyLog.wtf(
                            "Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody,
                            "utf-8"
                        )
                        return "".toByteArray()
                    }
                }

            }
            requestQueue.add(stringRequest)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}