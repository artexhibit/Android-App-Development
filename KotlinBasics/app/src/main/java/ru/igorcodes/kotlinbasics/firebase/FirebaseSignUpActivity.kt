package ru.igorcodes.kotlinbasics.firebase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseSignUpBinding

class FirebaseSignUpActivity: AppCompatActivity() {

    lateinit var signUpBinding: ActivityFirebaseSignUpBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        signUpBinding = ActivityFirebaseSignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        signUpBinding.buttonSignUpUser.setOnClickListener {
            val userEmail = signUpBinding.editTextEmailSignUp.text.toString()
            val userPassword = signUpBinding.editTextPasswordSignUp.text.toString()

            signUpWithFirebase(userEmail, userPassword)
        }
    }

    private fun signUpWithFirebase(userEmail: String, userPassword: String) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Your account has been created", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception?.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }
}