package ru.igorcodes.kotlinbasics.notificationProcedures
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
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
import ru.igorcodes.kotlinbasics.MainActivity
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityNotificationProceduresBinding

class NotificationProceduresActivity: AppCompatActivity() {

    private lateinit var notificationProceduresActivityBinding: ActivityNotificationProceduresBinding
    private var counter = 0
    private val CHANNEL_ID = "6"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        notificationProceduresActivityBinding = ActivityNotificationProceduresBinding.inflate(layoutInflater)
        val view = notificationProceduresActivityBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        notificationProceduresActivityBinding.sendNotification.setOnClickListener {
            counter++
            notificationProceduresActivityBinding.sendNotification.text = counter.toString()

            if (counter == 5) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),4)
                } else {
                    startNotification()
                }
            }
        }
    }

    private fun startNotification(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext,6, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        //actionButton
        val actionIntent = Intent(applicationContext, Receiver::class.java)
        actionIntent.putExtra("toast", "This is a notification message")
        val actionPendingIntent = PendingIntent.getBroadcast(applicationContext, 7, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        //dismissButton
        val dismissIntent = Intent(applicationContext, ReceiverDismiss::class.java)
        val dismissPendingIntent = PendingIntent.getBroadcast(applicationContext, 8, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        //show image or log scrollable text in a notification
        val icon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.android)
        val text: String = resources.getString(R.string.big_text)

        val channel = NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)

        builder.apply {
            setSmallIcon(R.drawable.small_icon)
            setContentTitle("Notification Title")
            setContentText("Notification text")
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            addAction(R.drawable.small_icon, "Toast Message", actionPendingIntent)
            addAction(R.drawable.small_icon, "Dismiss", dismissPendingIntent)
            setLargeIcon(icon)
            //setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon).bigLargeIcon(null as Icon?))
            setStyle(NotificationCompat.BigTextStyle().bigText(text))
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        NotificationManagerCompat.from(this).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                if (ContextCompat.checkSelfPermission(this@NotificationProceduresActivity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                    if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                        notify(3, builder.build())
                    } else {
                        ActivityCompat.requestPermissions(this@NotificationProceduresActivity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),6)
                    }
                } else {
                    notify(3, builder.build())
                }
            } else {
                notify(3, builder.build())
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 6 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startNotification()
        }
    }
}