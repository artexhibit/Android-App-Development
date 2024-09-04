package ru.igorcodes.kotlinbasics.localNotification
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityLocalNotificationBinding

class LocalNotificationActivity: AppCompatActivity() {

    private lateinit var localNotificationBinding: ActivityLocalNotificationBinding
    private var counter = 0
    private val CHANNEL_ID = "1"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        localNotificationBinding = ActivityLocalNotificationBinding.inflate(layoutInflater)
        val view = localNotificationBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        localNotificationBinding.sendNotification.setOnClickListener {
            counter++
            localNotificationBinding.sendNotification.text = counter.toString()

            if (counter % 5 == 0) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),4)
                } else {
                    startNotification()
                }
            }
        }
    }

    fun startNotification(){
        val channel = NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)

        builder.apply {
            setSmallIcon(R.drawable.small_icon)
            setContentTitle("Notification Title")
            setContentText("Notification text")
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        NotificationManagerCompat.from(this).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                if (ContextCompat.checkSelfPermission(this@LocalNotificationActivity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                    if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                        notify(1, builder.build())
                    } else {
                        ActivityCompat.requestPermissions(this@LocalNotificationActivity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),4)
                    }
                } else {
                    notify(1, builder.build())
                }
            } else {
                notify(1, builder.build())
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 4 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startNotification()
        }
    }
}