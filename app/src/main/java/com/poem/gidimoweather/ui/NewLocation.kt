package com.poem.gidimoweather.ui

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.poem.gidimoweather.databinding.ActivityNewLocationBinding
import com.poem.gidimoweather.utils.SharedPreference


class NewLocation : AppCompatActivity() {

    private lateinit var binding: ActivityNewLocationBinding
    var prevStarted = "prevStarted"
    @SuppressLint("CommitPrefEdits")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreference = SharedPreference(this)

        binding.btnSaveLocation.setOnClickListener{
            val currentLocation:String = binding.editTextCurrentLocation.text.toString()
            sharedPreference.save("location",currentLocation)
            Toast.makeText(this@NewLocation,"location saved",Toast.LENGTH_SHORT).show()

            val validlocation = isValidCurrentLocation(currentLocation)

            if (!validlocation) return@setOnClickListener


            val a = Intent(this@NewLocation, MainActivity::class.java)
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
            finish()
        }



    }

    override fun onResume() {
        super.onResume()
        val sharedpreferences = getSharedPreferences("Gidimo Weather", Context.MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prevStarted, java.lang.Boolean.TRUE)
            editor.apply()
        } else {
            val a = Intent(this@NewLocation, MainActivity::class.java)
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
            finish()
        }
    }



    private fun isValidCurrentLocation(location: String): Boolean {
        if (location == "") {
            binding.editTextCurrentLocation.error = "Please enter current location"
            return false
        }
        return true
    }



}
