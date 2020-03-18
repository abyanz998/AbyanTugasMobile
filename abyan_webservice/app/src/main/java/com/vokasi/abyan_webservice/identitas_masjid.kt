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
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class identitas_masjid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas_masjid)
        val context = this



        home.setOnClickListener {
            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        getdariserver()

        update.setOnClickListener {
            var data_namamasjid = nama_masjid.text.toString()
            var data_alamatmasjid = alamat_masjid.text.toString()

            postkerserver(data_namamasjid, data_alamatmasjid)

            val intent= getIntent()
            startActivity(intent)
            finish()
        }

    }

    fun getdariserver(){

        AndroidNetworking.get("http://192.168.43.68/jamsholat/identitas_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")

                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("id_identitas"))
                        Log.e("_kotlinTitle", jsonObject.optString("nama_masjid"))

                        teks1.setText(jsonObject.optString("nama_masjid"))
                        teks2.setText(jsonObject.optString("alamat_masjid"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("err", anError.toString())
                }
            })
    }

    fun postkerserver(data_namamasjid:String,data_alamatmasjid:String){
        AndroidNetworking.post("http://192.168.43.68/jamsholat/proses-identitas.php")
            .addBodyParameter("nama_masjid", data_namamasjid)
            .addBodyParameter("alamat_masjid", data_alamatmasjid)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                Log.i("Teguh", "MANTAP")
                }

                override fun onError(anError: ANError?) {
                    Log.i("Teguh", "BOCOR")
                }
            })
    }

}
