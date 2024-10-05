package ru.igorcodes.kotlinbasics.firebase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseForgetBinding

class FirebaseForgetActivity: AppCompatActivity() {

    lateinit var forgetBinding: ActivityFirebaseForgetBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        forgetBinding = ActivityFirebaseForgetBinding.inflate(layoutInflater)
        val view = forgetBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        forgetBinding.buttonReset.setOnClickListener {
            val email = forgetBinding.editTextReset.text.toString()

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "We sent a password reset mail to your mail address", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}