package ru.igorcodes.kotlinbasics.lifeCycles
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.igorcodes.kotlinbasics.R

class LifeCyclesSecondActivity : AppCompatActivity() {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lifecycles_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        button = findViewById(R.id.button3)

        button.setOnClickListener {
            var intent = Intent(this@LifeCyclesSecondActivity, LifeCyclesActivity::class.java)
            startActivity(intent)
        }
        Log.d("AppMessage", "Second Activity onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AppMessage", "Second Activity onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d("AppMessage", "Second Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("AppMessage", "Second Activity onStop")
    }

    override fun onStart() {
        super.onStart()
        Log.d("AppMessage", "Second Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("AppMessage", "Second Activity onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("AppMessage", "Second Activity onRestart")
    }
}