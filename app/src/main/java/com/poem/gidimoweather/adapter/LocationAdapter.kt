package com.poem.gidimoweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poem.gidimoweather.R
import com.poem.gidimoweather.model.dailyforecast.ListItem
import java.text.SimpleDateFormat
import java.util.*


class LocationAdapter(var context: Context, articlesItems: ArrayList<ListItem>) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    var articlesItems: ArrayList<ListItem> = articlesItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.textTitle.text = "${articlesItems[position].temp?.day.toString()}°"
        holder.textViewDayOfWeek.text = getDateTime(articlesItems[position].dt.toString())
        holder.textTempMax.text = "${articlesItems[position].temp?.max.toString()}°"
        holder.textTempMin.text = "${articlesItems[position].temp?.min.toString()}°"

        when (articlesItems[position].weather?.get(0)?.icon.toString()) {
            "01d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a01d_svg)
            "01n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a01n_svg)
            "02d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a02d_svg)
            "02n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a02n_svg)
            "03d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a03d_svg)
            "03n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a03n_svg)
            "04d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a04d_svg)
            "04n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a04n_svg)
            "09d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a09d_svg)
            "09n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a09n_svg)
            "10d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a10d_svg)
            "10n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a10n_svg)
            "11d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a11d_svg)
            "11n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a11n_svg)
            "1232n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a1232n_svg)
            "13d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a13d_svg)
            "13n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a13n_svg)
            "50d" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a50d_svg)
            "50n" -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.a50n_svg)

            else -> holder.imageViewForecastIcon.setBackgroundResource(R.drawable.ic_launcher_foreground)
        }

        Glide.with(context).load(articlesItems[position].weather?.get(0)?.icon.toString()).into(holder.imageViewForecastIcon)


    }

    override fun getItemCount(): Int {
        return articlesItems.size
    }

    inner class LocationViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textTitle: TextView = itemView.findViewById(R.id.textViewTemp)
        var textViewDayOfWeek: TextView = itemView.findViewById(R.id.textViewDayOfWeek)
        var textTempMin: TextView = itemView.findViewById(R.id.textTempMin)
        var textTempMax: TextView = itemView.findViewById(R.id.textTempMax)
        var imageViewForecastIcon: ImageView = itemView.findViewById(R.id.imageViewForecastIcon)


    }

    private fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("EEE, d MMM yyyy")
            val netDate = Date(s.toLong() * 1000 ).addDays(1)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun Date.addDays(numberOfDaysToAdd: Int): Date{
        return Date(this.time + numberOfDaysToAdd * DayInMilliSec)
    }

    companion object {
        const val DayInMilliSec = 86400000
    }

}