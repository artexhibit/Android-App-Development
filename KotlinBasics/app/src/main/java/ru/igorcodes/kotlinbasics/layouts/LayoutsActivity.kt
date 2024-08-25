package ru.igorcodes.kotlinbasics.layouts
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import ru.igorcodes.kotlinbasics.R

class LayoutsActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var myText: TextView
    lateinit var doMagic: Button
    lateinit var myButton: Button
    lateinit var name: EditText
    lateinit var image: ImageView
    lateinit var male: CheckBox
    lateinit var female: CheckBox
    lateinit var result: TextView
    lateinit var layout: ConstraintLayout
    lateinit var green: RadioButton
    lateinit var yellow: RadioButton
    lateinit var red: RadioButton
    lateinit var toggleButton: ToggleButton
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layouts)

        myText = findViewById(R.id.textExample)
        doMagic = findViewById(R.id.doMagic)
        myButton = findViewById(R.id.myButton)
        name = findViewById(R.id.editTextName)
        image = findViewById(R.id.imageViewExample)
        male = findViewById(R.id.checkBoxMale)
        female = findViewById(R.id.checkBoxFemale)
        result = findViewById(R.id.textViewResult)
        layout = findViewById(R.id.main)
        green = findViewById(R.id.radioButtonGreen)
        yellow = findViewById(R.id.radioButtonYellow)
        red = findViewById(R.id.radioButtonRed)
        toggleButton = findViewById(R.id.toggleImage)
        spinner = findViewById(R.id.spinnerCountry)

        var arrayAdapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = this

        myText.setTextColor(Color.BLACK)
        //myText.setText("This is an example")

        myText.setOnClickListener {
            myText.setTextColor(Color.WHITE)
            myText.setBackgroundColor(Color.BLACK)
        }
        doMagic.setOnClickListener {
            doMagic.setBackgroundColor(Color.BLACK)
            myText.isVisible = false
            doMagic.isVisible = false
        }

        myButton.setOnClickListener {
            myText.isVisible = true
            doMagic.isVisible = true
            myText.text = "${myText.text} ${name.text.toString()}"
            image.setImageResource(R.drawable.swifty_avatar)

            if (green.isChecked) {
                layout.setBackgroundColor(Color.GREEN)
            } else if (yellow.isChecked) {
                layout.setBackgroundColor(Color.YELLOW)
            } else {
                layout.setBackgroundColor(Color.RED)
            }
        }

        male.setOnClickListener {
            if (male.isChecked) {
                result.text = "Your gender is male"
                female.isChecked = false
            } else {
                result.text = "What is your gender?"
            }
        }

        female.setOnClickListener {
            if (female.isChecked) {
                result.text = "Your gender is female"
                male.isChecked = false
            } else {
                result.text = "What is your gender?"
            }
        }

        toggleButton.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                image.visibility = View.INVISIBLE
                result.text = "The image is invisible"
            } else {
                image.visibility = View.VISIBLE
                result.text = "The image is visible"
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, itemIndex: Int, id: Long) {
        if (parent != null) {
            result.text = parent.getItemAtPosition(itemIndex).toString()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}