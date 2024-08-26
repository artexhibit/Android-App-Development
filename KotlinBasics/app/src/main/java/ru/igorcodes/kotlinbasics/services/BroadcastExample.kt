package ru.igorcodes.kotlinbasics.services
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastExample: BroadcastReceiver() {
    override fun onReceive(contenxt: Context?, intent: Intent?) {
        val isAirPlaneMode: Boolean = intent?.getBooleanExtra("state", false) == true

        if (isAirPlaneMode) {
            Toast.makeText(contenxt, "The device is an airplane mode", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(contenxt, "The device is not in an airplane mode", Toast.LENGTH_LONG).show()
        }
    }
}