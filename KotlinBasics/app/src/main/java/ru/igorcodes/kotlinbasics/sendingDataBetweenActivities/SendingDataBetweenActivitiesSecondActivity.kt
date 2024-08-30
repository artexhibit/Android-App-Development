package ru.igorcodes.kotlinbasics.sendingDataBetweenActivities
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R

class SendingDataBetweenActivitiesSecondActivity: AppCompatActivity() {

    lateinit var tvName: TextView
    lateinit var tvEmail: TextView
    lateinit var tvPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_sending_data_between_activities)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvName = findViewById(R.id.textViewName)
        tvEmail = findViewById(R.id.textViewEmail)
        tvPhone = findViewById(R.id.textViewPhone)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val phone = intent.getLongExtra("phone", 0)

        tvName.text = "Hello $name"
        tvEmail.text = "Your email is $email"
        tvPhone.text = "Your phone is $phone"
    }
}