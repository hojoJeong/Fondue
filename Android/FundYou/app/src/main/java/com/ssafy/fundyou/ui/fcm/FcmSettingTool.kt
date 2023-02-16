package com.ssafy.fundyou.ui.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ssafy.fundyou.ui.MainActivity
import com.ssafy.fundyou.ui.splash.SplashActivity

class FcmSettingTool : FirebaseMessagingService() {
    private lateinit var builder: NotificationCompat.Builder
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        Log.d(
            TAG,
            "onMessageReceived: FCM data : ${remoteMessage.data["title"]}, ${remoteMessage.data["body"]}, ${remoteMessage.data["isHost"]}"
        )
        Log.d(TAG, "onMessageReceived: FCM notification : ${remoteMessage.notification?.title} , ${remoteMessage.notification?.body}")

        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            NotificationCompat.Builder(applicationContext)
        }

        var title = remoteMessage.data["title"].toString()
        var content = remoteMessage.data["body"].toString()
        val isHost = remoteMessage.data["isHost"].toString()
        val user = if(isHost == "true") "host" else "participate"
        var intent = Intent(this, SplashActivity::class.java).apply {
            putExtra("user", user)
        }

        Log.d(TAG, "onMessageReceived: $user")

        //사용자가 Foreground 상태일 때
        if(remoteMessage.notification != null){
            title = remoteMessage.notification!!.title.toString()
            content = remoteMessage.notification!!.body.toString()
            intent = Intent(this, MainActivity::class.java).apply {
                putExtra("user", user)
            }
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notificationBuilder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(com.ssafy.fundyou.R.drawable.ic_app_logo)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    companion object {
        private const val TAG = "FcmSetting..."
        private const val CHANNEL_ID = "fondue"
        private const val CHANNEL_NAME = "퐁듀 푸쉬 알림"
        private const val NOTIFICATION_ID = 202
        private const val REQUEST_CODE = 202
    }
}