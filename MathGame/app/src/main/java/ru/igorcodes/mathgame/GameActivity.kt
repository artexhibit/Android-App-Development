package ru.igorcodes.mathgame
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import java.util.Locale
import kotlin.random.Random

class GameActivity: AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var textScore: TextView
    private lateinit var textLife: TextView
    private lateinit var textTime: TextView
    private lateinit var textQuestion: TextView
    private lateinit var editTextAnswer: EditText
    private lateinit var buttonOk: Button
    private lateinit var buttonNext: Button

    private lateinit var timer: CountDownTimer

    private var pickedGameMode: GameMode = GameMode.Addition

    private var correctAnswer = 0
    private var userScore = 0
    private var userLife = 3
    private val startTimerInMillis: Long = 60000
    private var timeLeftInMillis: Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        configure()
        LayoutManager.setOnApplyWindowInsetsListener(this@GameActivity)
        configureDataSource()
        gameContinue(pickedGameMode)

        buttonOk.setOnClickListener { buttonOkPressed() }
        buttonNext.setOnClickListener { buttonNextPressed() }
    }

    private fun configure() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
    }

    private fun configureDataSource() {
        toolbar = findViewById(R.id.gameActivityToolbar)
        toolbar.setOnClickListener { finish() }

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pickedGameMode = intent.getSerializableExtra("GameMode", GameMode::class.java) ?: GameMode.Addition
        } else {
            @Suppress("DEPRECATION")
            pickedGameMode = intent.getSerializableExtra("GameMode") as? GameMode ?: GameMode.Addition
        }
    }

    private fun gameContinue(mode: GameMode) {
        val number1 = Random.nextInt(0, 100)
        val number2 = Random.nextInt(0, 100)

        when (mode) {
            GameMode.Addition -> {
                toolbar.title = "Addition"
                textQuestion.text = "$number1 + $number2"
                correctAnswer = number1 + number2
            }
            GameMode.Subtraction -> {
                toolbar.title = "Subtraction"
                textQuestion.text = "$number1 - $number2"
                correctAnswer = number1 - number2
            }
            GameMode.Multiplication -> {
                toolbar.title = "Multiplication"
                textQuestion.text = "$number1 x $number2"
                correctAnswer = number1 * number2
            }
        }
        startTimer()
    }

    private fun startTimer() {
        timer = object: CountDownTimer(timeLeftInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, your time is up!"
            }
        }.start()
    }

    private fun updateText() {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    private fun pauseTimer() {
        timer.cancel()
    }

    private fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }

    private fun buttonOkPressed() {
        val input = editTextAnswer.text.toString()

        if (input.isEmpty()) {
            Toast.makeText(applicationContext, "Please write an answer or click the next button", Toast.LENGTH_LONG).show()
        } else {
            pauseTimer()
            val userAnswer = input.toInt()

            if (userAnswer == correctAnswer) {
                userScore += 10
                textQuestion.text = "Congrats, answer is correct"
                textScore.text = userScore.toString()
            } else {
                userLife--
                textQuestion.text = "Sorry, answer is wrong"
                textLife.text = userLife.toString()
            }
        }
    }

    private fun buttonNextPressed() {
        pauseTimer()
        resetTimer()
        gameContinue(pickedGameMode)
        editTextAnswer.setText("")
        checkIfNoMoreLife()
    }

    private fun checkIfNoMoreLife() {
        if (userLife == 0) {
            Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
            val intent = Intent(this@GameActivity, ResultActivity::class.java)
            intent.putExtra("score", userScore)
            startActivity(intent)
            finish()
        }
    }
}