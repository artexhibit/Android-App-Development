package ru.igorcodes.kotlinbasics.firebase
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseLoginBinding

class FirebaseLoginActivity: AppCompatActivity() {

    lateinit var loginBinding: ActivityFirebaseLoginBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        loginBinding = ActivityFirebaseLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginBinding.buttonSignIn.setOnClickListener {
            val userEmail = loginBinding.editTextEmailSignIn.text.toString()
            val userPassword = loginBinding.editTexPasswordSignIn.text.toString()

            signInWithFirebase(userEmail, userPassword)
        }

        loginBinding.buttonSignUp.setOnClickListener {
            val intent = Intent(this@FirebaseLoginActivity, FirebaseSignUpActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonForgot.setOnClickListener {
            val intent = Intent(this, FirebaseForgetActivity::class.java)
            startActivity(intent)
        }

        loginBinding.buttonSignInWithPhoneNumber.setOnClickListener {
            val intent = Intent(this, FirebasePhoneActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if (user != null) {
            openFirebaseActivity()
        }
    }

    private fun signInWithFirebase(userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Sign in is successful", Toast.LENGTH_SHORT).show()
                openFirebaseActivity()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT,).show()
            }
        }
    }

    private fun openFirebaseActivity() {
        val intent = Intent(this@FirebaseLoginActivity, FirebaseActivity::class.java)
        startActivity(intent)
        finish()
    }
}