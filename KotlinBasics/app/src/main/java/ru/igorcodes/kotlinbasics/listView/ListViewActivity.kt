package ru.igorcodes.kotlinbasics.listView
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.igorcodes.kotlinbasics.R

class ListViewActivity: AppCompatActivity() {

    lateinit var listView: ListView
    var currentToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        listView = findViewById(R.id.listView)
        var countryList = resources.getStringArray(R.array.countriesListView)
        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countryList)
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            currentToast?.cancel()
           val countryName = parent.getItemAtPosition(position).toString()
            currentToast = Toast.makeText(this, "You selected $countryName", Toast.LENGTH_SHORT)
            currentToast?.show()
        }
    }
}