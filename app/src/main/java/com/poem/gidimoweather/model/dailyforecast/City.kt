package com.poem.gidimoweather.model.dailyforecast

import com.google.gson.annotations.SerializedName

data class City(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("coord")
	val coord: Coord? = null,

	@field:SerializedName("timezone")
	val timezone: Float? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Float? = null,

	@field:SerializedName("population")
	val population: Float? = null
)