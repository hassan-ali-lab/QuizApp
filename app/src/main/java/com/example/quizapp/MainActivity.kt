package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException


class MainActivity : AppCompatActivity() {
    lateinit var password: EditText;
    lateinit var username: EditText;
    lateinit var login_button: Button;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        password = findViewById(R.id.password)
        username = findViewById(R.id.username)
        login_button = findViewById(R.id.login_btn)

        login_button.setOnClickListener {
            var uname = username.text.toString()
            var pass = password.text.toString()
            try {
                val requestQueue = Volley.newRequestQueue(this)
                val URL = Data.URL + "/logins"
                val jsonBody = JSONObject()
                jsonBody.put("username", uname)
                jsonBody.put("password", pass)
                val requestBody = jsonBody.toString()
                val stringRequest: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
                    URL, null,
                    Response.Listener { response ->
                        if (response.getInt("isAdmin") == 0) {
                            var intent: Intent = Intent(this, QuizSelection::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            var intent: Intent = Intent(this, QuizCreation::class.java)
                            startActivity(intent)
                            finish()
                        }
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

//                    override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
//                        var responseString = response.statusCode.toString()
//                        return Response.success(
//                            responseString, HttpHeaderParser.parseCacheHeaders(response)
//                        )
//                    }
                }
                requestQueue.add(stringRequest)
            } catch (e: JSONException) {
                e.printStackTrace()
            }


        }

    }
}