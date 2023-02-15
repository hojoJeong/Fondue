package com.ssafy.fundyou.ui.common

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmSetting: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

    }



    companion object{
        private const val TAG = "FcmSetting..."
    }
}