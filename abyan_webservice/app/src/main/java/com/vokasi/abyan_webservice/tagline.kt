package com.vokasi.abyan_webservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_identitas_masjid.home
import kotlinx.android.synthetic.main.activity_jadwal_sholat.*
import kotlinx.android.synthetic.main.activity_tagline.*
import org.json.JSONObject

class tagline : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagline)
        val context = this

        home.setOnClickListener {
            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        getdariserver()
    }
    fun getdariserver(){

        AndroidNetworking.get("http://192.168.43.68/jamsholat/tagline.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")

                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("isi_tagline"))

                        isi_tagline.setText(jsonObject.optString("isi_tagline"))

                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("err", anError.toString())
                }
            })
    }
}
