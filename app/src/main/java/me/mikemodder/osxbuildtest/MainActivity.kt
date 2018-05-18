package me.mikemodder.osxbuildtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notifBtn.setOnClickListener {
            var nb = NotificationCompat.Builder(super.getApplicationContext())
                    .setSmallIcon(R.drawable.abc_popup_background_mtrl_mult)
                    .setContentTitle("test")
                    .setContentText("literally go live your life to the fullest")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()
            var notifMan = NotificationManagerCompat.from(super.getApplicationContext())
            notifMan.notify(2, nb)
        }
    }
}