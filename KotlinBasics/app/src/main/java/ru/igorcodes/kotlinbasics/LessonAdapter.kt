package ru.igorcodes.kotlinbasics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.igorcodes.kotlinbasics.data.Lesson

interface LessonClickListener {
    fun onLessonClick(lesson: Lesson)
}

class LessonAdapter(private val lessonList: ArrayList<Lesson>, private val lessonClickListener: LessonClickListener): RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

   inner class LessonViewHolder(view: View, private val lessonClickListener: LessonClickListener, private val lessonList: ArrayList<Lesson>): RecyclerView.ViewHolder(view) {
        val lessonTitle: TextView = view.findViewById(R.id.lessonText)
        init { view.setOnClickListener { lessonClickListener.onLessonClick(lessonList[adapterPosition]) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false)
        return LessonViewHolder(viewLayout, lessonClickListener, lessonList)
    }

    override fun getItemCount(): Int {
       return lessonList.count()
    }

    override fun onBindViewHolder(holder: LessonViewHolder, index: Int) {
        holder.lessonTitle.text = lessonList[index].lessonName
    }
}