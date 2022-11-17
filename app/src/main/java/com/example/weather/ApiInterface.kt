package com.example.weather

import com.example.weather.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//const val BASE_URL = "https://api.openweathermap.org/data/2.5/weather
// ?q=dhaka,bd&units=metric&appid=8118ed6ee68db2debfaaa5a44c832918"

interface ApiInterface {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Response<WeatherResponse>
}