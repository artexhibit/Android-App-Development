package ru.igorcodes.kotlinbasics.dialogFragment
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import ru.igorcodes.kotlinbasics.R

class DialogFragmentActivity: AppCompatActivity() {

    private lateinit var show: Button
    private lateinit var name: TextView
    private lateinit var age: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dialog_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        show = findViewById(R.id.buttonShow)
        name = findViewById(R.id.textViewName)
        age = findViewById(R.id.textViewAge)

        show.setOnClickListener {
            val fragmentManager: FragmentManager = supportFragmentManager
            val myDialogFragment = MyDialogFragment()

            myDialogFragment.isCancelable = false
            myDialogFragment.show(fragmentManager, "MyDialogFragment")
        }
    }

    fun getUserData(userName: String, userAge: Int) {
        name.text = "Name: $userName"
        age.text = "Age: $userAge"
    }
}