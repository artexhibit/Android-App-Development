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
import ru.igorcodes.kotlinbasics.dialogFragment.DialogFragmentActivity
import ru.igorcodes.kotlinbasics.fragments.FragmentsActivity
import ru.igorcodes.kotlinbasics.gridView.GridViewActivity
import ru.igorcodes.kotlinbasics.intent.IntentMainActivity
import ru.igorcodes.kotlinbasics.layouts.LayoutsActivity
import ru.igorcodes.kotlinbasics.lifeCycles.LifeCyclesActivity
import ru.igorcodes.kotlinbasics.listFragment.ListFragmentMainActivity
import ru.igorcodes.kotlinbasics.listView.ListViewActivity
import ru.igorcodes.kotlinbasics.multipleLanguage.MultipleLanguageActivity
import ru.igorcodes.kotlinbasics.myFirstProject.MyFirstProjectActivity
import ru.igorcodes.kotlinbasics.objectOrientedProgramming.ObjectOrientedProgrammingActivity
import ru.igorcodes.kotlinbasics.recyclerView.RecyclerViewActivity
import ru.igorcodes.kotlinbasics.scrollView.ScrollViewActivity
import ru.igorcodes.kotlinbasics.sendSMS.SendSMSActivity
import ru.igorcodes.kotlinbasics.sendingDataBetweenActivities.SendingDataBetweenActivitiesMainActivity
import ru.igorcodes.kotlinbasics.sendingDataFromActivityToFragment.SendingDataFromActivityToFragmentActivity
import ru.igorcodes.kotlinbasics.sendingDataFromFragmentToActivity.SendingDataFromFragmentToActivityActivity
import ru.igorcodes.kotlinbasics.sendingDataFromFragmentToFragment.SendingDataFromFragmentToFragmentActivity
import ru.igorcodes.kotlinbasics.services.ServicesActivity
import ru.igorcodes.kotlinbasics.topAppBar.TopAppBarActivity
import ru.igorcodes.kotlinbasics.userInteractions.UserInteractionsActivity
import ru.igorcodes.kotlinbasics.webView.WebViewActivity

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
            Lesson.LIST_VIEW -> createIntent(ListViewActivity())
            Lesson.RECYCLER_VIEW -> createIntent(RecyclerViewActivity())
            Lesson.SCROLL_VIEW -> createIntent(ScrollViewActivity())
            Lesson.SERVICES -> createIntent(ServicesActivity())
            Lesson.TOP_APP_BAR -> createIntent(TopAppBarActivity())
            Lesson.USER_INTERACTIONS -> createIntent(UserInteractionsActivity())
            Lesson.WEB_VIEW -> createIntent(WebViewActivity())
            Lesson.MULTIPLE_LANGUAGE -> createIntent(MultipleLanguageActivity())
            Lesson.FRAGMENTS -> createIntent(FragmentsActivity())
            Lesson.DIALOG_FRAGMENT -> createIntent(DialogFragmentActivity())
            Lesson.LIST_FRAGMENT -> createIntent(ListFragmentMainActivity())
            Lesson.SENDING_DATA_BETWEEN_ACTIVITIES -> createIntent(SendingDataBetweenActivitiesMainActivity())
            Lesson.SENDING_DATA_FROM_ACTIVITY_TO_FRAGMENT -> createIntent(SendingDataFromActivityToFragmentActivity())
            Lesson.SENDING_DATA_FROM_FRAGMENT_TO_ACTIVITY -> createIntent(SendingDataFromFragmentToActivityActivity())
            Lesson.SENDING_DATA_FROM_FRAGMENT_TO_FRAGMENT -> createIntent(SendingDataFromFragmentToFragmentActivity())
            Lesson.SEND_SMS -> createIntent(SendSMSActivity())
        }
    }

    private fun createIntent(activity: AppCompatActivity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}