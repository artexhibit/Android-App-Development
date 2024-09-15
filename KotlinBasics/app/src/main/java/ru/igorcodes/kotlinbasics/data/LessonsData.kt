package ru.igorcodes.kotlinbasics.data

enum class Lesson(val lessonName: String) {
    GRID_VIEW("GridView"),
    MY_FIRST_PROJECT("MyFirstProject"),
    OBJECT_ORIENTED_PROGRAMMING("ObjectOrientedProgramming"),
    INTENT("Intent"),
    LAYOUTS("Layouts"),
    LIFE_CYCLES("LifeCycles"),
    LIST_VIEW("ListView"),
    RECYCLER_VIEW("RecyclerView"),
    SCROLL_VIEW("ScrollView"),
    SERVICES("Services"),
    TOP_APP_BAR("TopAppBar"),
    USER_INTERACTIONS("UserInteractions"),
    WEB_VIEW("WebView"),
    MULTIPLE_LANGUAGE("MultipleLanguage"),
    FRAGMENTS("Fragments"),
    DIALOG_FRAGMENT("DialogFragment"),
    LIST_FRAGMENT("ListFragment"),
    SENDING_DATA_BETWEEN_ACTIVITIES("SendingDataBetweenActivities"),
    SENDING_DATA_FROM_ACTIVITY_TO_FRAGMENT("SendingDataFromActivityToFragment"),
    SENDING_DATA_FROM_FRAGMENT_TO_ACTIVITY("SendingDataFromFragmentToActivity"),
    SENDING_DATA_FROM_FRAGMENT_TO_FRAGMENT("SendingDataFromFragmentToFragment"),
    SEND_SMS("SendSMS"),
    SEND_EMAIL("SendEmail"),
    MAKE_CALL("MakeCall"),
    SPEECH_TO_TEXT("SpeechToText"),
    LOCAL_NOTIFICATION("LocalNotification"),
    PERIODIC_NOTIFICATION("PeriodicNotification"),
    NOTIFICATION_PROCEDURES("NotificationProcedures"),
    FIREBASE("Firebase"),
}

object LessonsData {
    fun lessonListItems(): ArrayList<Lesson> {
        return ArrayList(Lesson.values().toList())
    }
}