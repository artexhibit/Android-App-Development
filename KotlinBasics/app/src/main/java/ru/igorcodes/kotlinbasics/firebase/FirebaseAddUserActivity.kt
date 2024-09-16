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
import ru.igorcodes.kotlinbasics.databinding.ActivityFirebaseAddUserBinding

class FirebaseAddUserActivity: AppCompatActivity() {

    private lateinit var addUserBiding: ActivityFirebaseAddUserBinding
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val myReference: DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addUserBiding = ActivityFirebaseAddUserBinding.inflate(layoutInflater)
        val view = addUserBiding.root

        enableEdgeToEdge()
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addUserBiding.buttonAddUser.setOnClickListener {
            addUserToDatabase()
        }
    }

    private fun addUserToDatabase() {
        val name = addUserBiding.editTextFirebaseName.text.toString()
        val age = addUserBiding.editTextFirebaseAge.text.toString().toInt()
        val email = addUserBiding.editTextFirebaseEmail.text.toString()
        val id = myReference.push().key.toString()
        val user = Users(name, age, email, id)

        myReference.child(id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "The user has been added to the database", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}