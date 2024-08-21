package ru.igorcodes.mathgame
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ResultActivity: AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var playAgain: Button
    private lateinit var exit: Button

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        LayoutManager.setOnApplyWindowInsetsListener(this@ResultActivity)
        configureDataSource()

        playAgain.setOnClickListener { playAgainButtonPressed() }
        exit.setOnClickListener { exitButtonPressed() }
    }

    private fun configureDataSource() {
        result = findViewById(R.id.textViewResult)
        playAgain = findViewById(R.id.buttonAgain)
        exit = findViewById(R.id.buttonExit)
        score = intent.getIntExtra("score", 0)
        result.text = "Your score is $score"
    }

    private fun playAgainButtonPressed() {
        val intent = Intent(this@ResultActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun exitButtonPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}