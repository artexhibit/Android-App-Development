package ru.igorcodes.kotlinbasics.sendingDataFromFragmentToActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import ru.igorcodes.kotlinbasics.R

class SendingDataFromFragmentToActivityFragment: Fragment() {

    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var send: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sending_data_from_fragment_to_activity, container, false)
        name = view.findViewById(R.id.editTextName)
        email = view.findViewById(R.id.editTextEmail)
        send = view.findViewById(R.id.buttonSend)

        send.setOnClickListener {
            val username = name.text.toString()
            val email = email.text.toString()

            (activity as SendingDataFromFragmentToActivityActivity).takeData(username, email)
            //val mainActivity = activity as SendingDataFromFragmentToActivityActivity
            //mainActivity.takeData(username, email)
        }

        return view
    }
}