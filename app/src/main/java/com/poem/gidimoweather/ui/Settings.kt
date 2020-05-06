package com.poem.gidimoweather.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.widget.Toast
import com.poem.gidimoweather.databinding.ActivitySettingsBinding
import com.poem.gidimoweather.utils.SharedPreference

class Settings : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = "Settings"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sharedPreference = SharedPreference(this)

        if (sharedPreference.getValueString("location")!=null) {
            binding.editTextCurrentLocation.text = Editable.Factory.getInstance().newEditable(sharedPreference.getValueString("location")!!)
        }else{
            binding.editTextCurrentLocation.hint="NO value found"
        }

        binding.btnUpdateResetLocation.setOnClickListener{
            val currentLocation:String = binding.editTextCurrentLocation.text.toString()
            sharedPreference.save("location",currentLocation)
            Toast.makeText(this@Settings,"location updated", Toast.LENGTH_SHORT).show()

            val validlocation = isValidCurrentLocation(currentLocation)

            if (!validlocation) return@setOnClickListener


            val a = Intent(this@Settings, MainActivity::class.java)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
