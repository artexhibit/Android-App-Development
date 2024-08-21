package ru.igorcodes.mathgame
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity: AppCompatActivity() {

   private lateinit var addition: Button
   private lateinit var subtraction: Button
   private lateinit var multi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        configure()
        LayoutManager.setOnApplyWindowInsetsListener(this@MainActivity)
        configureDataSource()
    }

    private fun configure() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
    }

    private fun configureDataSource() {
        addition = findViewById(R.id.buttonAdd)
        subtraction = findViewById(R.id.buttonSub)
        multi = findViewById(R.id.buttonMulti)

        addition.setOnClickListener { createIntent(GameMode.Addition) }
        subtraction.setOnClickListener { createIntent(GameMode.Subtraction) }
        multi.setOnClickListener { createIntent(GameMode.Multiplication) }
    }

    private fun createIntent(mode: GameMode) {
        val intent = Intent(this@MainActivity, GameActivity::class.java)
        intent.putExtra("GameMode", mode)
        startActivity(intent)
    }
}