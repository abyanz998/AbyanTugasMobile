package com.vokasi.abyan_webservice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.hitomi.cmlibrary.CircleMenu
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context=this
        AndroidNetworking.initialize(getApplicationContext());

        getdariserver()
        marku.setSelected(true)

        jadwal.setOnClickListener {
            val intent= Intent(context,jadwal_sholat::class.java)
            startActivity(intent)
            finish()
        }


        id_masjid.setOnClickListener {
            val intent= Intent(context,identitas_masjid::class.java)
            startActivity(intent)
            finish()
        }

        pengumuman.setOnClickListener {
            val intent= Intent(context,pengumuman_masjid::class.java)
            startActivity(intent)
            finish()
        }

        slide.setOnClickListener {
            val intent= Intent(context, slideshow::class.java)
            startActivity(intent)
            finish()
        }

        tag.setOnClickListener {
            val intent= Intent(context,tagline::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun getdariserver(){

        AndroidNetworking.get("http://192.168.43.68/jamsholat/marquee.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")

                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("isi_marquee"))

                        marku.setText(jsonObject.optString("isi_marquee"))

                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("err", anError.toString())
                }
            })
    }

}

//class MainActivity : AppCompatActivity() {
//
//    companion object {
//        fun newInstance():identitas_masjid = identitas_masjid()
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val circleMenu = findViewById<View>(R.id.mantap) as CircleMenu
//        val arrayName = arrayOf(identitas_masjid, "megaphone", "mosque", "pray", "tagline")
//        val context = this
//        circleMenu.setMainMenu(
//            Color.parseColor("#CDCDCD"),
//            R.drawable.ic_menu_black_24dp,
//            R.drawable.ic_indeterminate_check_box_black_24dp
//        )
//            .addSubMenu(Color.parseColor("#258CFF"), R.drawable.slideshow)
//            .addSubMenu(Color.parseColor("#6d4c41"), R.drawable.megaphone)
//            .addSubMenu(Color.parseColor("#ff0000"), R.drawable.mosque)
//            .addSubMenu(Color.parseColor("#ff0000"), R.drawable.pray)
//            .addSubMenu(Color.parseColor("#008577"), R.drawable.tagline)
//            .setOnMenuSelectedListener { index ->
//                val intent= Intent(context,arrayName[index]::class.java)
//                startActivity(intent)
//                finish()
//            }
//    }
//}
