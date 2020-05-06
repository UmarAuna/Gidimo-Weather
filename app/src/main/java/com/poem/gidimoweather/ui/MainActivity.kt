package com.poem.gidimoweather.ui


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.Glide
import com.poem.gidimoweather.R
import com.poem.gidimoweather.adapter.LocationAdapter
import com.poem.gidimoweather.databinding.ActivityMainBinding
import com.poem.gidimoweather.model.dailyforecast.DailyForecast
import com.poem.gidimoweather.model.dailyforecast.ListItem
import com.poem.gidimoweather.model.dayforecast.DayForecast
import com.poem.gidimoweather.utils.Is_Network
import com.poem.gidimoweather.viewmodel.DayViewModel
import com.poem.gidimoweather.viewmodel.DailyForecastViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dashboard_forecast.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var dailyForecastViewModel: DailyForecastViewModel? = null
    private var dayViewModel: DayViewModel? = null
    var articlesItems: ArrayList<ListItem> = ArrayList<ListItem>()
    var locationAdapter: LocationAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dailyForecastViewModel = ViewModelProvider(this).get(DailyForecastViewModel::class.java)
        dailyForecastViewModel?.init()

        dayViewModel = ViewModelProvider(this).get(DayViewModel::class.java)
        dayViewModel?.init()

        setupRecyclerView()
        getDaily()
        getDay()


        binding.swiperefreshMain.setColorScheme(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);

        binding.swiperefreshMain.setOnRefreshListener(OnRefreshListener {
            if (Is_Network().isOnline(this)) {
                getDaily()
                getDay()
                binding.listError.visibility = View.GONE
            } else {
                binding.listError.visibility = View.VISIBLE
                binding.swiperefreshMain.isRefreshing = false
            }
        })
    }


    fun getDaily(){
        binding.swiperefreshMain.isRefreshing = true
        dailyForecastViewModel?.newsAPI?.observe(this, object : Observer,
            androidx.lifecycle.Observer<DailyForecast?> {
            override fun onChanged(dataModels: DailyForecast?) {
                articlesItems.clear()
                val newsArticles: List<ListItem> = dataModels?.list as List<ListItem>
                articlesItems.addAll(newsArticles)
                locationAdapter!!.notifyDataSetChanged()

            }

            override fun update(o: Observable?, arg: Any?) {
            }
        })
        binding.swiperefreshMain.isRefreshing = false
    }

    fun getDay(){
        binding.swiperefreshMain.isRefreshing = true
        dayViewModel?.dayAPI?.observe(this, object : Observer,
            androidx.lifecycle.Observer<DayForecast?> {
            override fun onChanged(dataModels: DayForecast?) {
                textViewTemperature.text = "${dataModels?.list?.get(0)?.main?.temp.toString()}°"
                textViewWeatherMain.text = dataModels?.list?.get(0)?.weather?.get(0)?.main.toString()
                textViewHumidity.text = "${dataModels?.list?.get(0)?.main?.humidity.toString()}°"
                chipToday.text = "Today, ${dataModels?.city?.name}"

                when (dataModels?.list?.get(0)?.weather?.get(0)?.icon.toString()) {
                    "01d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a01d_svg)
                    "01n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a01n_svg)
                    "02d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a02d_svg)
                    "02n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a02n_svg)
                    "03d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a03d_svg)
                    "03n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a03n_svg)
                    "04d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a04d_svg)
                    "04n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a04n_svg)
                    "09d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a09d_svg)
                    "09n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a09n_svg)
                    "10d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a10d_svg)
                    "10n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a10n_svg)
                    "11d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a11d_svg)
                    "11n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a11n_svg)
                    "1232n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a1232n_svg)
                    "13d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a13d_svg)
                    "13n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a13n_svg)
                    "50d" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a50d_svg)
                    "50n" -> imageViewWeatherIcon.setBackgroundResource(R.drawable.a50n_svg)

                    else -> imageViewWeatherIcon.setBackgroundResource(R.drawable.ic_launcher_foreground)
                }

                Glide.with(this@MainActivity).load(dataModels?.list?.get(0)?.weather?.get(0)?.icon.toString()).into(imageViewWeatherIcon)
                binding.swiperefreshMain.isRefreshing = false
            }

            override fun update(o: Observable?, arg: Any?) {
            }
        })
    }


    private fun setupRecyclerView() {
        locationAdapter = LocationAdapter(this@MainActivity, articlesItems)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerForecast.layoutManager = layoutManager
        binding.recyclerForecast.adapter = locationAdapter
        binding.recyclerForecast.itemAnimator = DefaultItemAnimator()
        binding.recyclerForecast.isNestedScrollingEnabled = true
        locationAdapter!!.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the main; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_setting) {
            val a = Intent(this@MainActivity, Settings::class.java)
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}


