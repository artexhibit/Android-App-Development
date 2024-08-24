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
    SENDING_DATA_BETWEEN_ACTIVITIES("SendingDataBetweenActivities")
}

object LessonsData {
    fun lessonListItems(): ArrayList<Lesson> {
        return ArrayList(Lesson.values().toList())
    }
}