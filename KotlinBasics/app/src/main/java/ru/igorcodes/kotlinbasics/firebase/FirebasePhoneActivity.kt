package ru.igorcodes.kotlinbasics.firebase
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebasePhoneBinding
import java.util.concurrent.TimeUnit

class FirebasePhoneActivity: AppCompatActivity() {

    private lateinit var phoneBinding: ActivityFirebasePhoneBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var verificationCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        phoneBinding = ActivityFirebasePhoneBinding.inflate(layoutInflater)
        val view = phoneBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        phoneBinding.buttonSendSMS.setOnClickListener {
            val phoneNumber = phoneBinding.editTextPhoneNumber.text.toString()

            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this@FirebasePhoneActivity)
                .setCallbacks(mCallbacks)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        phoneBinding.buttonVerify.setOnClickListener { signInWithSMSCode() }

        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                TODO("Not yet implemented")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("FirebasePhoneActivity", "onVerificationFailed: ${e.message}", e)
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCode = p0
            }
        }
    }

    private fun signInWithSMSCode() {
        val userEnterCode = phoneBinding.editTextVerificationCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationCode, userEnterCode)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this@FirebasePhoneActivity, FirebaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, "The code your entered is incorrect", Toast.LENGTH_SHORT,).show()
            }
        }
    }
}