package ru.igorcodes.kotlinbasics.sendingDataFromActivityToFragment
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.igorcodes.kotlinbasics.R

class SendingDataFromActivityToFragmentActivity: AppCompatActivity() {

    lateinit var etWeight: EditText
    lateinit var etHeight: EditText
    lateinit var calculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sending_data_from_activity_to_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etWeight = findViewById(R.id.editTextWeight)
        etHeight = findViewById(R.id.editTextHeight)
        calculate = findViewById(R.id.buttonCalc)


        calculate.setOnClickListener {
            val weight = etWeight.text.toString().toInt()
            val height = etHeight.text.toString().toInt()
            val bundle = Bundle()

            bundle.putInt("weight", weight)
            bundle.putInt("height", height)
            val sendingDataFromActivityToFragmentBmiFragment = SendingDataFromActivityToFragmentBMIFragment()
            sendingDataFromActivityToFragmentBmiFragment.arguments = bundle

            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.replace(R.id.frame, sendingDataFromActivityToFragmentBmiFragment)
            ft.commit()
        }
    }
}