package ru.igorcodes.kotlinbasics.sendingDataFromFragmentToFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.igorcodes.kotlinbasics.R

class SendingDataFromFragmentToFragmentSecondFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second_sending_data_from_fragment_to_fragment, container, false)
        val name: TextView = view.findViewById(R.id.textViewFirebaseName)

        name.text = arguments?.getString("username").toString()
        return view
    }
}