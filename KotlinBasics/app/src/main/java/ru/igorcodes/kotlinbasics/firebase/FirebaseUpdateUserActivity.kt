package ru.igorcodes.kotlinbasics.firebase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ru.igorcodes.kotlinbasics.R
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseUpdateUserBinding

class FirebaseUpdateUserActivity: AppCompatActivity() {

    private lateinit var updateUserBiding: ActivityFirebaseUpdateUserBinding

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myReference: DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateUserBiding = ActivityFirebaseUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBiding.root

        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Update User"
        getAndSetData()

        updateUserBiding.buttonUpdateUser.setOnClickListener { updateData() }
    }

    private fun getAndSetData() {
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0).toString()
        val email = intent.getStringExtra("email")

        updateUserBiding.editTextFirebaseUpdateName.setText(name)
        updateUserBiding.editTextFirebaseUpdateAge.setText(age)
        updateUserBiding.editTextFirebaseUpdateEmail.setText(email)
    }

    private fun updateData() {
        val updatedName = updateUserBiding.editTextFirebaseUpdateName.text.toString()
        val updatedAge = updateUserBiding.editTextFirebaseUpdateAge.text.toString().toInt()
        val updatedEmail = updateUserBiding.editTextFirebaseUpdateEmail.text.toString()
        val userID = intent.getStringExtra("id").toString()

        val userMap = mutableMapOf<String, Any>()
        userMap["userID"] = userID.toString()
        userMap["userName"] = updatedName
        userMap["userAge"] = updatedAge
        userMap["userEmail"] = updatedEmail

        myReference.child(userID).updateChildren(userMap).addOnCompleteListener {  task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "The user has been updated", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}