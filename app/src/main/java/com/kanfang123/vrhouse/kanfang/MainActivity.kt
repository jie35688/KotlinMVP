package com.kanfang123.vrhouse.kanfang

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kanfang123.vrhouse.kanfang.view.my.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.main_tv).setOnClickListener({
            startActivity(Intent(this,LoginActivity::class.java))
        })

    }
}
