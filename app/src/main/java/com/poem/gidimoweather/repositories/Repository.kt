package com.poem.gidimoweather.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.poem.gidimoweather.model.dailyforecast.DailyForecast
import com.poem.gidimoweather.model.dayforecast.DayForecast
import com.poem.gidimoweather.network.APIClient
import com.poem.gidimoweather.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Repository {
    private var application: Application? = null
    private var apiService: APIService? = null

    constructor(application: Application?) {
        this.application = application
    }

    constructor() {
        apiService = APIClient.newsClient?.create(APIService::class.java)
    }

    fun getNews(apiKey: String?, q: String?, units: String?, cnt: Int): MutableLiveData<DailyForecast?>? {
        val weatherData: MutableLiveData<DailyForecast?> = MutableLiveData<DailyForecast?>()
        apiService?.getDailyForecast(apiKey, q, units, cnt)?.enqueue(object : Callback<DailyForecast?> {
            override fun onResponse(call: Call<DailyForecast?>, response: Response<DailyForecast?>) {
                if (response.isSuccessful) {
                    weatherData.value = response.body()
                    Log.d("TAG", ""+response.body())
                }
            }

            override fun onFailure(call: Call<DailyForecast?>, t: Throwable) {
                try {
                    weatherData.value = null
                    Log.d("TAG", "onFailure: $t")
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        })
        return weatherData
    }

    fun getDay(apiKey: String?, q: String?, mode: String? , units: String?, cnt: Int): MutableLiveData<DayForecast?>? {
        val weatherData: MutableLiveData<DayForecast?> = MutableLiveData<DayForecast?>()
        apiService?.getDayForeCast(apiKey, q, mode ,units, cnt)?.enqueue(object : Callback<DayForecast?> {
            override fun onResponse(call: Call<DayForecast?>, response: Response<DayForecast?>) {
                if (response.isSuccessful) {
                    weatherData.value = response.body()
                    Log.d("TAG", ""+response.body())
                }
            }

            override fun onFailure(call: Call<DayForecast?>, t: Throwable) {
                try {
                    weatherData.value = null
                    Log.d("TAG", "onFailure: $t")
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        })
        return weatherData
    }

    companion object {
        private var repository: Repository? = null
        val instance: Repository?
            get() {
                if (repository == null) {
                    repository = Repository()
                }
                return repository
            }
    }
}