package com.poem.gidimoweather.model.dailyforecast

import com.google.gson.annotations.SerializedName

data class DailyForecast(

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("cnt")
	val cnt: Int? = null,

	@field:SerializedName("cod")
	val cod: Int? = null,

	@field:SerializedName("message")
	val message: Double? = null,

	@field:SerializedName("list")
	val list: List<ListItem?>? = null
)