package me.mikemodder.osxbuildtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "MainAct:onCreate"
        super.onCreate(savedInstanceState)
        //val jsonMediaType = MediaType.parse("application/json; charset=utf-8")
        setContentView(R.layout.activity_main)
        notifBtn.setOnClickListener {
            Log.d(TAG, "Button clicked, running MakeToast().execute()")
            MakeNotif().execute(this)
        }
    }
}