package com.ssafy.fundyou.ui.common

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ssafy.fundyou.domain.usecase.fcm.AddFcmTokenUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FcmSetting @Inject constructor(
    private val addFcmTokenUseCase: AddFcmTokenUseCase
) : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "onNewToken: addToken")
            addFcmTokenUseCase(token)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

    }

    companion object{
        private const val TAG = "FcmSetting..."
    }
}