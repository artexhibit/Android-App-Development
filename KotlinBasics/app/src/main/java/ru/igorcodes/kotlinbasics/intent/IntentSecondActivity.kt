package ru.igorcodes.kotlinbasics.intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import ru.igorcodes.kotlinbasics.R

class IntentSecondActivity: AppCompatActivity() {

    lateinit var result: TextView
    lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_intent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        result = findViewById(R.id.textView)
        toolbar = findViewById(R.id.materialToolbar)

        var userName: String = intent.getStringExtra("username").toString()
        var userAge: Int = intent.getIntExtra("userage", 0)

        result.text = "Your name is $userName and your age is $userAge"

        toolbar.setOnClickListener {
            finish()
        }
    }
}