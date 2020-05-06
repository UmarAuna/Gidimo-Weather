package com.poem.gidimoweather.network

import com.poem.gidimoweather.model.dailyforecast.DailyForecast
import com.poem.gidimoweather.model.dayforecast.DayForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("forecast/daily?")
    fun getDailyForecast(
        @Query("APPID") apiKey: String?,
        @Query("q") q: String?,
        @Query("units") units: String?,
        @Query("cnt") cnt: Int
    ): Call<DailyForecast?>

    @GET("forecast/?")
    fun getDayForeCast(
        @Query("APPID") apiKey: String?,
        @Query("q") q: String?,
        @Query("mode") mode: String?,
        @Query("units") units: String?,
        @Query("cnt") cnt: Int
    ): Call<DayForecast?>
}