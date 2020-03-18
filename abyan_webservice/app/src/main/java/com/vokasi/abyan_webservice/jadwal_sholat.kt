package com.vokasi.abyan_webservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_jadwal_sholat.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class jadwal_sholat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_sholat)
        val context=this
        AndroidNetworking.initialize(getApplicationContext());

        getdariserver()

        home.setOnClickListener {
            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getdariserver(){

        AndroidNetworking.get("http://192.168.43.68/jamsholat/jadwal_sholat.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")

                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))

                        txt1.setText(jsonObject.optString("shubuh"))
                        txt2.setText(jsonObject.optString("dhuha"))
                        txt3.setText(jsonObject.optString("dhuhur"))
                        txt4.setText(jsonObject.optString("ashar"))
                        txt5.setText(jsonObject.optString("maghrib"))
                        txt6.setText(jsonObject.optString("isha"))
                    }

                }

                override fun onError(anError: ANError) {
                    Log.i("err", anError.toString())
                }
            })
    }

    fun postkerserver(data1:String,data2:String){
        AndroidNetworking.post("http:// / ")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }

}
