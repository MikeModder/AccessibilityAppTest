package me.mikemodder.osxbuildtest

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent


/**
 * Created by mike on 5/17/18.
 */

class AccessibilityThing : AccessibilityService() {
    val TAG = "ACCS"
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if(event == null) { Log.d(TAG, "null event"); return }
        val eventType = event?.eventType
        Log.d(TAG, "Got am event?")
        when(eventType){
            AccessibilityEvent.TYPE_VIEW_FOCUSED -> {
                Log.d(TAG, "Got VIEW_FOCUSED event! Text " + event.text)
            }
            null -> { Log.d(TAG, "Got null") }
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