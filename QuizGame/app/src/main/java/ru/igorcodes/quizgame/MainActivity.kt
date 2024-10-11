package ru.igorcodes.quizgame
import android.content.Intent
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.CredentialManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.ClearCredentialStateRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.igorcodes.quizgame.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var credentialManager: CredentialManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        credentialManager = CredentialManager.create(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainBinding.buttonStartQuiz.setOnClickListener {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            startActivity(intent)
        }

        mainBinding.buttonSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            signOutWithGoogle()

            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signOutWithGoogle() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                credentialManager.clearCredentialState(ClearCredentialStateRequest())
                Toast.makeText(applicationContext, "Sign Out is successful", Toast.LENGTH_SHORT).show()

            } catch (e: ClearCredentialException) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}