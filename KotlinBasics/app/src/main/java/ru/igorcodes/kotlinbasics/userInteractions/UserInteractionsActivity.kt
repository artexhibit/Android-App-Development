package ru.igorcodes.kotlinbasics.userInteractions
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import ru.igorcodes.kotlinbasics.R

class UserInteractionsActivity: AppCompatActivity() {

    lateinit var showToast: Button
    lateinit var showSnackbar: Button
    lateinit var showDialog: Button
    lateinit var layout: ConstraintLayout

    lateinit var userName: EditText
    lateinit var userMessage: EditText
    lateinit var counter: Button
    lateinit var remember: CheckBox

    var count: Int = 0
    var name: String? = null
    var message: String? = null
    var isChecked: Boolean? = null

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_interactions)

        showToast = findViewById(R.id.buttonShowToast)
        showSnackbar = findViewById(R.id.buttonShowSnackbar)
        showDialog = findViewById(R.id.buttonShowDialog)
        layout = findViewById(R.id.main)

        userName = findViewById(R.id.editTextName)
        userMessage = findViewById(R.id.editTextMessage)
        counter = findViewById(R.id.button)
        remember = findViewById(R.id.checkBox)

        counter.setOnClickListener {
            count += 1
            counter.setText("" + count)
        }

        showToast.setOnClickListener {
            Toast.makeText(applicationContext, "This is a Toast message", Toast.LENGTH_LONG).show()
        }

        showSnackbar.setOnClickListener {
            Snackbar.make(layout, "This is a Toast message", Snackbar.LENGTH_INDEFINITE)
                .setAction("Close", View.OnClickListener {})
                .show()
        }

        showDialog.setOnClickListener {
            showAlertDialog()
        }
    }

    fun showAlertDialog() {
        var alertDialog = AlertDialog.Builder(this@UserInteractionsActivity)

        alertDialog
            .setTitle("Change")
            .setMessage("Do you want to change the text of the button?")
            .setIcon(R.drawable.warning)
            .setCancelable(false)
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, which ->
                dialogInterface.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, which ->
                showDialog.text = "Alert Dialog"
            })

        alertDialog.create().show()
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }

    fun saveData() {
        sharedPreferences = this.getSharedPreferences("savedData", Context.MODE_PRIVATE)
        name = userName.text.toString()
        message = userMessage.text.toString()
        isChecked = remember.isChecked

        val editor = sharedPreferences.edit()
        editor.putString("key name", name)
        editor.putString("key message", message)
        editor.putInt("key count", count)
        editor.putBoolean("key remember", isChecked!!)

        editor.apply()

        Toast.makeText(applicationContext, "Your data is saved", Toast.LENGTH_SHORT).show()
    }

    fun retrieveData() {
        sharedPreferences = this.getSharedPreferences("savedData", Context.MODE_PRIVATE)
        name = sharedPreferences.getString("key name", "K")
        message = sharedPreferences.getString("key message", "M")
        count = sharedPreferences.getInt("key count", 1)
        isChecked = sharedPreferences.getBoolean("key remember", false)

        userName.setText(name)
        userMessage.setText(message)
        counter.setText("" + count)
        remember.isChecked = isChecked!!
    }
}