package ru.igorcodes.kotlinbasics.sendingDataBetweenActivities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import ru.igorcodes.kotlinbasics.R

class SendingDataBetweenActivitiesMainActivity: AppCompatActivity() {

    lateinit var etName: TextInputEditText
    lateinit var etEmail: TextInputEditText
    lateinit var etPhone: TextInputEditText
    lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_sending_data_between_activities)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etName = findViewById(R.id.editTextName)
        etEmail = findViewById(R.id.editTextEmail)
        etPhone = findViewById(R.id.editTextPhone)
        signUp = findViewById(R.id.buttonSignUp)

        signUp.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString().toLong()

            val intent = Intent(this, SendingDataBetweenActivitiesSecondActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("phone", phone)

            startActivity(intent)
        }
    }
}