package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException


class QuizSelection : AppCompatActivity() {
    lateinit var list: ListView;
    var mylist = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_selection)
        list = findViewById(R.id.list)
//        list.onItemClickListener = ItemCl

        var url = Data.URL + "/quizs"
        var queue = Volley.newRequestQueue(this)


        var jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null, { response ->
                try {
                    val jsonArray = JSONArray(response.toString())
                    for (i in 0 until jsonArray.length()) {
                        val json_response = jsonArray.getJSONArray(i)
                        val obj = json_response.getString(0)
                        mylist.add(obj)
                    }

                    var adapter = ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        mylist
                    )
                    list.adapter = adapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Log.d("suck error", "" + error.toString())
            }
        )
        queue.add(jsonArrayRequest)

        list.setOnItemClickListener { parent, view, position, id ->
            queue.add(JsonArrayRequest(
                Request.Method.GET,
                Data.URL + "/quizquestions/" + mylist.get(position),
                null,
                { response ->
                    try {
                        val jsonArray = JSONArray(response.toString())
//                        [4,"2022-12-04 17:24:13","When did the prime minister issue his statement?","Friday","Monday","Wednesday","Saturday","Friday"]
//                        jsonArray.get(0)
                        var length = jsonArray.length()
                        for (j in 0 until length) {
                            var rec = jsonArray.getJSONArray(j)
                            Data.questions.add(
                                Question(
                                    rec.getInt(0),
                                    rec.getString(2),
                                    rec.getString(3),
                                    rec.getString(4),
                                    rec.getString(5),
                                    rec.getString(6),
                                    rec.getString(7)
                                )
                            )
                        }
                        startActivity(Intent(this, QuizQuestionsActivity::class.java));
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                { error ->
                    Log.d("suck error", "" + error.toString())
                }
            ))
        }
    }
}
