package ru.igorcodes.kotlinbasics
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ru.igorcodes.kotlinbasics.data.Lesson
import ru.igorcodes.kotlinbasics.data.LessonsData
import ru.igorcodes.kotlinbasics.databinding.ActivityMainBinding
import ru.igorcodes.kotlinbasics.myFirstProject.MyFirstProjectActivity

class MainActivity: AppCompatActivity(), LessonClickListener {

    private lateinit var lessonList: ArrayList<Lesson>
    private lateinit var lessonAdapter: LessonAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setOnApplyWindowInsetsListener()

        lessonList = LessonsData.lessonListItems()
        lessonAdapter = LessonAdapter(lessonList, this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = lessonAdapter
    }

    private fun setOnApplyWindowInsetsListener() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onLessonClick(lesson: Lesson) {
        when (lesson) {
            Lesson.GRID_VIEW -> TODO()
            Lesson.MY_FIRST_PROJECT -> createIntent(MyFirstProjectActivity())
            Lesson.OBJECT_ORIENTED_PROGRAMMING -> TODO()
            Lesson.INTENT -> TODO()
            Lesson.LAYOUTS -> TODO()
            Lesson.LIFE_CYCLES -> TODO()
            Lesson.LIST_VIEW -> TODO()
            Lesson.RECYCLER_VIEW -> TODO()
            Lesson.SCROLL_VIEW -> TODO()
            Lesson.SERVICES -> TODO()
            Lesson.TOP_APP_BAR -> TODO()
            Lesson.USER_INTERACTIONS -> TODO()
            Lesson.WEB_VIEW -> TODO()
            Lesson.MULTIPLE_LANGUAGE -> TODO()
            Lesson.FRAGMENTS -> TODO()
            Lesson.DIALOG_FRAGMENT -> TODO()
            Lesson.LIST_FRAGMENT -> TODO()
            Lesson.SENDING_DATA_BETWEEN_ACTIVITIES -> TODO()
        }
    }

    private fun createIntent(activity: AppCompatActivity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}