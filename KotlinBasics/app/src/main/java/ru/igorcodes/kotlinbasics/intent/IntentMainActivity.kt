package ru.igorcodes.kotlinbasics.intent
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R

class IntentMainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var name: EditText
        lateinit var age: EditText
        lateinit var send: Button

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_intent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        name = findViewById(R.id.editTextName)
        age = findViewById(R.id.editTextAge)
        send = findViewById(R.id.button)

        send.setOnClickListener {
            var userName: String = name.text.toString()
            var userAge: Int? = age.text.toString().toIntOrNull()

            var intent = Intent(this@IntentMainActivity, IntentSecondActivity::class.java)
            intent.putExtra("username", userName)
            intent.putExtra("userage", userAge)

            startActivity(intent)
        }
    }
}