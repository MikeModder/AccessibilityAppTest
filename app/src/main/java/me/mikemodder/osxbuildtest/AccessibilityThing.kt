package me.mikemodder.osxbuildtest

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast


/**
 * Created by mike on 5/17/18.
 */

class AccessibilityThing : AccessibilityService() {
    val TAG = "ACCS"
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if(event == null) { Log.d(TAG, "null event"); return }
        val eventType = event?.eventType
        when(eventType){
            AccessibilityEvent.TYPE_VIEW_FOCUSED -> {
                Log.d(TAG, "Got VIEW_FOCUSED event! Text " + event.text)
                var appname = packageManager.getApplicationLabel(packageManager.getApplicationInfo(event.packageName.toString(), PackageManager.GET_META_DATA))
                Toast.makeText(super.getApplicationContext(), "VIEW_FOCUSED: "+event.packageName + ", App name: " + appname, Toast.LENGTH_LONG).show()
            }
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                Log.d(TAG, "Got VIEW_CLICKED from " + event.packageName)
                var appname = packageManager.getApplicationLabel(packageManager.getApplicationInfo(event.packageName.toString(), PackageManager.GET_META_DATA))
                Toast.makeText(super.getApplicationContext(), "VIEW_CLICKED: "+event.packageName + ", App name: " + appname, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onInterrupt() {
        Log.d(TAG, "We got interrupted ;-;")
    }

    override fun onServiceConnected() {
        Log.d(TAG, "Service connected! configuring...")
        var serviceInfoA = this.serviceInfo
        serviceInfoA.eventTypes = AccessibilityEvent.TYPES_ALL_MASK
        serviceInfoA.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
        //serviceInfo.flags = AccessibilityServiceInfo.DEFAULT
        serviceInfoA.notificationTimeout = 100
        this.serviceInfo = serviceInfoA
        //super.onServiceConnected()
    }
}