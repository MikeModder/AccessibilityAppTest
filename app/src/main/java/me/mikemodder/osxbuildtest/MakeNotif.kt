package me.mikemodder.osxbuildtest

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by mike on 5/19/18.
 */

class MakeNotif : AsyncTask<Activity, Void, Void?>() {
    override fun doInBackground(vararg params: Activity?): Void? {
        // Let's make us some toast... in the background?
        val TAG = "MakeToast:doInBackgr"
        val parent = params.first()
        val ctx = parent?.applicationContext
        try {
            val client = OkHttpClient()
            var req = Request.Builder()
                    .url("https://google.com/humans.txt")
                    .build()
            var res = client.newCall(req).execute()
            val notifTxt = res.body()!!.string()
            Log.d(TAG, "Got response: %s".format(notifTxt))
            var nb = NotificationCompat.Builder(ctx)
                    .setSmallIcon(R.drawable.abc_popup_background_mtrl_mult)
                    .setContentTitle("humans.txt")
                    .setContentText(notifTxt)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()
            var notifMan = NotificationManagerCompat.from(ctx)
            notifMan.notify(2, nb)
            return null
        } catch (e: Throwable){
            parent?.runOnUiThread({
                Toast.makeText(ctx, "Oh snap, we hit a snag!\n%s".format(e.message), Toast.LENGTH_LONG).show()
            })
        }
        return null

    }

}