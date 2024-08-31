package ru.igorcodes.kotlinbasics.sendingDataFromFragmentToFragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.igorcodes.kotlinbasics.R

class SendingDataFromFragmentToFragmentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sending_data_from_fragment_to_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val sendingDataFromFragmentToFragmentFirstFragment = SendingDataFromFragmentToFragmentFirstFragment()

        fragmentTransaction.add(R.id.frame, sendingDataFromFragmentToFragmentFirstFragment)
        fragmentTransaction.commit()
    }
}