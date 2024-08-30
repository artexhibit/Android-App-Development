package ru.igorcodes.kotlinbasics.fragments
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.igorcodes.kotlinbasics.R

class FragmentsActivity: AppCompatActivity() {

    lateinit var replace: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replace = findViewById(R.id.buttonReplace)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val firstFragment = FirstFragment()

        fragmentTransaction.add(R.id.frame, firstFragment)
        fragmentTransaction.commit()

        replace.setOnClickListener {
            val secondFragmentManager: FragmentManager = supportFragmentManager
            val secondFragmentTransaction: FragmentTransaction = secondFragmentManager.beginTransaction()

            val secondFragment = SecondFragment()
            secondFragmentTransaction.replace(R.id.frame, secondFragment)
            secondFragmentTransaction.addToBackStack(null)
            secondFragmentTransaction.commit()
        }
    }
}