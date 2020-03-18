package com.vokasi.abyan_webservice

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_identitas_masjid.home
import kotlinx.android.synthetic.main.activity_slideshow.*

class slideshow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)
        val context = this


        home.setOnClickListener {
            val intent= Intent(context, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
