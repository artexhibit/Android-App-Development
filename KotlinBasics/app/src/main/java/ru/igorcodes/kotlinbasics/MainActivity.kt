package ru.igorcodes.kotlinbasics
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ru.igorcodes.kotlinbasics.jetpackLazyColumn.JetpackLazyColumnMainActivity
import ru.igorcodes.kotlinbasics.data.Lesson
import ru.igorcodes.kotlinbasics.data.LessonsData
import ru.igorcodes.kotlinbasics.databinding.ActivityMainBinding
import ru.igorcodes.kotlinbasics.dialogFragment.DialogFragmentActivity
import ru.igorcodes.kotlinbasics.firebase.FirebaseLoginActivity
import ru.igorcodes.kotlinbasics.fragments.FragmentsActivity
import ru.igorcodes.kotlinbasics.gridView.GridViewActivity
import ru.igorcodes.kotlinbasics.intent.IntentMainActivity
import ru.igorcodes.kotlinbasics.jetpackCompose.JetpackComposeMainActivity
import ru.igorcodes.kotlinbasics.jetpackLazyGrid.JetpackLazyGridMainActivity
import ru.igorcodes.kotlinbasics.jetpackLazyRow.JetpackLazyRowMainActivity
import ru.igorcodes.kotlinbasics.jetpackNavigationOne.JetpackNavigationOneMainActivity
import ru.igorcodes.kotlinbasics.layouts.LayoutsActivity
import ru.igorcodes.kotlinbasics.lifeCycles.LifeCyclesActivity
import ru.igorcodes.kotlinbasics.listFragment.ListFragmentMainActivity
import ru.igorcodes.kotlinbasics.listView.ListViewActivity
import ru.igorcodes.kotlinbasics.localNotification.LocalNotificationActivity
import ru.igorcodes.kotlinbasics.makeCall.MakeCallActivity
import ru.igorcodes.kotlinbasics.multipleLanguage.MultipleLanguageActivity
import ru.igorcodes.kotlinbasics.myFirstProject.MyFirstProjectActivity
import ru.igorcodes.kotlinbasics.notificationProcedures.NotificationProceduresActivity
import ru.igorcodes.kotlinbasics.objectOrientedProgramming.ObjectOrientedProgrammingActivity
import ru.igorcodes.kotlinbasics.periodicNotification.PeriodicNotificationActivity
import ru.igorcodes.kotlinbasics.recyclerView.RecyclerViewActivity
import ru.igorcodes.kotlinbasics.retrofit.RetrofitMainActivity
import ru.igorcodes.kotlinbasics.scrollView.ScrollViewActivity
import ru.igorcodes.kotlinbasics.sendEmail.SendEmailActivity
import ru.igorcodes.kotlinbasics.sendSMS.SendSMSActivity
import ru.igorcodes.kotlinbasics.sendingDataBetweenActivities.SendingDataBetweenActivitiesMainActivity
import ru.igorcodes.kotlinbasics.sendingDataFromActivityToFragment.SendingDataFromActivityToFragmentActivity
import ru.igorcodes.kotlinbasics.sendingDataFromFragmentToActivity.SendingDataFromFragmentToActivityActivity
import ru.igorcodes.kotlinbasics.sendingDataFromFragmentToFragment.SendingDataFromFragmentToFragmentActivity
import ru.igorcodes.kotlinbasics.services.ServicesActivity
import ru.igorcodes.kotlinbasics.speechToText.SpeechToTextActivity
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
            Lesson.GRID_VIEW -> createIntent(GridViewActivity::class.java)
            Lesson.MY_FIRST_PROJECT -> createIntent(MyFirstProjectActivity::class.java)
            Lesson.OBJECT_ORIENTED_PROGRAMMING -> createIntent(ObjectOrientedProgrammingActivity::class.java)
            Lesson.INTENT -> createIntent(IntentMainActivity::class.java)
            Lesson.LAYOUTS -> createIntent(LayoutsActivity::class.java)
            Lesson.LIFE_CYCLES -> createIntent(LifeCyclesActivity::class.java)
            Lesson.LIST_VIEW -> createIntent(ListViewActivity::class.java)
            Lesson.RECYCLER_VIEW -> createIntent(RecyclerViewActivity::class.java)
            Lesson.SCROLL_VIEW -> createIntent(ScrollViewActivity::class.java)
            Lesson.SERVICES -> createIntent(ServicesActivity::class.java)
            Lesson.TOP_APP_BAR -> createIntent(TopAppBarActivity::class.java)
            Lesson.USER_INTERACTIONS -> createIntent(UserInteractionsActivity::class.java)
            Lesson.WEB_VIEW -> createIntent(WebViewActivity::class.java)
            Lesson.MULTIPLE_LANGUAGE -> createIntent(MultipleLanguageActivity::class.java)
            Lesson.FRAGMENTS -> createIntent(FragmentsActivity::class.java)
            Lesson.DIALOG_FRAGMENT -> createIntent(DialogFragmentActivity::class.java)
            Lesson.LIST_FRAGMENT -> createIntent(ListFragmentMainActivity::class.java)
            Lesson.SENDING_DATA_BETWEEN_ACTIVITIES -> createIntent(SendingDataBetweenActivitiesMainActivity::class.java)
            Lesson.SENDING_DATA_FROM_ACTIVITY_TO_FRAGMENT -> createIntent(SendingDataFromActivityToFragmentActivity::class.java)
            Lesson.SENDING_DATA_FROM_FRAGMENT_TO_ACTIVITY -> createIntent(SendingDataFromFragmentToActivityActivity::class.java)
            Lesson.SENDING_DATA_FROM_FRAGMENT_TO_FRAGMENT -> createIntent(SendingDataFromFragmentToFragmentActivity::class.java)
            Lesson.SEND_SMS -> createIntent(SendSMSActivity::class.java)
            Lesson.SEND_EMAIL -> createIntent(SendEmailActivity::class.java)
            Lesson.MAKE_CALL -> createIntent(MakeCallActivity::class.java)
            Lesson.SPEECH_TO_TEXT -> createIntent(SpeechToTextActivity::class.java)
            Lesson.LOCAL_NOTIFICATION -> createIntent(LocalNotificationActivity::class.java)
            Lesson.PERIODIC_NOTIFICATION -> createIntent(PeriodicNotificationActivity::class.java)
            Lesson.NOTIFICATION_PROCEDURES -> createIntent(NotificationProceduresActivity::class.java)
            Lesson.FIREBASE -> createIntent(FirebaseLoginActivity::class.java)
            Lesson.RETROFIT -> createIntent(RetrofitMainActivity::class.java)
            Lesson.JETPACK_COMPOSE -> createIntent(JetpackComposeMainActivity::class.java)
            Lesson.JETPACK_NAVIGATION_ONE -> createIntent(JetpackNavigationOneMainActivity::class.java)
            Lesson.JETPACK_LAZY_COLUMN -> createIntent(JetpackLazyColumnMainActivity::class.java)
            Lesson.JETPACK_LAZY_ROW -> createIntent(JetpackLazyRowMainActivity::class.java)
            Lesson.JETPACK_LAZY_GRID -> createIntent(JetpackLazyGridMainActivity::class.java)
        }
    }

    private fun createIntent(activity: Class<out Activity>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}