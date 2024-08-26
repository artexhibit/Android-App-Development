package ru.igorcodes.kotlinbasics.services
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R

class ServicesActivity : AppCompatActivity() {

    lateinit var startClassicService: Button
    lateinit var startJobIntentService: Button
    lateinit var stopClassicService: Button

    var br = BroadcastExample()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_services)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startClassicService = findViewById(R.id.startClassicService)
        startJobIntentService = findViewById(R.id.startJobIntentService)
        stopClassicService = findViewById(R.id.stopService)

        startClassicService.setOnClickListener {
            val intent = Intent(this@ServicesActivity, ClassicServicesExample::class.java)
            startService(intent)
        }

        startJobIntentService.setOnClickListener {
            val intent = Intent(this@ServicesActivity, JobIntentServiceExample::class.java)
            JobIntentServiceExample.myBackgroundService(this@ServicesActivity, intent)
        }

        stopClassicService.setOnClickListener {
            val intent = Intent(this@ServicesActivity, ClassicServicesExample::class.java)
            stopService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        this.registerReceiver(br, filter)
    }

    override fun onStop() {
        super.onStop()
        this.unregisterReceiver(br)
    }
}