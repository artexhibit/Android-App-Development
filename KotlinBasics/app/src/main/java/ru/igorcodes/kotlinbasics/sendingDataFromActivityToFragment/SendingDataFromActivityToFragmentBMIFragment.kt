package ru.igorcodes.kotlinbasics.sendingDataFromActivityToFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.igorcodes.kotlinbasics.R

class SendingDataFromActivityToFragmentBMIFragment: Fragment() {

    lateinit var result: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b_m_i_sending_data_from_activity_to_fragment, container, false)
        result = view.findViewById(R.id.textViewResult)

        val weight = arguments?.getInt("weight") ?: 0
        val height = arguments?.getInt("height") ?: 0

        val bmi: Double = ((weight * 10000) / (height * height)).toDouble()
        result.text =  "Your BMI is $bmi"

        return view
    }
}