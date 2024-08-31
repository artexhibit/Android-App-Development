package ru.igorcodes.kotlinbasics.sendEmail
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivitySendEmailBinding

class SendEmailActivity: AppCompatActivity() {

    lateinit var sendEmailBinding: ActivitySendEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sendEmailBinding = ActivitySendEmailBinding.inflate(layoutInflater)
        val view = sendEmailBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sendEmailBinding.buttonSend.setOnClickListener {
            val userAddress = sendEmailBinding.editTextAddress.text.toString()
            val userSubject = sendEmailBinding.editTextSubject.text.toString()
            val userMessage = sendEmailBinding.editTextMessage.text.toString()

            sendEmail(userAddress, userSubject, userMessage)
        }
    }

    private fun sendEmail(userAddress: String, userSubject: String, userMessage: String) {
        val emailAddresses = arrayOf(userAddress)
        val emailIntent = Intent(Intent.ACTION_SENDTO)

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, userSubject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, userMessage)

        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Choose an app"))
        }

//        Alternative to send email via all device's messaging apps
//        val emailAltIntent = Intent(Intent.ACTION_SEND)
//        emailAltIntent.type = "*/*"
//        emailAltIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
//        emailAltIntent.putExtra(Intent.EXTRA_SUBJECT, userSubject)
//        emailAltIntent.putExtra(Intent.EXTRA_TEXT, userMessage)
//
//        if (emailAltIntent.resolveActivity(packageManager) != null) {
//            startActivity(Intent.createChooser(emailAltIntent, "Choose an app"))
//        }
    }
}