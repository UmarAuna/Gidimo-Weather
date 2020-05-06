package com.poem.gidimoweather.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poem.gidimoweather.BuildConfig
import com.poem.gidimoweather.model.dayforecast.DayForecast
import com.poem.gidimoweather.repositories.Repository
import com.poem.gidimoweather.utils.SharedPreference
import java.lang.Exception

class DayViewModel(application: Application) : AndroidViewModel(application) {
    private var mutableLiveData: MutableLiveData<DayForecast?>? = null
    private var repository: Repository
    val sharedPreference = SharedPreference(application)
    fun init() {
        if (mutableLiveData != null) {
            return
        }
        repository = Repository.instance!!
        try {
            if (BuildConfig.API_KEY.isEmpty()) {
                Log.d("API_KEY_ERROR", "Key is empty")
            }
        mutableLiveData = repository.getDay(BuildConfig.API_KEY,sharedPreference.getValueString("location")!!, "json" ,"metric",5)
        }catch (e: Exception){
            e.printStackTrace();
        }
    }

    val dayAPI: LiveData<DayForecast?>?
        get() = mutableLiveData
    init {
        repository = Repository(application)
    }
}