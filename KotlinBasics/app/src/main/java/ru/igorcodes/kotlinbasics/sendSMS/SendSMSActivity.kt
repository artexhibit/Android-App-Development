package ru.igorcodes.kotlinbasics.sendSMS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R

class SendSMSActivity: AppCompatActivity() {

    lateinit var message: EditText
    lateinit var number: EditText
    lateinit var send: Button

    var userMessage = ""
    var userNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_send_sms)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        message = findViewById(R.id.editTextMessage)
        number = findViewById(R.id.editTextNumber)
        send = findViewById(R.id.buttonSend)

        send.setOnClickListener {
            userMessage = message.text.toString()
            userNumber = number.text.toString()

            sendSMS(userMessage, userNumber)
        }
    }

    private fun sendSMS(userMessage: String, userNumber: String) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), 1)
        } else {
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= 23) getSystemService(SmsManager::class.java) else SmsManager.getDefault()
            smsManager.sendTextMessage(userNumber, null, userMessage, null, null)
            Toast.makeText(applicationContext, "Message sent", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.size > 0 && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= 23) getSystemService(SmsManager::class.java) else SmsManager.getDefault()
            smsManager.sendTextMessage(userNumber, null, userMessage, null, null)
            Toast.makeText(applicationContext, "Message sent", Toast.LENGTH_LONG).show()
        }
    }
}