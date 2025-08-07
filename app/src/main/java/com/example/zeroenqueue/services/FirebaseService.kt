package com.example.zeroenqueue.services

import android.widget.Toast
import com.example.zeroenqueue.common.Common
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class FirebaseService: FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Common.updateToken(this, p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val dataRecv = p0.data
        
        // Handle data payload notifications
        if (dataRecv.isNotEmpty()) {
            val title = dataRecv[Common.NOTI_TITLE] ?: "New Notification"
            val content = dataRecv[Common.NOTI_CONTENT] ?: "You have a new notification"
            Common.showNotification(this, Random().nextInt(), title, content, null)
        }
        // Handle notification payload (when app is in foreground)
        else if (p0.notification != null) {
            val title = p0.notification!!.title ?: "New Notification"
            val content = p0.notification!!.body ?: "You have a new notification"
            Common.showNotification(this, Random().nextInt(), title, content, null)
        }
    }
}