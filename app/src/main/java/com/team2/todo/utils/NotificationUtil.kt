package com.team2.todo.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.team2.todo.R
import com.team2.todo.data.entities.relations.TodoWithSubTodos
import kotlin.random.Random

/**
 * Created by Manu KJ on 11/2/23.
 */

object NotificationUtil {
    private lateinit var context: Context
    private lateinit var notificationManager: NotificationManager
    private const val LOCATION_CHANNEL_ID = "LOCATION_CHANNEL_ID"
    const val LOCATION_CHANNEL_NAME = "LOCATION_CHANNEL_NAME"


    fun init(context: Context) {
        this.context = context
        notificationManager = context.getSystemService(NotificationManager::class.java)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Notification"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(LOCATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showGeoFencingNotification(property: TodoWithSubTodos) {
        var message =
            "You have reached the location of ${property.todo.title}, pending task ${
                getPendingSubTask(
                    property
                )
            } task";
        if (notificationManager != null) {
            val notification = NotificationCompat.Builder(context, LOCATION_CHANNEL_ID)
                .setContentTitle("Location Reached")
                .setContentText(message)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(message)
                )
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                .setSmallIcon(R.drawable.ic_logo)
                .build()

            notificationManager.notify(
                Random.nextInt(),
                notification
            )
        }

    }

    private fun getPendingSubTask(property: TodoWithSubTodos): String {
        var count = 1;
        property.subtodos.forEach {
            if (it.status == false) {
                count++
            }
        }
        return count.toString()
    }


}
