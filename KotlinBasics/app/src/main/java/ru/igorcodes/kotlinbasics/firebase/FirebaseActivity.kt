package ru.igorcodes.kotlinbasics.firebase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ru.igorcodes.kotlinbasics.R

class FirebaseActivity: AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var buttonSend: Button
    private lateinit var textViewName: TextView

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference = database.reference.child("Users")
    private val reference2: DatabaseReference = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextName = findViewById(R.id.editTextName)
        buttonSend = findViewById(R.id.buttonSend)
        textViewName = findViewById(R.id.textViewName)

        getDataFromFireBaseRealtimeDatabase()

        buttonSend.setOnClickListener {
            val userName = editTextName.text.toString()
            reference.child("userName").setValue(userName)
        }
    }

    private fun getDataFromFireBaseRealtimeDatabase() {
        reference2.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val realName = snapshot.child("Users").child("name").value as String
                textViewName.text = realName
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error retrieving data from database", Toast.LENGTH_SHORT).show()
            }
        })
    }
}