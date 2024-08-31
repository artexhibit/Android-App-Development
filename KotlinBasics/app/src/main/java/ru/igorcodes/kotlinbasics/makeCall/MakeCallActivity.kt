package ru.igorcodes.kotlinbasics.makeCall
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityMakeCallBinding
import ru.igorcodes.kotlinbasics.databinding.ActivitySendEmailBinding

class MakeCallActivity: AppCompatActivity() {

    private lateinit var makeCallBinding: ActivityMakeCallBinding
    var userNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        makeCallBinding = ActivityMakeCallBinding.inflate(layoutInflater)
        val view = makeCallBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        makeCallBinding.buttonCall.setOnClickListener {
            userNumber = makeCallBinding.editTextPhone.text.toString()
            startCall(userNumber)
        }
    }

    private fun startCall(userNumber:String) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 2)
        } else {
            val intent = Intent(Intent.ACTION_DIAL)

            intent.data = Uri.parse("tel:$userNumber")
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 2 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_CALL)

            intent.data = Uri.parse("tel:$userNumber")
            startActivity(intent)
        }
    }
}