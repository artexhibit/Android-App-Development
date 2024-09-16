package ru.igorcodes.kotlinbasics.sendingDataFromFragmentToActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.igorcodes.kotlinbasics.R

class SendingDataFromFragmentToActivityActivity: AppCompatActivity() {

    lateinit var name: TextView
    lateinit var email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sending_data_from_fragment_to_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        name = findViewById(R.id.textViewFirebaseName)
        email = findViewById(R.id.textViewEmail)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val sendingDataFromFragmentToActivityFragment = SendingDataFromFragmentToActivityFragment()

        fragmentTransaction.add(R.id.frame, sendingDataFromFragmentToActivityFragment)
        fragmentTransaction.commit()
    }

    fun takeData(username: String, useremail: String) {
        name.text = username
        email.text = useremail
    }
}