package ru.igorcodes.kotlinbasics.listFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import ru.igorcodes.kotlinbasics.R

class MyListSecondFragment: Fragment() {

    lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_second_my_list, container, false)
        imageView = view.findViewById(R.id.imageView)

        val index = arguments?.getInt("index", 0)

        when (index) {
            0 -> imageView.setImageResource(R.drawable.berlin)
            1 -> imageView.setImageResource(R.drawable.athens)
            2 -> imageView.setImageResource(R.drawable.rome)
            3 -> imageView.setImageResource(R.drawable.tokyo)
            4 -> imageView.setImageResource(R.drawable.amsterdam)
        }
        return view
    }
}