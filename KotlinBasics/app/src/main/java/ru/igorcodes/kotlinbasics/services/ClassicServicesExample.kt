package ru.igorcodes.kotlinbasics.services
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ClassicServicesExample: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("ClassicService", "Classic Service is created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ClassicService", "Classic Service is started")
        Log.d("ClassicServiceThread", Thread.currentThread().name)

        //stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ClassicService", "Classic Service is stopped")
    }
}