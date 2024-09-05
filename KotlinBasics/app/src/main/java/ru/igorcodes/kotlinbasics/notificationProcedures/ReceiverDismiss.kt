package ru.igorcodes.kotlinbasics.notificationProcedures
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

class ReceiverDismiss: BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        context?.let {
            val notificationManagerCompat = NotificationManagerCompat.from(it)
            notificationManagerCompat.cancel(3)
        }
    }
}