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
        if (dataRecv.isNotEmpty()) {
            Common.showNotification(this, Random().nextInt(),
                dataRecv[Common.NOTI_TITLE]!!,
                dataRecv[Common.NOTI_CONTENT]!!,
                null)
        }
        else {
            Toast.makeText(this, "data payload is empty", Toast.LENGTH_SHORT).show()
        }
    }
}