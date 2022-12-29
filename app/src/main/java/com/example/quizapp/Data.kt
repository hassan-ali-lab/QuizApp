package com.example.quizapp

import android.R
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

class Data {
    companion object {
        val URL = "https://5203-121-52-154-72.ap.ngrok.io"
        var questions = ArrayList<Question>()
    }
}