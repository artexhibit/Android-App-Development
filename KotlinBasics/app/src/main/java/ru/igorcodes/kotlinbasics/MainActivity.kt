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
import ru.igorcodes.kotlinbasics.gridView.GridViewActivity
import ru.igorcodes.kotlinbasics.intent.IntentMainActivity
import ru.igorcodes.kotlinbasics.layouts.LayoutsActivity
import ru.igorcodes.kotlinbasics.lifeCycles.LifeCyclesActivity
import ru.igorcodes.kotlinbasics.myFirstProject.MyFirstProjectActivity
import ru.igorcodes.kotlinbasics.objectOrientedProgramming.ObjectOrientedProgrammingActivity

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
            Lesson.GRID_VIEW -> createIntent(GridViewActivity())
            Lesson.MY_FIRST_PROJECT -> createIntent(MyFirstProjectActivity())
            Lesson.OBJECT_ORIENTED_PROGRAMMING -> createIntent(ObjectOrientedProgrammingActivity())
            Lesson.INTENT -> createIntent(IntentMainActivity())
            Lesson.LAYOUTS -> createIntent(LayoutsActivity())
            Lesson.LIFE_CYCLES -> createIntent(LifeCyclesActivity())
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