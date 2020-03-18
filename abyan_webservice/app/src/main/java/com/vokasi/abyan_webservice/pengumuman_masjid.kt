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
import kotlinx.android.synthetic.main.activity_identitas_masjid.teks1
import kotlinx.android.synthetic.main.activity_jadwal_sholat.*
import kotlinx.android.synthetic.main.activity_pengumuman_masjid.*
import org.json.JSONObject

class pengumuman_masjid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman_masjid)
        val context = this

        home.setOnClickListener {
            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        getdariserver()
    }

    fun getdariserver(){

        AndroidNetworking.get("http://192.168.43.68/jamsholat/pengumuman_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")

                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_pengumuman"))

                        judul_pengumuman.setText(jsonObject.optString("judul_pengumuman"))
                        isi_pengumuman.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("err", anError.toString())
                }
            })
    }
}
