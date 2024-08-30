package ru.igorcodes.kotlinbasics.listFragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.igorcodes.kotlinbasics.R

class ListFragmentSecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_list_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val index = intent.getIntExtra("index", 0)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val myListSecondFragment = MyListSecondFragment()

        val bundle = Bundle()
        bundle.putInt("index", index)
        myListSecondFragment.arguments = bundle

        fragmentTransaction.add(R.id.frameLayout, myListSecondFragment)
        fragmentTransaction.commit()
    }
}