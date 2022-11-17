package com.example.weather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //link the textView in which the temperature will be displayed
        textView = findViewById(R.id.textView)
        //create an instance of the Fused Location Provider Client
        btVar1.setOnClickListener {
            getWeather()
        }

    }

    private fun getWeather() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = apiInterface.getWeather(
                    "tashkent,uz",
                    "metric",
                    "979f5419793e1c9607c3bb59ea5ea5b1"
                )
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    Log.e("lat", weatherResponse.toString())
                    textView.text = weatherResponse?.main?.temp.toString()

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { Log.e("Error", it) }
            }
        }

    }
}