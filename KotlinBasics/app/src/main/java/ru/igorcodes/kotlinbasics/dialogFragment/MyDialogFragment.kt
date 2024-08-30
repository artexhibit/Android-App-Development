package ru.igorcodes.kotlinbasics.dialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import ru.igorcodes.kotlinbasics.R

class MyDialogFragment: DialogFragment() {

    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var cancel: Button
    private lateinit var ok: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_dialog, container, false)
        name = view.findViewById(R.id.editTextName)
        age = view.findViewById(R.id.editTextAge)
        cancel = view.findViewById(R.id.buttonCancel)
        ok = view.findViewById(R.id.buttonOk)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        ok.setOnClickListener {
            val userName: String = name.text.toString()
            val userAge: Int = age.text.toString().toInt()

            val dialogFragmentActivity: DialogFragmentActivity = activity as DialogFragmentActivity
            dialogFragmentActivity.getUserData(userName, userAge)
            dialog?.dismiss()
        }

        cancel.setOnClickListener {
            dialog?.dismiss()
        }
        return view
    }
}