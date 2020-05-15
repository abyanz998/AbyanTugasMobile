package com.vokasi.progressbar_abyan

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var tContainer: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tContainer = findViewById(R.id.transisi) // buat layout semuanya

        button.setOnClickListener(object : View.OnClickListener {
            var visible = false                                                                     // in juga ga bisa dipakai smartcast broo
            override fun onClick(v: View) {
                Handler().postDelayed({
                    TransitionManager.beginDelayedTransition(tContainer)
                    visible = !visible
                    jos.setVisibility(if (visible) View.VISIBLE else View.GONE)
                    skor.setVisibility(if (visible) View.VISIBLE else View.GONE)
                }, 1500)

            }
        })
    }
}
